package com.volterra.ecosysteme;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

  public void setTarget(Tribe target) {
    this.target = target;
  }

  /**
   * The tribe *this* is aggressing. NULL if not aggressing anyone.
   */
  protected Tribe target;


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
   * Get the tribe which is the current tribe's target in a battle.
   * @return The target tribe
   */
  public Tribe getTarget() {
    return target;
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

  public Color getColor() { return this.members.get(0).getColor(); }

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
    ctx.stroke(this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue());
    ctx.strokeWeight(this.size() * 0.4f);
    ctx.ellipse(this.x, this.y, this.size(), this.size());
  }
}
