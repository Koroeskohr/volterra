package com.volterra.ecosysteme;

/**
 * Created by YellowFish on 25/04/2016.
 */
public class Human extends Species {
    /**
     * Default constructor. Calls Species default constructor
     */
    public Human() {
        super();
    }

    /**
     * Constructor with all the parameters
     * @param friendlySpecies
     * @param averageLifeSpan
     * @param litterSize
     * @param aggressiveness
     * @param force
     * @param reproductivity
     * @param mutualAid
     * @param courage
     * @param speed
     */
    public Human(Species[] friendlySpecies, int averageLifeSpan, int litterSize, int aggressiveness, int force, int reproductivity, int mutualAid, int courage, float speed) {
        super(friendlySpecies, averageLifeSpan, litterSize, aggressiveness, force, reproductivity, mutualAid, courage, speed);
    }
}
