package com.volterra.ecosysteme;

/**
 * Created by Christophe on 25/04/2016.
 *
 * Décorateur pour ajouter un trait de personnalité aux tribus.
 */
public abstract class Trait extends Tribe {
    /**
     * La tribu à laquelle on ajoute le décorateur
     */
    protected Tribe _originalTribe;

    /**
     * Distribue les bonus et les malus liés au trait
     */
    public void computeAttributes() {

    }
}
