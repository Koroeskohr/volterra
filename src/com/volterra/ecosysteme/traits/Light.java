package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.Tribe;
import com.volterra.ecosysteme.species.Species;
import com.volterra.ecosysteme.utils.Utils;

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

  /**
   * @see Trait#getSpeed()
   * @return Speed with computed decorators
   */
  @Override
  public float getSpeed() {
    return this._originalTribe.getSpeed() + 0.5f;
  }

  /**
   * @see Trait#getForce()
   * @return Force with computed decorators
   */
  @Override
  public int getForce() {
    return Utils.clamp(this._originalTribe.getForce() - 1, 1, 100);
  }
}
