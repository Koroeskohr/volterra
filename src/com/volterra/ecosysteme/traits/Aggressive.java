package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.species.Species;
import com.volterra.ecosysteme.Tribe;

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
        return Math.min(this._originalTribe.getAggressiveness() + 20, 100);
    }

    @Override
    public int getCourage() {
        return Math.min(this._originalTribe.getCourage() + 10, 100);
    }
}
