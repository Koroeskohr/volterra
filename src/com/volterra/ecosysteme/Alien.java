package com.volterra.ecosysteme;

/**
 * Created by YellowFish on 28/04/2016.
 */
public class Alien extends Species {
    /**
     * Default constructor. Calls Species default constructor
     */
    private static final String[] speciesTraits = { "Aggressive"};

    /**
     * Returns the array of class names as Strings that represents all the base traits for Human
     * @return Array of class names as Strings
     */
    public static String[] getSpeciesTraits() {
        return speciesTraits;
    }


    public Alien() {
        // super(friendlySpecies, averageLifeSpan, litterSize, aggressiveness, force, reproductivity, mutualAid, courage, speed);
        super(null, 0, 0, 30, 10, 0, 0, 50, 1.2f);
    }
}
