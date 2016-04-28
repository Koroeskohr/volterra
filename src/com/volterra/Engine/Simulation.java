package com.volterra.Engine;

import com.volterra.ecosysteme.*;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Christophe on 26/04/2016.
 *
 * This class manage the different steps of a turn
 */
public class Simulation implements Renderable {
    static private Simulation instance = null;

    /**
     * The list of <i>Tribes</i> on the map.
     */
    private ArrayList<Tribe> tribes;

    /**
     * The <i>AggressionManager</i> used in the simulation to determine the current conflicts on the map.
     */
    private AggressionManager aggressionManager;

    private Simulation() {
        super();
    }

    /**
     * Initialize the simulation and all of its components (tribes, agressionManager, ...)
     * @param nbTribe Number of tribes to create in the simulation
     * @return A reference to the instance of <i>Simulation</i>
     */
    public static Simulation initialize(int nbTribe) {

        if (Simulation.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(Simulation.class) {
                if (Simulation.instance == null) {
                    Simulation.instance = new Simulation();
                    Simulation.instance.tribes = new ArrayList<>();
                    Tribe tmpTribe;
                    int indexEnum;
                    long seed = new Date().getTime();
                    Random randGenerator = new Random(seed);
                    for (int i = 0; i < nbTribe; i++) {
                        indexEnum = randGenerator.nextInt(TribeFactory.numberOfSpecies());
                        tmpTribe = TribeFactory.create(TribeFactory.getSpeciesFromInt(indexEnum));
                        Simulation.instance.tribes.add(tmpTribe);
                    }
                    Simulation.instance.aggressionManager = new AggressionManager();
                }
            }
        }
        return Simulation.instance;
    }

    /**
     * Return the <i>Simulation</i> object
     * @return A reference on the <i>Simulation</i> created before or a null pointer.
     */
    public Simulation getSimulation() {
        if (Simulation.instance != null) {
            return Simulation.instance;
        }
        return null;
    }

    public void runAi() {
        for (Tribe tribe :tribes) {
            tribe.runAI(1);
        }
    }

    public void update(float delta) {
        this.aggressionManager.processAgressions();

        // REMOVE when AggressionManager functional: test code for aggressing and fleeing movement
        if (delta % 300 == 0) {
            tribes.get(0).setTarget(tribes.get(1));
            tribes.get(0).setState(AIStateMachine.State.AGGRESSING);
            tribes.get(1).setTarget(tribes.get(0));
            tribes.get(1).setState(AIStateMachine.State.FLEEING);
        }

        for (Tribe tribe :tribes) {
            updateTribeCoordinates((int) delta, tribe);
        }
    }

    private void updateTribeCoordinates(int delta, Tribe tribe) {
        Random random = new Random();
        float xd = tribe.getXd();
        float yd = tribe.getYd();
        float x = tribe.getX();
        float y = tribe.getY();
        AIStateMachine.State state = tribe.getState();

        if (state == AIStateMachine.State.NEUTRAL) {
            if (delta % (random.nextInt(30) + 30) == 0) {
                if (x >= 1000) xd = random.nextInt(2) - 1;
                else if (x <= 0) xd = random.nextInt(2);
                else xd = random.nextInt(3) - 1;

                if (y >= 500) yd = random.nextInt(2) - 1;
                else if (y <= 0) yd = random.nextInt(2);
                else yd = random.nextInt(3) - 1;

                if (random.nextInt(4) == 0) {
                    xd = 0;
                    yd = 0;
                }
            }
        }
        else if (state == AIStateMachine.State.AGGRESSING) {
            float xVictim = tribe.getTarget().getX();
            float yVictim = tribe.getTarget().getY();

            if (xVictim > x) xd = 1;
            else if (xVictim < x) xd = -1;
            else xd = 0;

            if (yVictim > y) yd = 1;
            else if (yVictim < y) yd = -1;
            else yd = 0;
        }
        else if (state == AIStateMachine.State.FLEEING) {
            float xAssailant = tribe.getTarget().getX();
            float yAssailant = tribe.getTarget().getY();

            if (xAssailant > x) xd = -1;
            else if (xAssailant < x) xd = 1;

            if (yAssailant > y) yd = -1;
            else if (yAssailant < y) yd = 1;
        }

        x += xd*tribe.getSpeed();
        y += yd*tribe.getSpeed();

        if (x > 1000) x = 1000;
        else if (x < 0) x = 0;
        if (y > 500) y = 500;
        else if (y < 0) y = 0;

        tribe.setX(x);
        tribe.setY(y);
        tribe.setXd(xd);
        tribe.setYd(yd);
    }

    /**
     * Display the tribes containing in the simulation on the screen.
     * @param ctx The PApplet which draw the <i>Renderable</i> elements.
     */
    public void render(PApplet ctx) {
        for (Tribe tribe :tribes) {
            tribe.render(ctx);
        }
        //ctx.filter(PConstants.BLUR, 2);
    }
}
