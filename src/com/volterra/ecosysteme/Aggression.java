package com.volterra.ecosysteme;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by YellowFish on 26/04/2016.
 */
public class Aggression {
    /**
     * Possibles states for the aggression object
     * IDLE: Default, or if the assailant did not pass his aggression test
     * PURSUIT: If the assailant is pursuing the victim
     * FIGHT: If the two Tribes are fighting
     */
    public enum AggressionState { IDLE, PURSUIT, FIGHT }

    private AggressionState state;
    private ArrayList<Tribe> assailants;
    private ArrayList<Tribe> victims;
    /**
     * Action start time. Reset when changing state
     */
    private Instant actionStart;

    /**
     * Max duration for the PURSUIT state
     */
    private int aggressionDurationLimit = 4;

    /**
     * Aggression constructor
     * @param assailant The Tribe assailant
     * @param victim The Tribe victim
     */
    public Aggression(Tribe assailant, Tribe victim) {
        this.actionStart = null;
        this.assailants = new ArrayList<>();
        this.victims = new ArrayList<>();

        this.assailants.add(assailant);
        this.victims.add(victim);
    }

    /**
     * Return the current state of the aggression
     * @return current state
     */
    public AggressionState getState() {
        return state;
    }

    /**
     * Return the assailants
     * @return assailants
     */
    public ArrayList<Tribe> getAssailants() {
        return assailants;
    }

    /**
     * Return the first assailant
     * The first assailant is the victim's target
     * @return first assailant
     */
    public Tribe getFirstAssailant() { return assailants.get(0); }

    /**
     * Add a new tribe to the assailants
     * @param tribe
     */
    public void addAssailant(Tribe tribe) {
        tribe.setTarget(this.getFirstVictim());
        this.assailants.add(tribe);
        this.setTribeState(tribe, AIStateMachine.State.AGGRESSING);
    }

    /**
     * Remove all dead assailants from the assailants list
     */
    public void removeDeadAssailants() {
        for (Iterator<Tribe> iterator = assailants.iterator(); iterator.hasNext();) {
            Tribe assailant = iterator.next();

            if (!assailant.isAlive()) iterator.remove();
        }
    }

    /**
     * Return the victim of the aggression
     * @return victim
     */
    public ArrayList<Tribe> getVictims() {
        return victims;
    }

    /**
     * Return the first victim
     * The first victim is the assailant's target
     * @return the first victim
     */
    public Tribe getFirstVictim() { return victims.get(0); }

    /**
     * Add a tribe to the victims
     * @param tribe
     */
    public void addVictim(Tribe tribe) {
        tribe.setTarget(this.getFirstAssailant());
        this.setTribeState(tribe, AIStateMachine.State.AGGRESSING);
        this.victims.add(tribe);
    }

    /**
     * Remove all dead victims from the victims list
     */
    public void removeDeadVictims() {
        for (Iterator<Tribe> iterator = victims.iterator(); iterator.hasNext();) {
            Tribe assailant = iterator.next();

            if (!assailant.isAlive()) iterator.remove();
        }
    }

    /**
     * Return the time when the aggression was created
     * @return aggressionsStart
     */
    public Instant getActionStart() {
        return actionStart;
    }

    /**
     * Return the max duration for the current aggression
     * @return aggressionDurationLimit
     */
    public int getAggressionDurationLimit() {
        return aggressionDurationLimit;
    }

    /**
     * Set a new target for the assailants
     * @param tribe new target
     */
    public void setAssailantsTarget(Tribe tribe) {
        for (Tribe assailant : this.assailants) {
            assailant.setTarget(tribe);
        }
    }

    /**
     * Set a new target for the victims
     * @param tribe new target
     */
    public void setVictimsTarget(Tribe tribe) {
        for (Tribe victim : this.victims) {
            victim.setTarget(tribe);
        }
    }

    /**
     * Change a groupe state
     * @param group ArrayList of tribes
     * @param state New state
     */
    private void setGroupState(ArrayList<Tribe> group, AIStateMachine.State state) {
        for (Tribe tribe : group) {
            tribe.setState(state);
        }
    }

    /**
     * Set a new state for a tribe
     * @param tribe
     * @param state
     */
    public void setTribeState(Tribe tribe, AIStateMachine.State state) {
        tribe.setState(state);
    }

    /**
     * Reset all remaining assailants and victims to the MIGRATING state
     */
    public void resetTribes() {
        setGroupState(assailants, AIStateMachine.State.MIGRATING);
        setGroupState(victims, AIStateMachine.State.MIGRATING);
    }

    /**
     * Set aggression state to IDLE
     * Set assailants and victims states to NEUTRAL
     */
    public void setIdleState() {
        this.actionStart = Instant.now();
        this.state = AggressionState.IDLE;
        setGroupState(assailants, AIStateMachine.State.NEUTRAL);
        setGroupState(victims, AIStateMachine.State.NEUTRAL);
    }

    /**
     * Set aggression state to PURSUIT
     * Set assailants state to AGGRESSING
     * Set victims state to FLEEING
     */
    public void setPursuitFleeingState() {
        this.actionStart = Instant.now();
        this.state = AggressionState.PURSUIT;
        setGroupState(assailants, AIStateMachine.State.AGGRESSING);
        setGroupState(victims, AIStateMachine.State.FLEEING);
    }

    /**
     * Set aggression state to PURSUIT
     * Set assailants and victims states to AGGRESSING
     */
    public void setPursuitMutualState() {
        this.actionStart = Instant.now();
        this.state = AggressionState.PURSUIT;
        setGroupState(assailants, AIStateMachine.State.AGGRESSING);
        setGroupState(victims, AIStateMachine.State.AGGRESSING);
    }

    /**
     * Set aggression state to FIGHT
     * Set assailants and victims states to FIGHT
     */
    public void setFightState() {
        this.actionStart = Instant.now();
        this.state = AggressionState.FIGHT;
        setGroupState(assailants, AIStateMachine.State.FIGHT);
        setGroupState(victims, AIStateMachine.State.FIGHT);
    }
}
