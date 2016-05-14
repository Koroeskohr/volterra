package com.volterra.engine.visualeffects;

import com.volterra.engine.Simulation;
import com.volterra.ecosysteme.utils.Utils;
import processing.core.PApplet;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Created by YellowFish on 05/05/2016.
 */
public class BirthEffect extends VisualEffect {
  /**
   * Amount of milliseconds the effect will stay on the screen
   */
  private final int lifeSpan = 500;

  /**
   * Amount of members born in the tribe, displayed on the screen.
   */
  private int birthValue;

  /**
   * Horizontal coordinate
   */
  private float x;

  /**
   * Vertical coordinate
   */
  private float y;

  /**
   * Moment when the effect was created
   */
  private Instant creationTime;

  /**
   * Constructor
   * @param birthValue Amount of milliseconds the effect will stay on the screen
   * @param x Horizontal coordinate
   * @param y Vertical coordinate
   */
  public BirthEffect(int birthValue, float x, float y) {
    this.birthValue = birthValue;
    this.x = x;
    this.y = y;
    this.creationTime = Instant.now();
  }

  /**
   * Is the effect over
   * @return Whether the effect has disappeared or not
   */
  public boolean isOver() {
    return Instant.now().isAfter(this.creationTime.plusMillis(this.lifeSpan));
  }

  /**
   * Update of the effect every frame
   * @param deltaTime Time elapsed since last frame
   */
  public void update(float deltaTime) {
    if (this.isOver()) {
      Simulation.getSimulation().getEffectsDisplayer().remove(this);
    }
  }

  /**
   * Display of the effect every frame
   * @param ctx The context which draws the element. Here it is a PApplet.
   */
  public void render(PApplet ctx) {
    // Goes up to 500, window is 720px high
    long timeTillSpawn = this.creationTime.until(Instant.now(), ChronoUnit.MILLIS);

    ctx.fill(0, 255, 255, Utils.clamp(400 - timeTillSpawn, 0, 255));
    ctx.textSize(15);
    ctx.text("+" + birthValue, x, y - (timeTillSpawn / 18));

  }
}
