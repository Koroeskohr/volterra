package com.volterra.ecosysteme;

import java.awt.*;

/**
 * Created by YellowFish on 25/04/2016.
 */
public class Human extends Species {
    /**
     * Default constructor. Calls Species default constructor
     */
    private static final String[] speciesTraits = { "Fugitive", "Fertile" };

    /**
     * Returns the array of class names as Strings that represents all the base traits for Human
     * @return Array of class names as Strings
     */
    public static String[] getSpeciesTraits() {
        return speciesTraits;
    }


    public Human() {
        // super(friendlySpecies, averageLifeSpan, litterSize, aggressiveness, force, reproductivity, mutualAid, courage, speed, color);
        super(null, 0, 2, 30, 1, 50, 0, 50, 1, new Color(0,100,200));
    }
}
