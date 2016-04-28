package com.volterra.ecosysteme;

/**
 * Created by YellowFish on 25/04/2016.
 */
public class Human extends Species {
    /**
     * Default constructor. Calls Species default constructor
     */
    public Human() {
        // super(friendlySpecies, averageLifeSpan, litterSize, aggressiveness, force, reproductivity, mutualAid, courage, speed);
        super(null, 0, 0, 30, 10, 0, 0, 50, 0);
    }
}
