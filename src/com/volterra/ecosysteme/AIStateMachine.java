package com.volterra.ecosysteme;

/**
 * Created by Victor on 25/04/2016.
 */
public interface AIStateMachine {
  /**
   * Different states of a Tribe
   */
  enum State { NEUTRAL, MIGRATING, FLEEING, AGGRESSING, FIGHT };

  /**
   * Updates AI
   * @param deltaTime Time elapsed since last frame
   */
  void runAI(float deltaTime);
}
