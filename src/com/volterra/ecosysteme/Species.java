package com.volterra.ecosysteme;

/**
 * Created by YellowFish on 25/04/2016.
 */
public abstract class Species {
    private Species[] friendlySpecies;  // Friend with
    private int averageLifeSpan;        // Average life span of the species
    private int litterSize;             // Number of children max when giving birth
    private int aggressiveness;         // Aggressiveness of the species (Range from 1 to 100)
    private int force;                  // Force, affect damages given
    private int reproductivity;         // Chance of reproduction
    private int mutualAid;              // Chance of helping friendly species
    private int courage;                // Courage, affect actions such as aggression
    private float speed;                // Speed
}
