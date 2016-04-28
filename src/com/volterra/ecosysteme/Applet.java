package com.volterra.ecosysteme;

import com.volterra.Engine.Simulation;
import com.volterra.ecosysteme.traits.Aggressive;
import com.volterra.ecosysteme.traits.Fugitive;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by Koroeskohr on 08/04/2016.
 */
public class Applet extends PApplet {
  private int x = 0;
  private Simulation simulation;

  public void settings() {
    //fullScreen();
    size(1000, 500);
  }

  public void setup(){
    background(125);
    smooth();

    simulation = Simulation.initialize(3);
  }

  public void draw(){
    clear();

    simulation.render(this);


    /*fill(255,0,0);
    stroke(0,255,0);
    clear();

    rect(0, 0, x++, 35);*/

    // for each renderable
      //update

    // for each renderable
      // render


  }


}
