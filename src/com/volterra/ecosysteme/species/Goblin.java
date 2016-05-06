package com.volterra.ecosysteme.species;

import java.awt.*;

/**
 * Created by YellowFish on 05/05/2016.
 */
public class Goblin extends Species {
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
   * Builds an Goblin instance with its species base characteristics
   */
  public Goblin() {
      super(null,     // friendlySpecies
              0,      // averageLifeSpan
              4,      // litterSize
              70,     // aggressiveness
              1,      // force
              35,     // reproductivity
              30,      // mutualAid
              45,     // courage
              0.9f,   // speed
              1.1f,   // attackSpeed
              new Color(150,50,50));
  }
}
