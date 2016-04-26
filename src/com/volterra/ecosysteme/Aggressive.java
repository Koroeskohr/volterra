package com.volterra.ecosysteme;

/**
 * Created by Christophe on 25/04/2016.
 *
 * Add the <i>Aggressive</i> trait to a <i>Tribe</i>.
 */
public class Aggressive<T extends Species> extends Trait<T> {
    /**
     * Constructor
     * @param originalTribe The <i>Tribe</i> on wich apply the trait.
     */
    public Aggressive(Tribe<T> originalTribe) {
        super(originalTribe);
    }

    @Override
    public int getAggressiveness() {
        return this._originalTribe.getAggressiveness() + 10;
    }

    @Override
    public int getForce() {
        return this._originalTribe.getForce() + 10;
    }

    @Override
    public int getMutualAid() {
        return this._originalTribe.getMutualAid() + 5;
    }

    @Override
    public int getCourage() {
        return this._originalTribe.getCourage() + 5;
    }
}