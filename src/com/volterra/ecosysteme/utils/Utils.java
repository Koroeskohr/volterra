package com.volterra.ecosysteme.utils;

/**
 * Created by Koroeskohr on 05/05/2016.
 */
public class Utils {
  /**
   * Clamps a value
   * @param val Value to clamp
   * @param min Minimum value
   * @param max Maximum value
   * @return The clamped number
   */
  public static float clamp(float val, float min, float max) {
    return Math.max(min, Math.min(max, val));
  }

  /**
   * Clamps a value
   * @param val Value to clamp
   * @param min Minimum value
   * @param max Maximum value
   * @return The clamped number
   */
  public static int clamp(int val, int min, int max) {
    return Math.max(min, Math.min(max, val));
  }


}
