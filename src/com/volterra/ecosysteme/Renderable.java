package com.volterra.ecosysteme;

import processing.core.PApplet;

/**
 * Created by Victor on 25/04/2016.
 *
 * Define necessary methods to draw an element
 */
public interface Renderable {
  /**
   * Update the state of the element to draw.
   * @param deltaTime
     */
  void update(float deltaTime);

  /**
   * Draw the element on the screen
   * @param ctx The context which draws the element. Here it is a PApplet.
     */
  void render(PApplet ctx);
}
