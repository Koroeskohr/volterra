package com.volterra.ecosysteme;

import com.volterra.Engine.Simulation;
import processing.core.PApplet;

import java.util.ArrayList;

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
    Human Theo = new Human();
    ArrayList<Human> members = new ArrayList<>();
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);

    TribeWithTraits<Human> myTribe = new TribeWithTraits<>(10, 10, AIStateMachine.State.NEUTRAL, members, null);

    TribeWithTraits<Human> myTribe2 = new TribeWithTraits<>(50, 10, AIStateMachine.State.NEUTRAL, members, null);

    ArrayList<Tribe> tribes = new ArrayList<>();
    tribes.add(myTribe);
    tribes.add(myTribe2);

    Simulation simulation = Simulation.initialize(tribes);

    fill(255,0,0);
    stroke(0,255,0);
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
