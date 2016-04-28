package com.volterra.ecosysteme;

import java.time.Instant;

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
    enum AggressionState { IDLE, PURSUIT, FIGHT }

    private AggressionState state;
    private Tribe assailant;
    private Tribe victim;
    private Instant aggressionStart;

    /**
     * Max duration for the PURSUIT state
     */
    private int aggressionDurationLimit = 10;

    /**
     * Aggression constructor
     * @param assailant The Tribe assailant
     * @param victim The Tribe victim
     */
    public Aggression(Tribe assailant, Tribe victim) {
        this.aggressionStart = Instant.now();

        this.assailant = assailant;
        this.victim = victim;
    }

    /**
     * Return the current state of the aggression
     * @return current state
     */
    public AggressionState getState() {
        return state;
    }

    /**
     * Return the assailant
     * @return assailant
     */
    public Tribe getAssailant() {
        return assailant;
    }

    /**
     * Return the victim of the aggression
     * @return victim
     */
    public Tribe getVictim() {
        return victim;
    }

    /**
     * Return the time when the aggression was created
     * @return aggressionsStart
     */
    public Instant getAggressionStart() {
        return aggressionStart;
    }

    /**
     * Return the max duration for the current aggression
     * @return aggressionDurationLimit
     */
    public int getAggressionDurationLimit() {
        return aggressionDurationLimit;
    }

    /**
     * Set a new target for the assailant
     * @param tribe new target
     */
    public void setAssailantTarget(Tribe tribe) {
        this.assailant.setTarget(tribe);
    }

    /**
     * Set a new target for the victim
     * @param tribe new target
     */
    public void setVictimTarget(Tribe tribe) {
        this.victim.setTarget(tribe);
    }

    /**
     * Set aggression state to IDLE
     * Set assailant and victim states to NEUTRAL
     */
    public void setIdleState() {
        this.state = AggressionState.IDLE;
        this.assailant.setState(AIStateMachine.State.NEUTRAL);
        this.victim.setState(AIStateMachine.State.NEUTRAL);
    }

    /**
     * Set aggression state to PURSUIT
     * Set assailant state to AGGRESSING
     * Set victim state to FLEEING
     */
    public void setPursuitFleeingState() {
        this.state = AggressionState.PURSUIT;
        this.assailant.setState(AIStateMachine.State.AGGRESSING);
        this.victim.setState(AIStateMachine.State.FLEEING);
    }

    /**
     * Set aggression state to PURSUIT
     * Set assailant and victim states to AGGRESSING
     */
    public void setPursuitMutualState() {
        this.state = AggressionState.PURSUIT;
        this.assailant.setState(AIStateMachine.State.AGGRESSING);
        this.victim.setState(AIStateMachine.State.AGGRESSING);
    }

    /**
     * Set aggression state to FIGHT
     * Set assailant and victim states to FIGHT
     */
    public void setFightState() {
        this.state = AggressionState.FIGHT;
        this.assailant.setState(AIStateMachine.State.FIGHT);
        this.victim.setState(AIStateMachine.State.FIGHT);
    }
}