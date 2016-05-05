package com.volterra.ecosysteme.species;

import java.awt.*;

/**
 * Created by YellowFish on 05/05/2016.
 */
public class Elf extends Species {
    /**
     * Default constructor. Calls Species default constructor
     */
    private static final String[] speciesTraits = { "Infertile", "Aggressive", "Light", "Fast" };

    /**
     * Returns the array of class names as Strings that represents all the base traits for Human
     * @return Array of class names as Strings
     */
    public static String[] getSpeciesTraits() {
        return speciesTraits;
    }

    public Elf() {
        super(null,     // friendlySpecies
                0,      // averageLifeSpan
                2,      // litterSize
                20,     // aggressiveness
                3,      // force
                15,     // reproductivity
                0,      // mutualAid
                75,     // courage
                1.3f,   // speed
                1.4f,   // attackSpeed
                new Color(100,200,150));
    }
}