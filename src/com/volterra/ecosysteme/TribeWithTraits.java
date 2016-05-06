package com.volterra.ecosysteme;

import com.volterra.ecosysteme.species.Species;

import java.time.Instant;
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
   * @param members An <i>ArrayList</i> containing the individuals that the <i>Tribe</i> contains
   */
  public TribeWithTraits(ArrayList<T> members, float x, float y, Class<T> species) {
    super(species);
    this.x = x;
    this.y = y;
    this.state = State.MIGRATING;
    this.members = members;
    this.target = null;

    setTribeAttributes();
  }

  /**
   * Sets all the base attributes for a tribe
   */
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
    this.attackSpeed = members.get(0).getAttackSpeed();
    this.color = members.get(0).getColor();
    this.lastBirth = Instant.now();
  }

}
