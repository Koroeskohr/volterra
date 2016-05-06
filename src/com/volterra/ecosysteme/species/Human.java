package com.volterra.ecosysteme.species;

import java.awt.*;

/**
 * Created by YellowFish on 25/04/2016.
 */
public class Human extends Species {
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
