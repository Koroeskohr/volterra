package com.volterra.ecosysteme;

/**
 * Created by Christophe on 25/04/2016.
 */
public abstract class Trait extends AbstractTribe {
    /* La Tribe à laquelle on ajoute le décorateur */
    protected AbstractTribe _originalTribe;

    /* La fonction qui réalise l'action à modifier en fonction de la classe AbstractTribe */
    public void computeAction() {

    }
}
