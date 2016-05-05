package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.Tribe;
import com.volterra.ecosysteme.species.Species;

/**
 * Created by YellowFish on 05/05/2016.
 */
public class Light<T extends Species> extends Trait<T> {
    /**
     * Constructor
     * @param originalTribe The <i>Tribe</i> on wich apply the trait.
     */
    public Light(Tribe<T> originalTribe) {
        super(originalTribe);
    }

    @Override
    public float getSpeed() {
        return this._originalTribe.getSpeed() + 0.5f;
    }

    @Override
    public int getForce() {
        return this._originalTribe.getForce() - 1;
    }
}
