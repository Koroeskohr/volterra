package com.volterra.ecosysteme.utils;

import java.util.Random;

/**
 * Created by Koroeskohr on 05/05/2016.
 */
public class Dice {
  static private Dice instance;
  private Random random;

  private Dice() {
    super();
  };

  public static Dice initialize() {
    if (Dice.instance == null) {
      synchronized(Dice.class) {
        if (Dice.instance == null) {
          Dice.instance = new Dice();
          Dice.instance.random = new Random();
        }
      }
    }
    return Dice.instance;
  }

  public static Dice getInstance() {
    if (Dice.instance != null) {
      return Dice.instance;
    }
    return null;
  }

  public static boolean winRoll(int required, int max)  {
    if (required > max)
      throw new IllegalStateException("required number was higher than max possible");

    return rollDice(max) <= required;
  }

  public static boolean winRoll(int required) {
    return winRoll(required, 100);
  }

  public static int rollDice(int max)  {
    return Dice.instance.random.nextInt(max);
  }



}
