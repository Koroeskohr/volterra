package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.Tribe;
import com.volterra.ecosysteme.species.Species;

/**
 * Created by YellowFish on 05/05/2016.
 */
public class Slow<T extends Species> extends Trait<T> {
  /**
   * Constructor
   * @param originalTribe The <i>Tribe</i> on which apply the trait.
   */
  public Slow(Tribe<T> originalTribe) {
    super(originalTribe);
  }

  /**
   * @see Trait#getSpeed()
   * @return Speed with computed decorators
   */
  @Override
  public float getSpeed() {
    return this._originalTribe.getSpeed() - 0.2f;
  }

  /**
   * @see Trait#getAttackSpeed()
   * @return Attack speed with computed decorators
   */
  @Override
  public float getAttackSpeed() {
    return this._originalTribe.getAttackSpeed() - 0.2f;
  }
}
