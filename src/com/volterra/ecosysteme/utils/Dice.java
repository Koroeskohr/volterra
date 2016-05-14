package com.volterra.ecosysteme.utils;

import java.util.Random;

/**
 * Created by Koroeskohr on 05/05/2016.
 */
public class Dice {
  /**
   * Instance of the singleton
   */
  static private Dice instance;

  /**
   * Random instance of the singleton
   */
  private Random random;

  /**
   * Private constructor to avoid instantiation
   */
  private Dice() {
    super();
  };

  /**
   * Singleton initialization
   * @return Dice instance
   */
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

  /**
   * Singleton instance getter
   * @return Dice singleton instance
   */
  public static Dice getInstance() {
    if (Dice.instance == null) {
      Dice.initialize();
    }
    return Dice.instance;
  }

  /**
   * Computes if a dice roll is won based on a probability range and a random number
   * @param required The minimum number required to make the roll won
   * @param max The max number on the dice
   * @return Whether the dice roll was won or not
   */
  public static boolean winRoll(int required, int max)  {
    if (required > max)
      throw new IllegalStateException("required number was higher than max possible");

    return rollDice(max) <= required;
  }

  /**
   * Overloaded function for d100.
   * @param required The minimum number required to make the roll won
   * @return Whether the dice roll was won or not
   */
  public static boolean winRoll(int required) {
    return winRoll(required, 100);
  }

  /**
   * Roll a dice by picking a random number
   * @param max The max number on the dice
   * @return A number between 1 and max
   */
  public static int rollDice(int max)  {
    return Dice.getInstance().random.nextInt(max);
  }

}
