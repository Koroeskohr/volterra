package com.volterra.ecosysteme.species;

import java.awt.*;

/**
 * Created by YellowFish on 05/05/2016.
 */
public class Goblin extends Species {
 /**
   * Builds an Elf instance with its species base characteristics
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
