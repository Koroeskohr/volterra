package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.Species;
import com.volterra.ecosysteme.Tribe;
import processing.core.PApplet;

/**
 * Created by Christophe on 25/04/2016.
 *
 * Decorator to add character <i>Traits</i> that give some bonuses or penalties to a <i>Tribe</i>.
 */
public abstract class Trait<T extends Species> extends Tribe<T> {
    /**
     * The <i>Tribe</i> on which the trait is added
     */
    protected Tribe<T> _originalTribe;

    /**
     * Constructor
     * @param originalTribe The <i>Tribe</i> on wich apply the trait.
     */
    Trait(Tribe<T> originalTribe) {
        this._originalTribe = originalTribe;
    }

    @Override
    public float getY() {
        return _originalTribe.getY();
    }

    @Override
    public float getX() {
        return _originalTribe.getX();
    }

    @Override
    public Tribe getTarget() {
        return _originalTribe.getTarget();
    }

    @Override
    public State getState() {
        return _originalTribe.getState();
    }

    @Override
    public void setState(State state) {
        _originalTribe.setState(state);
    }

    @Override
    public int size() {
        return _originalTribe.size();
    }

    @Override
    public int radius() {
        return _originalTribe.radius();
    }

    @Override
    public double getDistanceToTarget() {
        return _originalTribe.getDistanceToTarget();
    }

    @Override
    public boolean isBusy() {
        return _originalTribe.isBusy();
    }

    @Override
    public boolean isInAttackRange() {
        return _originalTribe.isInAttackRange();
    }

    @Override
    public void aggress(Tribe enemy) {
        _originalTribe.aggress(enemy);
    }

    @Override
    public void attack(Tribe enemy) {
        _originalTribe.attack(enemy);
    }

    @Override
    public void runAI(float deltaTime) {
        _originalTribe.runAI(deltaTime);
    }

    @Override
    public void update(float deltaTime) {
        _originalTribe.update(deltaTime);
    }

    @Override
    public void render(PApplet ctx) {
        ctx.fill(255,255,255);
        ctx.stroke(this.getAggressiveness() * 2,this.getCourage() * 2,this.getForce() * 2);
        ctx.strokeWeight(this.size() * 0.4f);
        ctx.ellipse(_originalTribe.getX(), _originalTribe.getY(), this.size(), this.size());
    }

    @Override
    public float getSpeed() {
        return this._originalTribe.getSpeed();
    }

    @Override
    public int getAggressiveness() {
        return this._originalTribe.getAggressiveness();
    }

    @Override
    public int getForce() {
        return this._originalTribe.getForce();
    }

    @Override
    public int getReproductivity() {
        return this._originalTribe.getReproductivity();
    }

    @Override
    public int getMutualAid() {
        return this._originalTribe.getMutualAid();
    }

    @Override
    public int getCourage() {
        return this._originalTribe.getCourage();
    }

    @Override
    public int getLitterSize() {
        return this._originalTribe.getLitterSize();
    }

    @Override
    public int getAverageLifeSpan() {
        return this._originalTribe.getAverageLifeSpan();
    }

    @Override
    public void setTarget(Tribe target) {
        super.setTarget(target);
    }
}
