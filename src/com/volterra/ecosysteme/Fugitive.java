package com.volterra.ecosysteme;

/**
 * Created by Christophe on 25/04/2016.
 *
 * Add the <i>Fugitive</i> trait to a <i>Tribe</i>.
 */
public class Fugitive<T extends Species> extends Trait<T> {
    /**
     * Constructor
     * @param originalTribe The <i>Tribe</i> on wich apply the trait.
     */
    public Fugitive(Tribe<T> originalTribe) {
        super(originalTribe);
    }

    @Override
    public float getSpeed() {
        return this._originalTribe.getSpeed() + 1;
    }

    @Override
    public int getAggressiveness() {
        return this._originalTribe.getAggressiveness() - 20;
    }

    @Override
    public int getCourage() {
        return this._originalTribe.getCourage() - 20;
    }
}