package com.volterra.Engine;

import com.volterra.ecosysteme.Tribe;

import java.util.ArrayList;

/**
 * Created by Christophe on 26/04/2016.
 *
 * This class manage the different steps of a turn
 */
public class Simulation {
    static private Simulation instance = null;

    /**
     * The list of <i>Tribes</i> on the map.
     */
    private ArrayList<Tribe> tribes;

    /**
     * The <i>AggressionManager</i> used in the simulation to determine the current conflicts on the map.
     */
    private AggressionManager agressionManager;

    private Simulation() {
        super();
    }

    /**
     * Initialize a <i>Simulation</i> object if it doesn't exist. Else, returns a reference on the <i>Simulation</i> that already exists.
     * @param tribes the list of tribes contained by the <i>Simulation</i>
     * @return A reference on the <i>Simulation</i> created or a reference on the <i>Simulation</i> that already exists
     */
    public static Simulation initialize(ArrayList<Tribe> tribes) {

        if (Simulation.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(Simulation.class) {
                if (Simulation.instance == null) {
                    Simulation.instance = new Simulation();
                    Simulation.instance.tribes = tribes;
                    Simulation.instance.agressionManager = new AgressionManager();
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

    }

    public void update() {
        for (Tribe tribe :tribes) {
            tribe.update(1);
        }
    }

    public void render() {
        for (Tribe tribe :tribes) {
            tribe.render();
        }
    }
}
