package com.volterra.Engine.visualeffects;

import com.volterra.Engine.Simulation;
import processing.core.PApplet;

import java.time.Instant;

/**
 * Created by Koroeskohr on 02/05/2016.
 */
public class DamageEffect extends VisualEffect {
  private final int lifeSpan = 1000;

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
    System.out.println(Instant.now());
    System.out.println(this.creationTime);
    System.out.println(this.creationTime.plusMillis(this.lifeSpan));

    return Instant.now().isAfter(this.creationTime.plusMillis(this.lifeSpan));
  }

  public void update(float deltaTime){
    if(this.isOver()){
      Simulation.getSimulation().getEffectsDisplayer().remove(this);
      System.out.println("remove");
    }
  }

  public void render(PApplet ctx){
    ctx.fill(255);
    ctx.text("-" + damageValue, x, y);
  }
}
