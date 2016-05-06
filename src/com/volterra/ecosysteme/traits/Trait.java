package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.species.Species;
import com.volterra.ecosysteme.Tribe;
import com.volterra.engine.Simulation;
import com.volterra.engine.visualeffects.BirthEffect;
import processing.core.PApplet;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;

/**
 * Created by Christophe on 25/04/2016.
 *
 * Decorator to add character <i>Traits</i> that give some bonuses or penalties to a <i>Tribe</i>.
 */
public abstract class Trait<T extends Species> extends Tribe<T> {
  /**
   * The <i>Tribe</i> on which the trait is added
   */
  protected Tribe<T> _originalTribe;

  /**
   * Constructor
   * @param originalTribe The <i>Tribe</i> on which apply the trait.
   */
  Trait(Tribe<T> originalTribe) {
    super(originalTribe.getSpecies());
    this._originalTribe = originalTribe;
  }

  /**
   * Simple proxy from getting the tribe's Y coordinate
   * @return the tribe's Y coordinate
   */
  @Override
  public float getY() {
    return _originalTribe.getY();
  }

  /**
   * Simple proxy from getting the tribe's X coordinate
   * @return the tribe's X coordinate
   */
  @Override
  public float getX() {
    return _originalTribe.getX();
  }

  /**
   * Setter for the X coordinate
   * @param x Value to set the horizontal coordinate to.
   */
  @Override
  public void setX(float x) {
    _originalTribe.setX(x);
  }

  /**
   * Setter for the Y coordinate
   * @param y Value to set the vertical coordinate to.
   */
  @Override
  public void setY(float y) {
    _originalTribe.setY(y);
  }

  @Override
  public float getYd() {
    return _originalTribe.getYd();
  }

  @Override
  public float getXd() {
    return _originalTribe.getXd();
  }

  @Override
  public void setXd(float xd) {
    _originalTribe.setXd(xd);
  }

  @Override
  public void setYd(float yd) {
    _originalTribe.setYd(yd);
  }

  /**
   * Get the color of the tribe.
   * @return The color of the tribe, that depends on the tribe's species.
   * @see Species#getColor()
   */
  @Override
  public Color getColor() { return _originalTribe.getColor(); }

  /**
   * Get the target of the tribe if it is in aggression state.
   * @return The enemy tribe targeted by Tribe.
   */
  @Override
  public Tribe getTarget() {
    return _originalTribe.getTarget();
  }

  /**
   * Set Tribe's target, used during an aggression sequence.
   * @param target
   * @see Simulation#processAiAggression(Tribe)
   */
  @Override
  public void setTarget(Tribe target) {
    _originalTribe.setTarget(target);
  }

  /**
   * Get the current AIStateMachine state of a tribe.
   * @return A state from the enum AIStateMachine.State.
   * @see com.volterra.ecosysteme.AIStateMachine.State
   */
  @Override
  public State getState() {
    return _originalTribe.getState();
  }

  /**
   * Changes the state. Used in the AI loop.
   * @param state The new state to apply on the tribe
   */
  @Override
  public void setState(State state) {
    _originalTribe.setState(state);
  }

  /**
   * Get an array of species that could help or at least not attack the Tribe.
   * @return An array of species that could help or at least not attack the Tribe.
   */
  @Override
  public Species[] getFriendlySpecies() {
    return this._originalTribe.getFriendlySpecies();
  }

  /**
   * Set the friendly species of a Tribe
   * @param friendlySpecies Friend species
   */
  @Override
  public void setFriendlySpecies(Species[] friendlySpecies) {
    this._originalTribe.setFriendlySpecies(friendlySpecies);
  }

  /**
   * Get the speed characteristic of a Tribe, used by the processing of the actual moving speed
   * @return The speed characteristic of a Tribe
   */
  @Override
  public float getSpeed() {
    return this._originalTribe.getSpeed();
  }

  /**
   * Add a value to the speed param. Necessary proxy due to the use of decorators.
   * @param speed Amount of speed added to the Tribe parameter
   */
  @Override
  public void addSpeed(float speed) {
    this._originalTribe.addSpeed(speed);
  }

  /**
   * Get attack speed value of a Tribe. Determines how often it will strike.
   * @return The attack speed param of a Tribe.
   */
  @Override
  public float getAttackSpeed() {
    return this._originalTribe.getAttackSpeed();
  }

  /**
   * Add a value to the attack speed param. Necessary proxy due to the use of decorators.
   * @param attackSpeed Amount of attack speed added to the Tribe parameter
   */
  @Override
  public void addAttackSpeed(float attackSpeed) {
    this._originalTribe.addAttackSpeed(attackSpeed);
  }

  /**
   * Get the aggressiveness value of a Tribe.
   * @return the aggressiveness value of a Tribe
   */
  @Override
  public int getAggressiveness() {
    return this._originalTribe.getAggressiveness();
  }

  /**
   * Add a value to the aggressiveness param. Necessary proxy due to the use of decorators.
   * @param aggressiveness Amount of aggressiveness added to the Tribe parameter
   */
  @Override
  public void addAggressiveness(int aggressiveness) {
    this._originalTribe.addAggressiveness(aggressiveness);
  }

  /**
   * Get the force value of a Tribe. Determines the damage dealt by a Tribe.
   * @return the force value of a Tribe
   */
  @Override
  public int getForce() {
    return this._originalTribe.getForce();
  }

  /**
   * Add a value to the force param. Necessary proxy due to the use of decorators.
   * @param force Amount of force added to the Tribe parameter
   */
  @Override
  public void addForce(int force) {
    this._originalTribe.addForce(force);
  }

  /**
   * Get the Reproductivity value of a Tribe.
   * @return the Reproductivity value of a Tribe
   */
  @Override
  public int getReproductivity() {
    return this._originalTribe.getReproductivity();
  }

  /**
   * Add a value to the reproductivity param. Necessary proxy due to the use of decorators.
   * @param reproductivity Amount of reproductivity added to the Tribe parameter
   */
  @Override
  public void addReproductivity(int reproductivity) {
    this._originalTribe.addReproductivity(reproductivity);
  }

  /**
   * Get the MutualAid value of a Tribe.
   * @return the MutualAid value of a Tribe
   */
  @Override
  public int getMutualAid() {
    return this._originalTribe.getMutualAid();
  }

  /**
   * Add a value to the mutual aid param. Necessary proxy due to the use of decorators.
   * @param mutualAid Amount of mutual aid added to the Tribe parameter
   */
  @Override
  public void addMutualAid(int mutualAid) {
    this._originalTribe.addMutualAid(mutualAid);
  }

  /**
   * Get the courage value of a Tribe.
   * @return the courage value of a Tribe
   */
  @Override
  public int getCourage() {
    return this._originalTribe.getCourage();
  }

  /**
   * Add a value to the courage param. Necessary proxy due to the use of decorators.
   * @param courage Amount of courage added to the Tribe parameter
   */
  @Override
  public void addCourage(int courage) {
    this._originalTribe.addCourage(courage);
  }

  /**
   * Get the litterSize value of a Tribe.
   * @return the litterSize value of a Tribe
   */
  @Override
  public int getLitterSize() {
    return this._originalTribe.getLitterSize();
  }

  /**
   * Add a value to the litterSize param. Necessary proxy due to the use of decorators.
   * @param litterSize Amount of litter size added to the Tribe parameter
   */
  @Override
  public void addLitterSize(int litterSize) {
    this._originalTribe.addLitterSize(litterSize);
  }

  /**
   * Get the average life span of a Tribe.
   * @return the average life span value of a Tribe
   */
  @Override
  public int getAverageLifeSpan() {
    return this._originalTribe.getAverageLifeSpan();
  }

  /**
   * Add a value to the averageLifeSpan param. Necessary proxy due to the use of decorators.
   * @param lifeSpan Amount of life span added to the Tribe parameter
   */
  @Override
  public void addAverageLifeSpan(int lifeSpan) {
    this._originalTribe.addAverageLifeSpan(lifeSpan);
  }

  /**
   * Returns the last time a Tribe had a birth
   * @return Instant of the last birth
   */
  @Override
  public Instant getLastBirth() { return this._originalTribe.getLastBirth(); }

  /**
   * Set the last birth to now.
   */
  @Override
  public void resetLastBirth() { this._originalTribe.resetLastBirth(); }

  /**
   * Returns the amount of Species instances in a Tribe
   * @return amount of Species instances in a Tribe
   */
  @Override
  public int size() {
    return _originalTribe.size();
  }

  /**
   * Return the size of the Tribe of the screen
   * @return size of the Tribe of the screen
   */
  @Override
  public int radius() {
    return _originalTribe.radius();
  }

  /**
   * Returns the distance to the target tribe
   * @return the distance to the target tribe
   */
  @Override
  public double getDistanceToTarget() {
    return _originalTribe.getDistanceToTarget();
  }


  /**
   * Determines if a Tribe is able to undergo an action
   * @return The ability of a Tribe to do something
   */
  @Override
  public boolean isBusy() {
    return _originalTribe.isBusy();
  }

  /**
   * Determines if a Tribe can attack its target
   * @return The ability of a tribe to hit its target
   */
  @Override
  public boolean isInAttackRange() {
    return _originalTribe.isInAttackRange();
  }

  /**
   * Determines if a Tribe can start an aggression on its target
   * @return The ability of a tribe to aggress its target
   */
  @Override
  public boolean isInAggressionRange() {
    return _originalTribe.isInAggressionRange();
  }

  /**
   * Starts the process of aggressing an enemy
   * @param enemy the tribe *this* is attacking
   */
  @Override
  public void aggress(Tribe enemy) {
    _originalTribe.aggress(enemy);
  }

  /**
   * Damages an enemy
   * @param damages Amount of damage
   */
  @Override
  public void attack(int damages) {
    _originalTribe.attack(damages);
  }

  /**
   * Make a tribe receive damage and lose members
   * @param i Amount of damage taken
   */
  @Override
  public void getDamages(int i) { _originalTribe.getDamages(i); }

  /**
   * Returns
   * @return Whether a Tribe is alive or not
   */
  @Override
  public boolean isAlive() { return _originalTribe.isAlive(); }

  /**
   * Add a Species to the members ArrayList
   * @param newMember Member of a species
   */
  @Override
  public void addMember(T newMember) {
    _originalTribe.addMember(newMember);
  }

  /**
   * Add <i>litterSize</i> amount of members to the members ArrayList
   * @param litterSize number of individual to add to the tribe.
   */
  @Override
  public void newMembers(int litterSize) {
    for(int i = 0 ; i < litterSize ; i++) {
      try {
        if (_originalTribe.getSpecies() == null)
          throw new IllegalStateException("node must have children");
        Constructor ctor = _originalTribe.getSpecies().getConstructor();
        _originalTribe.addMember(((_originalTribe.getSpecies().cast(ctor.newInstance()))));
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
   * @see com.volterra.ecosysteme.AIStateMachine#runAI
   */
  @Override
  public void runAI(float deltaTime) {
    _originalTribe.runAI(deltaTime);
  }

  /**
   * @see com.volterra.ecosysteme.Renderable#update
   */
  @Override
  public void update(float deltaTime) {
    _originalTribe.update(deltaTime);
  }

  /**
   * @see com.volterra.ecosysteme.Renderable#render
   * @param ctx The PApplet which draw the <i>Renderable</i> element.
   */
  @Override
  public void render(PApplet ctx) {
    _originalTribe.render(ctx);
  }

}
