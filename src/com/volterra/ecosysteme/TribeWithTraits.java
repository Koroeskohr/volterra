package com.volterra.ecosysteme;

import java.util.ArrayList;

/**
 * Created by Christophe on 26/04/2016.
 *
 * Represent a concrete <i>Tribe</i> class
 */
public class TribeWithTraits<T extends Species> extends Tribe<T> {


  public TribeWithTraits(float x, float y, State state, ArrayList<T> members, Tribe target) {
    this.x = x;
    this.y = y;
    this.state = state;
    this.members = members;
    this.target = target;
  }

  //TEST
  public TribeWithTraits(ArrayList<T> members) {
    this.x = 1.0f;
    this.y = 1.0f;
    this.state = State.NEUTRAL;
    this.members = members;
    this.target = null;
  }

}
