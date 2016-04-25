package com.volterra.ecosysteme;

/**
 * Created by Christophe on 25/04/2016.
 *
 * Add the <i>Fertile</i> trait to a <i>Tribe</i>.
 */
public class Fertile<T extends Species> extends Trait<T> {
    /**
     * Constructor
     * @param originalTribe The <i>Tribe</i> on wich apply the trait.
     */
    public Fertile(Tribe<T> originalTribe) {
        super(originalTribe);
    }

    @Override
    public int getReproductivity() {
        return this._originalTribe.getReproductivity() + 10;
    }

    @Override
    public int getLitterSize() {
        return this._originalTribe.getLitterSize() + 1;
    }

}
