package com.volterra.ecosysteme;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.ArrayList;

import java.lang.reflect.Constructor;

import com.volterra.ecosysteme.species.Species;
import com.volterra.ecosysteme.utils.Utils;
import com.volterra.engine.Simulation;
import com.volterra.engine.visualeffects.BirthEffect;
import com.volterra.engine.visualeffects.DamageEffect;
import processing.core.PApplet;

/**
 * Created by Koroeskohr on 25/04/2016.
 */
public abstract class Tribe<T extends Species> implements AIStateMachine, Renderable {
  /**
   * Factor of the actual size of the drawn circles
   */
  private final int sizeFactor = 3;

  /**
   * Coordinates of the tribe and center of the circle
   */
  protected float x, y;

  /**
   * Horizontal speed coefficient
   */
  protected float xd = 0.0f;

  /**
   * Vertical speed coefficient
   */
  protected float yd = 0.0f;

  /**
   * Current state of the tribe AI.
   */
  protected State state;

  /**
   * All the members of a group. They all belong to the same species.
   */
  protected ArrayList<T> members;

  /**
   * The species that composes the <i>Tribe</i>.
   */
  private final Class<T> species;

  /**
   * Friendly species of the tribe. They will not aggress it.
   */
  protected Species[] friendlySpecies;

  /**
   * The average amount of time a member of a tribe lives
   */
  protected int averageLifeSpan;

  /**
   * Amount of children a tribe can have, based on species.
   */
  protected int litterSize;

  /**
   * Coefficient that influences probability to aggress other tribes
   */
  protected int aggressiveness;

  /**
   * Coefficient that influences damage dealt
   */
  protected int force;

  /**
   * Coefficient that influences probability to have births in the tribe
   */
  protected int reproductivity;

  /**
   * Coefficient that influences probability to help friendly aggressed tribes
   */
  protected int mutualAid;

  /**
   * Coefficient that influences probability to start any action
   */
  protected int courage;

  /**
   * Coefficient that influences movement speed on the screen
   */
  protected float speed;

  /**
   * Coefficient that influences how often a tribe will attack
   */
  protected float attackSpeed;

  /**
   * Display color of the tribe in the simulation
   */
  protected Color color;

  /**
   * The tribe *this* is aggressing. Equals null if not aggressing anyone.
   */
  protected Tribe target;

  /**
   * Timepoint when the last birth occured in the tribe.
   */
  protected Instant lastBirth;

  /**
   * Define the species attribute of a <i>Tribe</i>
   * @param species Class contained in species attribute. It is the same class which is passed in template.
     */
  public Tribe(Class<T> species) {
    this.species = species;
  }

  /**
   * Get the tribe which is the current tribe's target in a battle.
   * @return The target tribe
   */
  public Tribe getTarget() {
    return target;
  }

  /**
   * Set Tribe's target, used during an aggression sequence.
   * @param target
   * @see Simulation#processAiAggression(Tribe)
   */
  public void setTarget(Tribe target) {
    this.target = target;
  }

  /**
   * Getter for the tribe's Y coordinate
   * @return the tribe's Y coordinate
   */
  public float getY() {
    return y;
  }

  /**
   * Getter for the tribe's X coordinate
   * @return the tribe's X coordinate
   */
  public float getX() {
    return x;
  }

  /**
   * Setter for the X coordinate
   * @param x Value to set the horizontal coordinate to.
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Setter for the Y coordinate
   * @param y Value to set the vertical coordinate to.
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * Getter for horizontal movement coefficient
   * @return horizontal movement coefficient
   */
  public float getXd() {
    return xd;
  }

  /**
   * Setter for horizontal movement coefficient
   * @param xd horizontal movement coefficient
   */
  public void setXd(float xd) {
    this.xd = xd;
  }

  /**
   * Getter for vertical movement coefficient
   * @return vertical movement coefficient
   */
  public float getYd() {
    return yd;
  }

  /**
   * Setter for vertical movement coefficient
   * @param yd vertical movement coefficient
   */
  public void setYd(float yd) {
    this.yd = yd;
  }

  /**
   * Get the current AIStateMachine state of a tribe.
   * @return A state from the enum AIStateMachine.State.
   * @see com.volterra.ecosysteme.AIStateMachine.State
   */
  public State getState() {
    return this.state;
  }

  /**
   * Changes the state. Used in the AI loop.
   * @param state The new state to apply on the tribe
   */
  public void setState(State state) {
    this.state = state;
  }

  /**
   * Get an array of species that could help or at least not attack the Tribe.
   * @return An array of species that could help or at least not attack the Tribe.
   */
  public Species[] getFriendlySpecies() {
    return friendlySpecies;
  }

  /**
   * Set friendly species for the Tribe
   * @param friendlySpecies Friend species
   */
  public void setFriendlySpecies(Species[] friendlySpecies) {
    this.friendlySpecies = friendlySpecies;
  }

  /**
   * Get the average life span of a Tribe.
   * @return the average life span value of a Tribe
   */
  public int getAverageLifeSpan() {
    return this.averageLifeSpan;
  }

  /**
   * Add a value to the averageLifeSpan param. Necessary proxy due to the use of decorators.
   * @param lifeSpan Amount of life span added to the Tribe parameter
   */
  public void addAverageLifeSpan(int lifeSpan) {
    this.averageLifeSpan += lifeSpan;
    if (this.averageLifeSpan > 100) this.averageLifeSpan = 100;
  }

  /**
   * Get litter size, the maximum number of children when giving birth
   * @return litter size
   */
  public int getLitterSize() {
    return Utils.clamp(this.litterSize, 1, 100);
  }

  /**
   * Add littersize to current littersize
   * @param litterSize Amount of litter size added to the Tribe parameter
   */
  public void addLitterSize(int litterSize) {
    this.litterSize += litterSize;
    if (this.litterSize > 100) this.litterSize = 100;
  }

  /**
   * Get the aggressiveness value of a Tribe.
   * @return the aggressiveness value of a Tribe
   */
  public int getAggressiveness() {
    return Utils.clamp(this.aggressiveness, 1, 100);
  }

  /**
   * Add aggressiveness to current aggressiveness
   * @param aggressiveness Amount of aggressiveness added to the Tribe parameter
   */
  public void addAggressiveness(int aggressiveness) {
    this.aggressiveness += aggressiveness;
    if (this.aggressiveness > 100) this.aggressiveness = 100;
  }

  /**
   * Get the force value of a Tribe. Determines the damage dealt by a Tribe.
   * @return the force value of a Tribe
   */
  public int getForce() {
    return Utils.clamp(this.force, 1, 100);
  }

  /**
   * Add force to current force
   * @param force Amount of force added to the Tribe parameter
   */
  public void addForce(int force) {
    this.force += force;
    if (this.force > 100) this.force = 100;
  }

  /**
   * Get the reproductivity chance of the species
   * @return Value of the reproductivity
   */
  public int getReproductivity() {
    return Utils.clamp(this.reproductivity, 1, 100);
  }

  /**
   * Add reproductivity chance to current reproductivity chance
   * @param reproductivity Amount of reproductivity added to the Tribe parameter
   */
  public void addReproductivity(int reproductivity) {
    this.reproductivity += reproductivity;
    if (this.reproductivity > 100) this.reproductivity = 100;
  }

  /**
   * Get the value of the mutual aid chance of the species
   * @return Value of the mutual aid
   */
  public int getMutualAid() {
    return Utils.clamp(this.mutualAid, 1, 100);
  }

  /**
   * Add mutual aid chance to current mutual aid chance
   * @param mutualAid Amount of mutual aid added to the Tribe parameter
   */
  public void addMutualAid(int mutualAid) {
    this.mutualAid += mutualAid;
    if (this.mutualAid > 100) this.mutualAid = 100;
  }

  /**
   * Get the value of courage of the species
   * @return Value of courage
   */
  public int getCourage() {
    return Utils.clamp(this.courage, 1, 100);
  }

  /**
   * Add courage to current courage
   * @param courage Amount of courage added to the Tribe parameter
   */
  public void addCourage(int courage) {
    this.courage += courage;
    if (this.courage > 100) this.courage = 100;
  }

  /**
   * Get the speed value of the species
   * @return Value of the speed
   */
  public float getSpeed() {
    return this.speed;
  }

  /**
   * Add speed to current speed
   * @param speed Amount of speed added to the Tribe parameter
   */
  public void addSpeed(float speed) {
    this.speed += speed;
  }

  /**
   * Get the attack speed value of the species
   * @return Value of the attack speed
   */
  public float getAttackSpeed() { return this.attackSpeed; }

  /**
   * Add attack speed to current attack speed
   * @param attackSpeed Amount of attack speed added to the Tribe parameter
   */
  public void addAttackSpeed(float attackSpeed) { this.attackSpeed += attackSpeed; }

  /**
   * Get the color of the tribe.
   * @return The color of the tribe, that depends on the tribe's species.
   * @see Species#getColor()
   */
  public Color getColor() { return this.color; }

  /**
   * Returns the last time a Tribe had a birth
   * @return Instant of the last birth
   */
  public Instant getLastBirth() {
    return this.lastBirth;
  }

  /**
   * Set the last birth to now.
   */
  public void resetLastBirth() {
    this.lastBirth = Instant.now();
  }

  /**
   * Computes the amount of members in a tribe
   * @return the amount of members in a tribe
   */
  public int size() {
    return this.members.size();
  }

  /**
   * Return the size of the Tribe of the screen
   * @return size of the Tribe of the screen
   */
  public int radius() {
    // FIXME: for now
    return this.size()*this.sizeFactor;
  }

  /**
   * Get distance from Tribe pos to x and y coordinates
   * @param x X coordinate of the considered object
   * @param y Y coordinate of the considered object
   * @return Distance as double
   */
  private double getDistance(float x, float y){
    return Math.sqrt(x*x + y*y);
  }

  /**
   * Returns the distance to the target tribe
   * @return the distance to the target tribe
   */
  public double getDistanceToTarget() {
    return getDistance((this.target.getX()+this.sizeFactor - this.x), (this.target.getY()+this.sizeFactor - this.y));
  }

  /**
   * Determines if a Tribe is able to undergo an action
   * @return Whether a tribe is in an action or not
   */
  public boolean isBusy() {
    // TODO : probably more conditions
    return (this.state == State.AGGRESSING || this.state == State.FLEEING);
  }

  /**
   * Determines if a Tribe can attack its target
   * @return The ability of a tribe to hit its target
   */
  public boolean isInAttackRange() {
    return getDistanceToTarget() < radius();
  }

  /**
   * Determines if a Tribe is close enough to help a friendly tribe
   * @return Whether or not the tribe is close enough to help
   */
  public boolean isInMutualAidRange() { return getDistanceToTarget() < (radius()+size()); }

  /**
   * Determines if a Tribe can start an aggression on its target
   * @return The ability of a tribe to aggress its target
   */
  //TODO: define dynamic value for aggression range in Species definition
  public boolean isInAggressionRange() { return getDistanceToTarget() < (radius() + 100); }

  /**
   * Starts an aggression with a tribe, putting it in fleeing or aggressing state as well, depending on a courage test.
   * @param enemy the tribe *this* is attacking
   */
  public void aggress(Tribe enemy) {
    setState(State.AGGRESSING);
    // TODO : determine what to do with that
    // enemy.notifyOfAggression(this);
  }

  /**
   * Damages the target based on force and number of units
   * @param damages Amount of damage
   */
  public void attack(int damages) {
    this.target.getDamages(damages);
  }

  /**
   * Remove members depending on the damages given
   * @param damages Amount of damage taken
   */
  public void getDamages(int damages) {
    int i = damages;
    while (i > 0 && this.size() >= 1) {
      this.members.remove(this.size() - 1);
      i--;
    }
    Simulation.getSimulation().getEffectsDisplayer().add(new DamageEffect(damages, this.getX()+this.size(), this.getY()+this.size()));
  }

  /**
   * Returns whether a Tribe is alive or not
   * @return Whether a Tribe is alive or not
   */
  public boolean isAlive() {
    return (this.size() >= 1);
  }

  /**
   * Return the species attribute of the <i>Tribe</i>
   * @return A <i>Class</i> which is the <i>Species</i> composing the <i>Tribe</i>
     */
  public Class<T> getSpecies() {
    return this.species;
  }

  /**
   * Add a Species to the members ArrayList
   * @param newMember Member of a species
   */
  public void addMember(T newMember) {
    this.members.add(newMember);
  }

  /**
   * Simulate birth in the tribe. Create new people and add them to the tribe.
   * @param litterSize number of individual to add to the tribe.
   */
  public void newMembers(int litterSize) {
    for(int i = 0 ; i < litterSize ; i++) {
      try {
        if (this.species == null) throw new NullPointerException("node must have children");
        Constructor ctor = this.species.getConstructor();
        this.members.add(((this.species.cast(ctor.newInstance()))));
      } catch (NoSuchMethodException |
              InvocationTargetException |
              IllegalAccessException |
              InstantiationException e) {
        e.printStackTrace();
      }
    }
    if (litterSize > 0) Simulation.getSimulation().getEffectsDisplayer().add(new BirthEffect(litterSize, this.getX()+this.size(), this.getY()+this.size()));
  }

  /**
   * @see com.volterra.ecosysteme.Renderable#update
   */
  public void runAI(float deltaTime) {

  }

  public void update(float delta) {

  }

  /**
   * Display the tribe on the screen.
   * @param ctx The PApplet which draw the <i>Renderable</i> element.
     */
  public void render(PApplet ctx) {
    ctx.fill(this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue());
    ctx.strokeWeight(3);

    switch (this.state){
      case AGGRESSING:
        ctx.stroke(255, 100, 0);
        break;
      case FIGHT:
        ctx.stroke(255, 0, 0);
        break;
      case FLEEING:
        ctx.stroke(0, 255, 255);
        break;
      case NEUTRAL:
        ctx.stroke(255, 255, 255);
        break;
      default:
        ctx.noStroke();
        break;
    }

    ctx.ellipse(this.x, this.y, this.radius(), this.radius());
    ctx.fill(255);
    ctx.textSize(12);
    ctx.text(this.size(), this.x+this.size(), this.y+this.size());
  }
}
