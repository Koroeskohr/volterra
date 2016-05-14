package com.volterra.ecosysteme.traits;

import com.volterra.ecosysteme.species.Species;
import com.volterra.ecosysteme.Tribe;
import com.volterra.ecosysteme.utils.Utils;

/**
 * Created by Christophe on 25/04/2016.
 *
 * Add the <i>Fertile</i> trait to a <i>Tribe</i>.
 */
public class Fertile<T extends Species> extends Trait<T> {
  /**
   * Constructor
   * @param originalTribe The <i>Tribe</i> on which apply the trait.
   */
  public Fertile(Tribe<T> originalTribe) {
    super(originalTribe);
  }

  /**
   * @see Trait#getReproductivity()
   * @return Reproductivity with computed decorators
   */
  @Override
  public int getReproductivity() {
    return Utils.clamp(this._originalTribe.getReproductivity() + 10, 1, 100);
  }

  /**
   * @see Trait#getLitterSize()
   * @return Litter size with computed decorators
   */
  @Override
  public int getLitterSize() {
    return Utils.clamp(this._originalTribe.getLitterSize() + 1, 1, 100);
  }

}
