package com.volterra.ecosysteme;

/**
 * Created by Christophe on 25/04/2016.
 *
 * Decorator to add character <i>Traits</i> that give some bonuses or penalties to a <i>Tribe</i>.
 */
public abstract class Trait<T extends Species> extends Tribe<T> {
    protected Tribe<T> _originalTribe;

    /**
     * Constructor
     * @param originalTribe The <i>Tribe</i> on wich apply the trait.
     */
    Trait(Tribe<T> originalTribe) {
        this._originalTribe = originalTribe;
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
}
