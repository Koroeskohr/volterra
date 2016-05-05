package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.Species;
import com.volterra.ecosysteme.Tribe;
import com.volterra.engine.Simulation;
import com.volterra.engine.visualeffects.BirthEffect;
import processing.core.PApplet;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
        super(originalTribe.getSpecies());
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
    public void setX(float x) {
        _originalTribe.setX(x);
    }

    @Override
    public void setY(float y) {
        _originalTribe.setY(y);
    }

    @Override
    public float getYd() {
        return _originalTribe.getYd();
    }

    @Override
    public float getXd() {
        return _originalTribe.getXd();
    }

    @Override
    public void setXd(float xd) {
        _originalTribe.setXd(xd);
    }

    @Override
    public void setYd(float yd) {
        _originalTribe.setYd(yd);
    }

    @Override
    public Color getColor() { return _originalTribe.getColor(); }

    @Override
    public Tribe getTarget() {
        return _originalTribe.getTarget();
    }

    @Override
    public void setTarget(Tribe target) {
        _originalTribe.setTarget(target);
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
    public Species[] getFriendlySpecies() {
        return this._originalTribe.getFriendlySpecies();
    }

    @Override
    public void setFriendlySpecies(Species[] friendlySpecies) {
        this._originalTribe.setFriendlySpecies(friendlySpecies);
    }

    @Override
    public float getSpeed() {
        return this._originalTribe.getSpeed();
    }

    @Override
    public void addSpeed(float speed) {
        this._originalTribe.addSpeed(speed);
    }

    @Override
    public int getAggressiveness() {
        return this._originalTribe.getAggressiveness();
    }

    @Override
    public void addAggressiveness(int aggressiveness) {
        this._originalTribe.addAggressiveness(aggressiveness);
    }

    @Override
    public int getForce() {
        return this._originalTribe.getForce();
    }

    @Override
    public void addForce(int force) {
        this._originalTribe.addForce(force);
    }

    @Override
    public int getReproductivity() {
        return this._originalTribe.getReproductivity();
    }

    @Override
    public void addReproductivity(int reproductivity) {
        this._originalTribe.addReproductivity(reproductivity);
    }

    @Override
    public int getMutualAid() {
        return this._originalTribe.getMutualAid();
    }

    @Override
    public void addMutualAid(int mutualAid) {
        this._originalTribe.addMutualAid(mutualAid);
    }

    @Override
    public int getCourage() {
        return this._originalTribe.getCourage();
    }

    @Override
    public void addCourage(int courage) {
        this._originalTribe.addCourage(courage);
    }

    @Override
    public int getLitterSize() {
        return this._originalTribe.getLitterSize();
    }

    @Override
    public void addLitterSize(int litterSize) {
        this._originalTribe.addLitterSize(litterSize);
    }

    @Override
    public int getAverageLifeSpan() {
        return this._originalTribe.getAverageLifeSpan();
    }

    @Override
    public void addAverageLifeSpan(int averageLifeSpan) {
        this._originalTribe.addAverageLifeSpan(averageLifeSpan);
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
    public boolean isInAggressionRange() {
        return _originalTribe.isInAggressionRange();
    }

    @Override
    public void aggress(Tribe enemy) {
        _originalTribe.aggress(enemy);
    }

    @Override
    public void attack(int damages) {
        _originalTribe.attack(damages);
    }

    @Override
    public void getDamages(int i) { _originalTribe.getDamages(i); }

    @Override
    public boolean isAlive() { return _originalTribe.isAlive(); }

    @Override
    public void addMember(T newMember) {
        _originalTribe.addMember(newMember);
    }

    @Override
    public void newMembers(int litterSize) {
        for(int i = 0 ; i < litterSize ; i++) {
            try {
                if (_originalTribe.getSpecies() == null) throw new NullPointerException("node must have children");
                Constructor ctor = _originalTribe.getSpecies().getConstructor();
                _originalTribe.addMember(((_originalTribe.getSpecies().cast(ctor.newInstance()))));
            } catch (NoSuchMethodException |
                    InvocationTargetException |
                    IllegalAccessException |
                    InstantiationException e) {
                e.printStackTrace();
            }
        }
        if (litterSize > 0) Simulation.getSimulation().getEffectsDisplayer().add(new BirthEffect(litterSize, this.getX(), this.getY()));
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
        _originalTribe.render(ctx);
    }

}
