package com.volterra.ecosysteme;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Christophe on 26/04/2016.
 *
 * Represent a concrete <i>Tribe</i> class
 */
public class TribeWithTraits<T extends Species> extends Tribe<T> {

  /**
   * Constructor
   * @param x Position on the map
   * @param y Position on the map
   * @param state The state of the tribe (<i>Neutre</i> | <i>Move</i> | <i>Aggression</i> | <i>Reproduce</i>)
   * @param members An <i>ArrayList</i> containing the individuals that the <i>Tribe</i> contains
   * @param target The <i>Tribe</i> that the current <i>Tribe</i> will attack
   */
  public TribeWithTraits(float x, float y, State state, ArrayList<T> members, Tribe target) {
      this.x = x;
      this.y = y;
      this.state = state;
      this.members = members;
      this.target = target;

      setTribeAttributes();
  }

  public TribeWithTraits(ArrayList<T> members, float x, float y) {
    this.x = x;
    this.y = y;
    this.state = State.NEUTRAL;
    this.members = members;
    this.target = null;

    setTribeAttributes();
  }

    private void setTribeAttributes() {
        this.friendlySpecies = members.get(0).getFriendlySpecies();
        this.averageLifeSpan = members.get(0).getAverageLifeSpan();
        this.litterSize = members.get(0).getLitterSize();
        this.aggressiveness = members.get(0).getAggressiveness();
        this.force = members.get(0).getForce();
        this.reproductivity = members.get(0).getReproductivity();
        this.mutualAid = members.get(0).getMutualAid();
        this.courage = members.get(0).getCourage();
        this.speed = members.get(0).getSpeed();
        this.color = members.get(0).getColor();
    }

}
