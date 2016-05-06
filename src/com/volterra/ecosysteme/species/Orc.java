package com.volterra.ecosysteme.species;

import java.awt.*;

/**
 * Created by YellowFish on 28/04/2016.
 */
public class Orc extends Species {
    public Orc() {
        super(null,     // friendlySpecies
                0,      // averageLifeSpan
                2,      // litterSize
                80,     // aggressiveness
                3,      // force
                40,     // reproductivity
                80,      // mutualAid
                65,     // courage
                1.2f,   // speed
                1.2f,   // attackSpeed
                new Color(100,100,110));
    }
}
