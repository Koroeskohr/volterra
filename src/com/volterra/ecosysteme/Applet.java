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
  public void settings() {
    //fullScreen();
    size(1000, 500);
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
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);
    members.add(Theo);

    TribeWithTraits<Human> myTribe = new TribeWithTraits<>(750, 250, AIStateMachine.State.NEUTRAL, members, null);

    Tribe<Human> myTribe2 = new Fugitive<>(new Aggressive<>(new TribeWithTraits<>(140, 220, AIStateMachine.State.NEUTRAL, members, null)));

    ArrayList<Tribe> tribes = new ArrayList<>();
    tribes.add(myTribe);
    tribes.add(myTribe2);

    Simulation simulation = Simulation.initialize(3);

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
