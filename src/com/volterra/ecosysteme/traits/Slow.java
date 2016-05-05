package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.Tribe;
import com.volterra.ecosysteme.species.Species;

/**
 * Created by YellowFish on 05/05/2016.
 */
public class Slow<T extends Species> extends Trait<T> {
    /**
     * Constructor
     * @param originalTribe The <i>Tribe</i> on wich apply the trait.
     */
    public Slow(Tribe<T> originalTribe) {
        super(originalTribe);
    }

    @Override
    public float getSpeed() {
        return this._originalTribe.getSpeed() - 0.2f;
    }

    @Override
    public float getAttackSpeed() {
        return this._originalTribe.getAttackSpeed() - 0.2f;
    }
}
