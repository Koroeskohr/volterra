package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.Tribe;
import com.volterra.ecosysteme.species.Species;

/**
 * Created by YellowFish on 05/05/2016.
 */
public class Coward<T extends Species> extends Trait<T> {
    /**
     * Constructor
     * @param originalTribe The <i>Tribe</i> on wich apply the trait.
     */
    public Coward(Tribe<T> originalTribe) {
        super(originalTribe);
    }

    @Override
    public int getAggressiveness() {
        return this._originalTribe.getAggressiveness() - 10;
    }

    @Override
    public int getCourage() {
        return this._originalTribe.getCourage() - 20;
    }

    @Override
    public int getMutualAid() { return this._originalTribe.getMutualAid() - 20; }
}
