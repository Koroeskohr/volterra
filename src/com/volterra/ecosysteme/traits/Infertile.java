package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.Tribe;
import com.volterra.ecosysteme.species.Species;

/**
 * Created by YellowFish on 05/05/2016.
 */
public class Infertile<T extends Species> extends Trait<T> {
    /**
     * Constructor
     * @param originalTribe The <i>Tribe</i> on wich apply the trait.
     */
    public Infertile(Tribe<T> originalTribe) {
        super(originalTribe);
    }

    @Override
    public int getReproductivity() {
        return this._originalTribe.getReproductivity() - 10;
    }

    @Override
    public int getLitterSize() {
        return this._originalTribe.getLitterSize() - 1;
    }
}
