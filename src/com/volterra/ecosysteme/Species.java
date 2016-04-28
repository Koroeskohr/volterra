package com.volterra.ecosysteme;


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
   * Force, affects damages given
   */
  private int force;

  /**
   * Chance of reproduction
   */
  private int reproductivity;

  /**
   * Chance of helping friendly species. Currently not used.
   */
  private int mutualAid;

  /**
   * Courage, affects actions such as aggression
   */
  private int courage;

  /**
   * Speed of the tribe
   */
  private float speed;

  /**
   * R, G, B components to define the tribe color
   */
  private float r, g, b;

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
  public Species(Species[] friendlySpecies, int averageLifeSpan, int litterSize, int aggressiveness, int force, int reproductivity, int mutualAid, int courage, float speed) {
    this.friendlySpecies = friendlySpecies;
    this.averageLifeSpan = averageLifeSpan;
    this.litterSize = litterSize;
    this.aggressiveness = aggressiveness;
    this.force = force;
    this.reproductivity = reproductivity;
    this.mutualAid = mutualAid;
    this.courage = courage;
    this.speed = speed;
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
   * Get the value of red component in species color.
   * @return Value of red component
     */
  public float getR() {
    return r;
  }

  /**
   * Set the value of blue component in species color
   * @param r the new value of blue component
   */
  public void setR(float r) {
    if (r > 255) {
      r = 255;
    }
    this.r = r;
  }

  /**
   * Get the value of green component in species color.
   * @return Value of green component
   */
  public float getG() {
    return g;
  }

  /**
   * Set the value of blue component in species color
   * @param g the new value of blue component
   */
  public void setG(float g) {
    if (g > 255) {
      g = 255;
    }
    this.g = g;
  }

  /**
   * Get the value of blue component in species color.
   * @return Value of blue component
   */
  public float getB() {
    return b;
  }

  /**
   * Set the value of blue component in species color
   * @param b the new value of blue component
     */
  public void setB(float b) {
    if (b > 255) {
      b = 255;
    }
    this.b = b;
  }
}
