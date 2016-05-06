package com.volterra.ecosysteme;

import processing.core.PApplet;

/**
 * Created by Victor on 25/04/2016.
 *
 * Defines necessary methods to draw an element
 */
public interface Renderable {
  /**
   * Updates the state of the element to draw.
   * @param deltaTime Time elapsed since last frame
     */
  void update(float deltaTime);

  /**
   * Draws the element on the screen
   * @param ctx The context which draws the element. Here it is a PApplet.
     */
  void render(PApplet ctx);
}
