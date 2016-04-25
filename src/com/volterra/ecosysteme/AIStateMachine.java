package com.volterra.ecosysteme;

/**
 * Created by Victor on 25/04/2016.
 */
public interface AIStateMachine {
  enum State { NEUTRAL, MIGRATING, FLEEING, AGGRESSING };

  void runAI(float deltaTime);
}
