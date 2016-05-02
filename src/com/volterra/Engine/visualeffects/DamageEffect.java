package com.volterra.Engine.visualeffects;

import com.volterra.Engine.Simulation;
import processing.core.PApplet;

import java.time.Instant;

/**
 * Created by Koroeskohr on 02/05/2016.
 */
public class DamageEffect extends VisualEffect {
  private final int lifeSpan = 500;

  private int damageValue;
  private float x;
  private float y;
  private Instant creationTime;

  public DamageEffect(int damageValue, float x, float y) {
    this.damageValue = damageValue;
    this.x = x;
    this.y = y;
    this.creationTime = Instant.now();
  }

  public boolean isOver(){
    return Instant.now().isAfter(this.creationTime.plusMillis(this.lifeSpan));
  }

  public void update(float deltaTime){
    if(this.isOver()){
      Simulation.getSimulation().getEffectsDisplayer().remove(this);
    }
  }

  public void render(PApplet ctx){
    ctx.fill(255, 100, 100);
    ctx.text("-" + damageValue, x, y);
  }
}
