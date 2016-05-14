package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.species.Species;
import com.volterra.ecosysteme.Tribe;
import com.volterra.ecosysteme.utils.Utils;

/**
 * Created by Christophe on 25/04/2016.
 *
 * Add the <i>Aggressive</i> trait to a <i>Tribe</i>.
 */
public class Aggressive<T extends Species> extends Trait<T> {
  /**
   * Constructor
   * @param originalTribe The <i>Tribe</i> on which apply the trait.
   */
  public Aggressive(Tribe<T> originalTribe) {
    super(originalTribe);
  }

  /**
   * @see Trait#getAggressiveness()
   * @return Aggressiveness with computed decorators
   */
  @Override
  public int getAggressiveness() {
    return Utils.clamp(this._originalTribe.getAggressiveness() + 20, 1, 100);
  }

  /**
   * @see Trait#getCourage()
   * @return Courage with computed decorators
   */
  @Override
  public int getCourage() {
    return Utils.clamp(this._originalTribe.getCourage() + 10, 1, 100);
  }

  /**
   * @see Trait#getMutualAid() ()
   * @return Mutual aid with computed decorators
   */
  @Override
  public int getMutualAid() {
    return Utils.clamp(this._originalTribe.getMutualAid() + 20, 1, 100);
  }
}
