package com.volterra.ecosysteme;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import com.volterra.Engine.Simulation;
import com.volterra.Engine.visualeffects.DamageEffect;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Created by Koroeskohr on 25/04/2016.
 */
public abstract class Tribe<T extends Species> implements AIStateMachine, Renderable {

  /**
   * Coordinates of the tribe and center of the circle
   */
  protected float x, y;

  protected float xd = 0.0f;
  protected float yd = 0.0f;

  /**
   * Current state of the tribe AI.
   */
  protected State state;

  /**
   * All the members of a group. They all belong to the same species.
   */
  protected ArrayList<T> members;

  protected Species[] friendlySpecies;
  protected int averageLifeSpan;
  protected int litterSize;
  protected int aggressiveness;
  protected int force;
  protected int reproductivity;
  protected int mutualAid;
  protected int courage;
  protected float speed;
  protected Color color;

  /**
   * The tribe *this* is aggressing. NULL if not aggressing anyone.
   */
  protected Tribe target;

  /**
   * Get the tribe which is the current tribe's target in a battle.
   * @return The target tribe
   */
  public Tribe getTarget() {
    return target;
  }

  public void setTarget(Tribe target) {
    this.target = target;
  }

  public float getY() {
    return y;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getXd() {
    return xd;
  }

  public void setXd(float xd) {
    this.xd = xd;
  }

  public float getYd() {
    return yd;
  }

  public void setYd(float yd) {
    this.yd = yd;
  }

  /**
   * Get the current state of a tribe
   * @return The state of the tribe
   */
  public State getState() {
    return this.state;
  }

  /**
   * Set the state of a tribe
   * @param state The new state to apply on the tribe
   */
  public void setState(State state) {
    this.state = state;
  }

  /**
   * Return the current friendly species of Tribe
   * @return friendly species
   */
  public Species[] getFriendlySpecies() {
    return friendlySpecies;
  }

  /**
   * Set friendly species for the Tribe
   * @param friendlySpecies
   */
  public void setFriendlySpecies(Species[] friendlySpecies) {
    this.friendlySpecies = friendlySpecies;
  }

  /**
   * Get the average lifespan of a species
   * @return The average lifespan
   */
  public int getAverageLifeSpan() {
    return this.averageLifeSpan;
  }

  /**
   * Add average lifespan to current average lifespan
   * @param averageLifeSpan
   */
  public void addAverageLifeSpan(int averageLifeSpan) {
    this.averageLifeSpan += averageLifeSpan;
    if (this.averageLifeSpan > 100) this.averageLifeSpan = 100;
  }

  /**
   * Get litter size, the maximum number of children when giving birth
   * @return litter size
   */
  public int getLitterSize() {
    return this.litterSize;
  }

  /**
   * Add littersize to current littersize
   * @param litterSize
   */
  public void addLitterSize(int litterSize) {
    this.litterSize += litterSize;
    if (this.litterSize > 100) this.litterSize = 100;
  }

  /**
   * Get the aggressiveness value
   * @return Agressiveness value
   */
  public int getAggressiveness() {
    return this.aggressiveness;
  }

  /**
   * Add aggressiveness to current aggressiveness
   * @param aggressiveness
   */
  public void addAggressiveness(int aggressiveness) {
    this.aggressiveness += aggressiveness;
    if (this.aggressiveness > 100) this.aggressiveness = 100;
  }

  /**
   * Get the force of the species
   * @return Force of the species
   */
  public int getForce() {
    return this.force;
  }

  /**
   * Add force to current force
   * @param force
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
    return this.reproductivity;
  }

  /**
   * Add reproductivity chance to current reproductivity chance
   * @param reproductivity
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
    return this.mutualAid;
  }

  /**
   * Add mutual aid chance to current mutual aid chance
   * @param mutualAid
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
    return this.courage;
  }

  /**
   * Add courage to current courage
   * @param courage
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
   * Add speed to curret speed
   * @param speed
   */
  public void addSpeed(float speed) {
    this.speed += speed;
  }

  /**
   * Return current tribe's color
   * @return
     */
  public Color getColor() { return this.color; }

  /**
   * Computes the amount of members in a tribe
   * @return the amount of members in a tribe
   */
  public int size() {
    return this.members.size();
  }

  public int radius() {
    // FIXME: for now
    return this.size();
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
   * Get distance from Tribe pos to this.target pos
   * @return Distance as double
   */
  public double getDistanceToTarget() {
    return getDistance((this.target.getX() - this.x), (this.target.getY() - this.y));
  }

  /**
   *
   * @return if Tribe is currently in a action (aggressing, fleeing)
   */
  public boolean isBusy() {
    // TODO : probably more conditions
    return (this.state == State.AGGRESSING || this.state == State.FLEEING);
  }

  /**
   *
   * @return if enemy is close enough to be hit
   */
  public boolean isInAttackRange() {
    return getDistanceToTarget() < radius();
  }

  /**
   * TODO: define dynamic value for aggression range in Specie definition
   * @return if enemy if in aggression range
     */
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
   */
  public void attack(int damages) {
    Simulation.getSimulation().getEffectsDisplayer().add(new DamageEffect(damages, this.target.getX(), this.target.getY()));
    this.target.getDamages(damages);
  }

  /**
   * Remove members depending on the damages given
   * @param damages
   */
  public void getDamages(int damages) {
    int i = damages;
    while (i > 0 && this.size() >= 1) {
      this.members.remove(this.size() - 1);
      i--;
    }
  }

  /**
   * Return true if members size > 0
   * @return
   */
  public boolean isAlive() {
    return (this.size() >= 1);
  }

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

    switch (this.getState()){
      case AGGRESSING:
      case FIGHT:
        ctx.stroke(255, 0, 0);
      case FLEEING:
        ctx.stroke(255, 255, 255);
      default:
        ctx.noStroke();
    }

    ctx.ellipse(this.x, this.y, this.size()*3, this.size()*3);
    ctx.fill(255);
    ctx.textSize(12);
    ctx.text(this.size(), this.x + this.radius(), this.y + this.radius());
  }
}
