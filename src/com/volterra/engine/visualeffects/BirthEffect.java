package com.volterra.engine.visualeffects;

import com.volterra.engine.Simulation;
import com.volterra.ecosysteme.utils.Utils;
import processing.core.PApplet;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Created by YellowFish on 05/05/2016.
 */
public class BirthEffect extends VisualEffect {
    private final int lifeSpan = 500;

    private int birthValue;
    private float x;
    private float y;
    private Instant creationTime;

    public BirthEffect(int birthValue, float x, float y) {
        this.birthValue = birthValue;
        this.x = x;
        this.y = y;
        this.creationTime = Instant.now();
    }

    public boolean isOver(){
        return Instant.now().isAfter(this.creationTime.plusMillis(this.lifeSpan));
    }

    public void update(float deltaTime){
        if(this.isOver()){
            Simulation.getSimulation().getEffectsDisplayer().remove(this);
        }
    }

    public void render(PApplet ctx){
        // Goes up to 500, window is 720px high
        long timeTillSpawn = this.creationTime.until(Instant.now(), ChronoUnit.MILLIS);

        ctx.fill(0, 255, 255, Utils.clamp(400 - timeTillSpawn, 0, 255));
        ctx.textSize(15);
        ctx.text("+" + birthValue, x, y - (timeTillSpawn / 18));

    }
}
