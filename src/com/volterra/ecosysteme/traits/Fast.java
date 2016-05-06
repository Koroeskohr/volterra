package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.species.Species;
import com.volterra.ecosysteme.Tribe;

/**
 * Created by Christophe on 25/04/2016.
 *
 * Add the <i>Fast</i> trait to a <i>Tribe</i>.
 */
public class Fast<T extends Species> extends Trait<T> {
  /**
   * Constructor
   * @param originalTribe The <i>Tribe</i> on which apply the trait.
   */
  public Fast(Tribe<T> originalTribe) {
    super(originalTribe);
  }

  /**
   * @see Trait#getSpeed()
   * @return Speed with computed decorators
   */
  @Override
  public float getSpeed() {
    return this._originalTribe.getSpeed() + 0.2f;
  }

  /**
   * @see Trait#getAttackSpeed()
   * @return Attack speed with computed decorators
   */
  @Override
  public float getAttackSpeed() {
    return this._originalTribe.getAttackSpeed() + 0.2f;
  }
}
