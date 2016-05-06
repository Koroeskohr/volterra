package com.volterra.ecosysteme;

import com.volterra.engine.Simulation;
import processing.core.PApplet;

/**
 * Created by Koroeskohr on 08/04/2016.
 */
public class Applet extends PApplet {
  /**
   * Frames since the start of the app
   */
  private int time = 0;

  /**
   * Simulation
   */
  private Simulation simulation;

  public void settings() {
    //fullScreen();
    simulation = Simulation.initialize(15);
    size(Simulation.getSimulation().getWindowWidth(), Simulation.getSimulation().getWindowHeight());
  }

  public void setup(){
    background(125);
    smooth();
  }

  public void draw(){
    time++;
    clear();

    if (time >= 5) simulation.runAi(time);
    simulation.update(time);
    simulation.render(this);

  }


}
