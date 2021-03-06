package com.volterra.ecosysteme.species;


import com.volterra.ecosysteme.Renderable;
import processing.core.PApplet;

import java.awt.*;
import java.util.Random;

/**
 * Created by YellowFish on 25/04/2016.
 */
public abstract class Species {
  /**
   * Species this species is friend with. Currently not used.
   */
  private Species[] friendlySpecies;

  /**
   * Average life span of the species
   */
  private int averageLifeSpan;

  /**
   * Number of children max when giving birth
   */
  private int litterSize;

  /**
   *  Aggressiveness of the species (Range from 1 to 100)
   */
  private int aggressiveness;

  /**
   * Force, affects damages given (Range from 1 to 100
   */
  private int force;

  /**
   * Chance and delay of reproduction (Range from 1 to 100)
   * 100 = 1 reproduction per second and 100% change of success
   * 50 = 1 reproduction per two seconds, 50% chance of success
   */
  private int reproductivity;

  /**
   * Chance of helping friendly species (Range 1 to 100). Currently not used.
   */
  private int mutualAid;

  /**
   * Courage, affects actions such as aggression (Range 1 to 100)
   */
  private int courage;

  /**
   * Speed of the tribe
   * 1 = normal speed
   */
  private float speed;

  /**
   * Attack speed
   * 1 = normal
   */
  private float attackSpeed;

  /**
   * R, G, B components to define the tribe color
   */
  private Color color;

  /**
   * Individual position
   */
  private float x, y;

  /**
   * Direction vector
   */
  private float dx, dy;

  /**
   * Default constructor
   */
  public Species() {
    this.friendlySpecies = null;
    this.averageLifeSpan = 0;
    this.litterSize = 0;
    this.aggressiveness = 0;
    this.force = 0;
    this.reproductivity = 0;
    this.mutualAid = 0;
    this.courage = 0;
    this.speed = 0;
    this.attackSpeed = 0;
    this.color = new Color((int)0,(int)0,(int)0);
    this.x = 0;
    this.y = 0;
  }

  /**
   * Constructor with all the parameters
   * @param friendlySpecies
   * @param averageLifeSpan
   * @param litterSize
   * @param aggressiveness
   * @param force
   * @param reproductivity
   * @param mutualAid
   * @param courage
   * @param speed
   */
  public Species(Species[] friendlySpecies, int averageLifeSpan, int litterSize, int aggressiveness, int force, int reproductivity, int mutualAid, int courage, float speed, float attackSpeed, Color color) {
    this.friendlySpecies = friendlySpecies;
    this.averageLifeSpan = averageLifeSpan;
    this.litterSize = litterSize;
    this.aggressiveness = aggressiveness;
    this.force = force;
    this.reproductivity = reproductivity;
    this.mutualAid = mutualAid;
    this.courage = courage;
    this.speed = speed;
    this.attackSpeed = attackSpeed;
    this.color = color;
  }

  /**
   * Get friendly species
   * @return Array vith friendly species
   */
  public Species[] getFriendlySpecies() {
      return friendlySpecies;
  }

  /**
   * Set new array of friendly species
   * @param friendlySpecies Array with species
   */
  public void setFriendlySpecies(Species[] friendlySpecies) {
    this.friendlySpecies = friendlySpecies;
  }

  /**
   * Get the average lifespan of a species
   * @return The average lifespan
   */
  public int getAverageLifeSpan() {
      return averageLifeSpan;
  }

  /**
   * Set new average lifespan for a species
   * @param averageLifeSpan Value of the average lifespan
   */
  public void setAverageLifeSpan(int averageLifeSpan) {
      this.averageLifeSpan = averageLifeSpan;
  }

  /**
   * Get litter size, the maximum number of children when giving birth
   * @return litter size
   */
  public int getLitterSize() {
      return litterSize;
  }

  /**
   * Set new litter size
   * @param litterSize Value of the maximum children when giving birth
   */
  public void setLitterSize(int litterSize) {
      this.litterSize = litterSize;
  }

  /**
   * Get the aggressiveness value
   * @return Agressiveness value
   */
  public int getAggressiveness() {
      return aggressiveness;
  }

  /**
   * Set new value for the aggressiveness of the species
   * @param aggressiveness Value of aggressiveness
   */
  public void setAggressiveness(int aggressiveness) {
      this.aggressiveness = aggressiveness;
  }

  /**
   * Get the force of the species
   * @return Force of the species
   */
  public int getForce() {
      return force;
  }

  /**
   * Set new value for the force of the species
   * @param force Value of the force
   */
  public void setForce(int force) {
      this.force = force;
  }

  /**
   * Get the reproductivity chance of the species
   * @return Value of the reproductivity
   */
  public int getReproductivity() {
      return reproductivity;
  }

  /**
   * Set new value for the reproductivity chance of the species
   * @param reproductivity Value of the reproductivity
   */
  public void setReproductivity(int reproductivity) {
      this.reproductivity = reproductivity;
  }

  /**
   * Get the value of the mutual aid chance of the species
   * @return Value of the mutual aid
   */
  public int getMutualAid() {
      return mutualAid;
  }

  /**
   * Set new value for the chance of mutual aid of the species
   * @param mutualAid Value of the mutual aid
   */
  public void setMutualAid(int mutualAid) {
      this.mutualAid = mutualAid;
  }

  /**
   * Get the value of courage of the species
   * @return Value of courage
   */
  public int getCourage() {
      return courage;
  }

  /**
   * Set new value for the courage of the species
   * @param courage Value of courage
   */
  public void setCourage(int courage) {
      this.courage = courage;
  }

  /**
   * Get the speed value of the species
   * @return Value of the speed
   */
  public float getSpeed() {
    return speed;
  }

  /**
   * Set new value for the speed of the species
   * @param speed Value of the speed
   */
  public void setSpeed(float speed) {
    this.speed = speed;
  }

  /**
   * Get the attack speed value of the species
   * @return Value of the attack speed
   */
  public float getAttackSpeed() {
    return attackSpeed;
  }

  /**
   * Set new value for the attack speed of the species
   * @param attackSpeed Value of the speed
   */
  public void setAttackSpeed(float attackSpeed) {
    this.attackSpeed = attackSpeed;
  }

  /**
   * Get Species color
   * @return color
   */
  public Color getColor() {
    return color;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void update(float areaRad) {
    float x = this.x;
    float y = this.y;
    float dx = this.dx;
    float dy = this.dy;
    float areaRadius = areaRad * 0.5f;
    float speed = 0.1f;
    Random randomGen = new Random();

    if ((x * x) + (y * y) >= (areaRadius) * (areaRadius)) {
      float distanceToCenter = (float)Math.sqrt((x * x) + (y * y));
      float ratio = speed / distanceToCenter;
      dx = ratio * (-x);
      dy = ratio * (-y);
    }
    else if (dx == 0 && dy == 0) {
      /* Define new direction */
      dx = randomGen.nextFloat() * (speed - (-speed)) - speed;
      dy = (float) Math.sqrt(speed * speed - (dx * dx));
      if (randomGen.nextBoolean()) {
        dy = -dy;
      }
    }
    else {
      if (randomGen.nextFloat() > 0.99) {
        dx = 0;
        dy = 0;
      }
      else {
        double a = randomGen.nextDouble() * (speed - (-speed)) - speed;
        float tmpXd = dx * (float)Math.cos(a) - dy * (float)Math.sin(a);
        dy = dx * (float)Math.sin(a) + dy * (float)Math.cos(a);
        dx = tmpXd;
      }
    }

    this.dx = dx;
    this.dy = dy;
    this.x = x+ dx;
    this.y = y + dy;
  }
}
