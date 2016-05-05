package com.volterra.ecosysteme;

import static com.volterra.ecosysteme.utils.Dice.winRoll;

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

    public ArrayList<Aggression> getAggressions() {
        return this.aggressions;
    }

    /**
     * Process all aggressions registered in the AggressionManager
     */
    public void processAgressions() {
        for (Iterator<Aggression> iterator = aggressions.iterator(); iterator.hasNext();) {
            Aggression aggression = iterator.next();

            // Remove the aggression
            if (processAggression(aggression)) {
                resetTribes(aggression);
                aggression.setAssailantsTarget(null);
                aggression.setVictimsTarget(null);

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
        Aggression aggression = new Aggression(assailant, victim);
        aggression.setAssailantsTarget(victim);
        aggression.setVictimsTarget(assailant);

        initNewAggression(aggression);

        aggressions.add(aggression);
    }

    /**
     * Add a new tribe to the assailants list of the given aggression
     * @param tribe Tribe to add
     * @param aggression Aggression
     */
    public void addAssailantToAggression(Tribe tribe, Aggression aggression) {
        aggression.addAssailant(tribe);
    }

    /**
     * Add a new tribe to the victims list of the given aggression
     * @param tribe Tribe to add
     * @param aggression Aggression
     */
    public void addVictimToAggression(Tribe tribe, Aggression aggression) {
        aggression.addVictim(tribe);
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

        if (aggressionTest(aggression.getFirstAssailant())) {
            if (courageTest(aggression.getFirstVictim())) {
                setPursuitMutualState(aggression);
            }
            else {
                setPursuitFleeingState(aggression);
            }
        }
    }

    /**
     * Aggression test based on the aggressiveness level of the tribe
     * @param tribe
     * @return
     */
    private boolean aggressionTest(Tribe tribe) {
        return winRoll(tribe.getAggressiveness(), 100);
    }

    /**
     * Courage test based on the courage level of the tribe
     * @param tribe
     * @return
     */
    private boolean courageTest(Tribe tribe) {
        return winRoll(tribe.getCourage(), 100);
    }

    /**
     * Process action depending on the aggression current state
     * @param aggression The aggression to process
     * @return true if the aggression is to be removed
     */
    private boolean processAggression(Aggression aggression) {
        processSecondaryPursuits(aggression);

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
     * Reset all tribes in an aggression to MIGRATING state
     * @param aggression
     */
    private void resetTribes(Aggression aggression) {
        aggression.resetTribes();
    }

    /**
     * Set the given aggression to the IDLE state
     * @param aggression
     */
    private void setIdleState(Aggression aggression) {
        aggression.setIdleState();
    }

    /**
     * Set the given aggression to the PURSUIT state with the victims state to FLEEING
     * @param aggression
     */
    private void setPursuitFleeingState(Aggression aggression) {
        aggression.setPursuitFleeingState();
    }

    /**
     * Set the given aggression to the PURSUIT state with both the assailants and the victims states to AGGRESSING
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
     * Process all secondary assailants and victims.
     * If they are in attack range, their state is switched to FIGHT and they enter the fight
     * @param aggression
     */
    private void processSecondaryPursuits(Aggression aggression) {
        for (Tribe assailant : aggression.getAssailants()) {
            if (assailant.getState() == AIStateMachine.State.AGGRESSING && assailant.isInAttackRange()) {
                aggression.setTribeState(assailant, AIStateMachine.State.FIGHT);
            }
        }
        for (Tribe victim : aggression.getVictims()) {
            if (victim.getState() == AIStateMachine.State.AGGRESSING && victim.isInAttackRange()) {
                aggression.setTribeState(victim, AIStateMachine.State.FIGHT);
            }
        }
    }
    /**
     * Check if the two Tribes are in attack range
     * @param aggression
     * @return false, this method does not end an aggression
     */
    private boolean processPursuit(Aggression aggression) {
        if (aggression.getFirstAssailant().isInAttackRange() || aggression.getFirstVictim().isInAttackRange()) {
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
     * Check if all the assailants and victims are still alive
     * If one group is completly dead, the remaining group gain some bonus defined in <i>postFightModifictions</i>
     * @param aggression
     * @return
     */
    private boolean processFight(Aggression aggression) {
        aggression.removeDeadAssailants();
        aggression.removeDeadVictims();

        if (aggression.getAssailants().size() <= 0) {
            postFightModifications(aggression.getVictims());
            return true;
        }
        if (aggression.getVictims().size() <= 0) {
            postFightModifications(aggression.getAssailants());
            return true;
        }

        aggression.setAssailantsTarget(aggression.getFirstVictim());
        aggression.setVictimsTarget(aggression.getFirstAssailant());

        return false;
    }

    /**
     * Process bonus after a combat ends
     * @param tribes
     */
    private void postFightModifications(ArrayList<Tribe> tribes) {
        for (Tribe tribe : tribes) {
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
}
