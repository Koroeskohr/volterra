package com.volterra.ecosysteme;

import processing.core.PApplet;

/**
 * Created by Koroeskohr on 08/04/2016.
 */
public class Applet extends PApplet {
  private int x = 0;
  public void settings() {
    //fullScreen();
  }

  public void setup(){
    background(125);
    smooth();
  }

  public void draw(){
    fill(255,0,0);
    stroke(0,255,0);
    clear();

    rect(0, 0, x++, 35);

    // for each renderable
      //update

    // for each renderable
      // render


  }


}
