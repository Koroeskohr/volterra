package com.volterra.ecosysteme.utils;

/**
 * Created by Koroeskohr on 05/05/2016.
 */
public class Utils {
  public static float clamp(float val, float min, float max) {
    return Math.max(min, Math.min(max, val));
  }

  public static int clamp(int val, int min, int max) {
    return Math.max(min, Math.min(max, val));
  }


}
