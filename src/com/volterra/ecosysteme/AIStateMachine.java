package com.volterra.ecosysteme;

/**
 * Created by Victor on 25/04/2016.
 */
public interface AIStateMachine {
  enum State { NEUTRAL, MIGRATING, FLEEING, AGGRESSING, FIGHT };

  void runAI(float deltaTime);
}
