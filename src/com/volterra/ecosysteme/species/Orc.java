package com.volterra.ecosysteme.species;

import java.awt.*;

/**
 * Created by YellowFish on 28/04/2016.
 */
public class Orc extends Species {
    /**
     * Default constructor. Calls Species default constructor
     */
    private static final String[] speciesTraits = { "Infertile", "Aggressive", "Heavy", "Fast"};

    /**
     * Returns the array of class names as Strings that represents all the base traits for Human
     * @return Array of class names as Strings
     */
    public static String[] getSpeciesTraits() {
        return speciesTraits;
    }


    public Orc() {
        super(null,     // friendlySpecies
                0,      // averageLifeSpan
                2,      // litterSize
                80,     // aggressiveness
                6,      // force
                30,     // reproductivity
                65,      // mutualAid
                85,     // courage
                1.2f,   // speed
                1.2f,   // attackSpeed
                new Color(100,100,110));
    }
}
