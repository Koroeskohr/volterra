package com.volterra.ecosysteme.species;

import java.awt.*;

/**
 * Created by YellowFish on 28/04/2016.
 */
public class Alien extends Species {
    /**
     * Default constructor. Calls Species default constructor
     */
    private static final String[] speciesTraits = { "Infertile", "Coward", "Light", "Fast"};

    /**
     * Returns the array of class names as Strings that represents all the base traits for Human
     * @return Array of class names as Strings
     */
    public static String[] getSpeciesTraits() {
        return speciesTraits;
    }


    public Alien() {
        super(null,     // friendlySpecies
                0,      // averageLifeSpan
                2,      // litterSize
                15,     // aggressiveness
                6,      // force
                30,     // reproductivity
                60,      // mutualAid
                10,     // courage
                1.2f,   // speed
                1.0f,   // attackSpeed
                new Color(0,200,50));
    }
}
