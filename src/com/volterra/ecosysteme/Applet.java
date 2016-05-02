package com.volterra.ecosysteme;

import com.volterra.engine.Simulation;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by Koroeskohr on 08/04/2016.
 */
public class Applet extends PApplet {
  private int time = 0;
  private Simulation simulation;

  public void settings() {
    //fullScreen();
    simulation = Simulation.initialize(10);
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
