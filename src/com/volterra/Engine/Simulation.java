package com.volterra.Engine;

import com.volterra.ecosysteme.AggressionManager;
import com.volterra.ecosysteme.Renderable;
import com.volterra.ecosysteme.Tribe;
import com.volterra.ecosysteme.TribeFactory;
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
        for (Tribe tribe :tribes) {
            tribe.update(1);
        }
    }

    /**
     * Display the tribes containing in the simulation on the screen.
     * @param ctx The PApplet which draw the <i>Renderable</i> elements.
     */
    public void render(PApplet ctx) {
        for (Tribe tribe :tribes) {
            tribe.render(ctx);
        }
        ctx.filter(PConstants.BLUR, 2);
    }
}
