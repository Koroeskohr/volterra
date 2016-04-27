package com.volterra.ecosysteme;

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
        // super(friendlySpecies, averageLifeSpan, litterSize, aggressiveness, force, reproductivity, mutualAid, courage, speed);
        super(null, 0, 0, 0, 0, 0, 0, 0, 0);
    }
}
