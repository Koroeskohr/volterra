package com.volterra.ecosysteme;

import java.util.ArrayList;

/**
 * Created by Koroeskohr on 25/04/2016.
 */
public abstract class Tribe<T extends Species> implements AIStateMachine, Renderable {


  /**
   * Coordinates of the tribe and center of the circle
   */
  private float x, y;

  /**
   * Current state of the tribe AI.
   */
  private State state;

  /**
   * All the members of a group. They all belong to the same species.
   */
  private ArrayList<T> members;

  /**
   * The tribe *this* is aggressing. NULL if not aggressing anyone.
   */
  private Tribe target;


  public float getY() {
    return y;
  }

  public float getX() {
    return x;
  }

  public Tribe getTarget() {
    return target;
  }

  public State getState() {
    return this.state;
  }

  public void setState(State state) {
    this.state = state;
  }

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
  private double getDistanceToTarget() {
    return getDistance(this.target.getX(), this.target.getY());
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
   * @param enemy the tribe *this* is attacking
   */
  public void attack(Tribe enemy) {
    // math magic
  }

  /**
   * Get the average lifespan of a species
   * @return The average lifespan
   */
  public int getAverageLifeSpan() {
    return this.members.get(0).getAverageLifeSpan();
  }

  /**
   * Get litter size, the maximum number of children when giving birth
   * @return litter size
   */
  public int getLitterSize() {
    return this.members.get(0).getLitterSize();
  }

  /**
   * Get the aggressiveness value
   * @return Agressiveness value
   */
  public int getAggressiveness() {
    return this.members.get(0).getAggressiveness();
  }

  /**
   * Get the force of the species
   * @return Force of the species
   */
  public int getForce() {
    return this.members.get(0).getForce();
  }

  /**
   * Get the reproductivity chance of the species
   * @return Value of the reproductivity
   */
  public int getReproductivity() {
    return this.members.get(0).getReproductivity();
  }

  /**
   * Get the value of the mutual aid chance of the species
   * @return Value of the mutual aid
   */
  public int getMutualAid() {
    return this.members.get(0).getMutualAid();
  }

  /**
   * Get the value of courage of the species
   * @return Value of courage
   */
  public int getCourage() {
    return this.members.get(0).getCourage();
  }

  /**
   * Get the speed value of the species
   * @return Value of the speed
   */
  public float getSpeed() {
    return this.members.get(0).getSpeed();
  }

  public void runAI(float deltaTime) {

  }

  public void update(float deltaTime) {

  }

  public void render() {

  }
}
