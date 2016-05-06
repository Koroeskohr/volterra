package com.volterra.ecosysteme.species;

import java.awt.*;

/**
 * Created by YellowFish on 25/04/2016.
 */
public class Human extends Species {
  /**
   * Species specific traits. Will be picked at random.
   */
  private static final String[] speciesTraits = { "Fertile", "Coward", "Heavy", "Fast" };

  /**
   * Returns the array of class names as Strings that represents all the base traits for Human
   * @return Array of class names as Strings
   */
  public static String[] getSpeciesTraits() {
      return speciesTraits;
  }

  /**
   * Builds an Human instance with its species base characteristics
   */
  public Human() {
    super(null,     // friendlySpecies
            0,      // averageLifeSpan
            2,      // litterSize
            50,     // aggressiveness
            2,      // force
            50,     // reproductivity
            20,     // mutualAid
            30,     // courage
            1.0f,   // speed
            1.0f,   // attackSpeed
            new Color(0,100,200));
  }
}
