package com.volterra.ecosysteme;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

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
                System.out.println(aggression.getAssailant().hashCode() + " stop " + aggression.getVictim().hashCode());
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

        if (aggression.getAssailant().aggressionTest()) {
            System.out.println("Assailant aggression test OK");
            if (aggression.getVictim().aggressionTest()) {
                System.out.println("Mutual aggression");
                setPursuitMutualState(aggression);
            }
            else {
                System.out.println("Fleeing");
                setPursuitFleeingState(aggression);
            }
        }
        else {
            System.out.println("Aggression test failed");
        }

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
        return (Duration.between(Instant.now(), aggression.getAggressionStart().plusSeconds(aggression.getAggressionDurationLimit())).getSeconds() <= 0);
    }

    /**
     * Check if the two Tribes are in attack range
     * @param aggression
     * @return false, this method does not end an aggression
     */
    private boolean processPursuit(Aggression aggression) {
        if (aggression.getAssailant().isInAttackRange()) {
            System.out.println("Fight start");
            setFightState(aggression);
        }

        return false;
    }

    /**
     *
     * @param aggression
     * @return
     */
    private boolean processFight(Aggression aggression) {
        /*
        TODO: implement proper ia for fight
         */
        if (Duration.between(Instant.now(), aggression.getAggressionStart().plusSeconds(aggression.getAggressionDurationLimit()/2)).getSeconds() <= 0) {
            System.out.println("Fight over");
            setIdleState(aggression);
        }
        return false;
    }


}
