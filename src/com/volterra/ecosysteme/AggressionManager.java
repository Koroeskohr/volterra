package com.volterra.ecosysteme;

import static com.volterra.ecosysteme.utils.Dice.rollDice;
import static com.volterra.ecosysteme.utils.Dice.winRoll;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by YellowFish on 26/04/2016.
 */
public class AggressionManager {
    /**
     * ArrayList with the current aggressions
     */
    ArrayList<Aggression> aggressions;

    /**
     * Default constructor
     */
    public AggressionManager() {
        aggressions = new ArrayList<Aggression>();
    }

    /**
     * Process all aggressions registered in the AggressionManager
     */
    public void processAgressions() {
        for (Iterator<Aggression> iterator = aggressions.iterator(); iterator.hasNext();) {
            Aggression aggression = iterator.next();

            // Remove the aggression
            if (processAggression(aggression)) {
                setIdleState(aggression);
                aggression.setAssailantTarget(null);
                aggression.setVictimTarget(null);

                iterator.remove();
            }
        }
    }

    /**
     * Create and initialize a new aggression, then add it to the aggressions list
     * @param assailant The Tribe assailant
     * @param victim The Tribe victim
     */
    public void addAggression(Tribe assailant, Tribe victim) {
        assailant.setTarget(victim);
        victim.setTarget(assailant);

        Aggression aggression = new Aggression(assailant, victim);

        initNewAggression(aggression);

        aggressions.add(aggression);
    }

    /**
     * Initialize a newly created aggression
     * Idle state if the assailant aggression test fail
     * Mutual pursuit state if the assailant aggression test and the victim aggression test pass
     * Fleeing pursuit state if the assailant aggression test pass but the victim aggression test fail
     * @param aggression The aggression to process
     */
    private void initNewAggression(Aggression aggression) {
        setIdleState(aggression);

        if (aggressionTest(aggression.getAssailant())) {
            if (courageTest(aggression.getVictim())) {
                setPursuitMutualState(aggression);
            }
            else {
                setPursuitFleeingState(aggression);
            }
        }
    }

    private boolean aggressionTest(Tribe tribe) {
        return winRoll(tribe.getAggressiveness(), 100);

    }

    private boolean courageTest(Tribe tribe) {
        return winRoll(tribe.getCourage(), 100);
    }

    /**
     * Process action depending on the aggression current state
     * @param aggression The aggression to process
     * @return true if the aggression is to be removed
     */
    private boolean processAggression(Aggression aggression) {
        switch (aggression.getState()) {
            case IDLE:
                return processIdle(aggression);
            case PURSUIT:
                return processPursuit(aggression);
            case FIGHT:
                return processFight(aggression);
            default:
                return false;
        }
    }

    /**
     * Set the given aggression to the IDLE state
     * @param aggression
     */
    private void setIdleState(Aggression aggression) {
        aggression.setIdleState();
    }

    /**
     * Set the given aggression to the PURSUIT state with the victim state to FLEEING
     * @param aggression
     */
    private void setPursuitFleeingState(Aggression aggression) {
        aggression.setPursuitFleeingState();
    }

    /**
     * Set the given aggression to the PURSUIT state with both the assailant and the victim states to AGGRESSING
     * @param aggression
     */
    private void setPursuitMutualState(Aggression aggression) {
        aggression.setPursuitMutualState();
    }

    /**
     * Set the given aggression to the FIGHT state
     * @param aggression
     */
    private void setFightState(Aggression aggression) {
        aggression.setFightState();
    }

    /**
     * Check the duration of the given aggression
     * @param aggression
     * @return true if the time ellapsed since the begining of the aggression is superior to aggression.aggressionDurationLimit
     */
    private boolean processIdle(Aggression aggression) {
        return (Duration.between(Instant.now(), aggression.getActionStart().plusSeconds(aggression.getAggressionDurationLimit())).getSeconds() <= 0);
    }

    /**
     * Check if the two Tribes are in attack range
     * @param aggression
     * @return false, this method does not end an aggression
     */
    private boolean processPursuit(Aggression aggression) {
        if (aggression.getAssailant().isInAttackRange() || aggression.getVictim().isInAttackRange()) {
            setFightState(aggression);
            return false;
        }
        else {
            // TODO: implement proper pursuit duration
            // For the moment, a pursuit last maxAggressionDuration/2 seconds
            return (Duration.between(Instant.now(), aggression.getActionStart().plusSeconds(aggression.getAggressionDurationLimit()/2)).getSeconds() <= 0);
        }
    }

    /**
     *
     * @param aggression
     * @return
     */
    private boolean processFight(Aggression aggression) {
        if (!aggression.getAssailant().isAlive()) {
            postFightModifications(aggression.getVictim());
            return true;
        }
        if (!aggression.getVictim().isAlive()) {
            postFightModifications(aggression.getAssailant());
            return true;
        }

        return false;
    }

    private void postFightModifications(Tribe tribe) {
        // 1/2 chance of aggressiveness up
        if (true || winRoll(1, 2)) {
            tribe.addAggressiveness(5);
        }
        // 1/2 chance of courage up
        if (true || winRoll(1, 2)) {
            tribe.addCourage(5);
        }
        // 1/10 chance of force up
        if (true || winRoll(1, 10)) {
            tribe.addForce(1);
        }
    }
}
