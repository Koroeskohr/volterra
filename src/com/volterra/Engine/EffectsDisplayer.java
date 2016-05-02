package com.volterra.Engine;

import com.volterra.Engine.visualeffects.VisualEffect;
import com.volterra.ecosysteme.Renderable;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Koroeskohr on 02/05/2016.
 */
public class EffectsDisplayer implements Renderable {
  private CopyOnWriteArrayList<VisualEffect> effects;

  public EffectsDisplayer(){
    this.effects = new CopyOnWriteArrayList<>();
  }

  public void add(VisualEffect vfx){
    Objects.requireNonNull(vfx);
    effects.add(vfx);
  }

  public void remove(VisualEffect vfx){
    effects.remove(vfx);
  }

  public void update(float deltaTime){
    for(VisualEffect effect : effects) {
      effect.update(deltaTime);
    }
  };

  public void render(PApplet ctx){
    for(VisualEffect effect : effects) {
      effect.render(ctx);
    }
  }

}
