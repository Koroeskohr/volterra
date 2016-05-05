package com.volterra.engine;

import com.volterra.ecosysteme.*;
import com.volterra.ecosysteme.utils.Dice;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Christophe on 26/04/2016.
 *
 * This class manage the different steps of a turn
 */
public class Simulation implements Renderable {
    static private Simulation instance = null;

  /**
   * Instance of the effectsDisplayer;
   */
  private EffectsDisplayer effectsDisplayer;

    /**
     * The list of <i>Tribes</i> on the map.
     */
    private ArrayList<Tribe> tribes;

    /**
     * The <i>AggressionManager</i> used in the simulation to determine the current conflicts on the map.
     */
    private AggressionManager aggressionManager;


    private final int windowWidth = 1366;
    private final int windowHeight = 720;
    private final int framerate = 60;

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

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
                    Simulation.instance.effectsDisplayer = new EffectsDisplayer();
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
    public static Simulation getSimulation() {
        if (Simulation.instance != null) {
            return Simulation.instance;
        }
        return null;
    }

    public EffectsDisplayer getEffectsDisplayer(){
        return this.effectsDisplayer;
    }

    public void removeDeadTribes() {
        int nb = tribes.size();
        for (Iterator<Tribe> iterator = tribes.iterator(); iterator.hasNext();) {
            Tribe tribe = iterator.next();

            if (!tribe.isAlive()) iterator.remove();
        }

        if (nb != tribes.size()) System.out.println("Tribes left: " + this.tribes.size());
    }

    /**
     * Process Tribes AI
     */
    public void runAi(int delta) {
        for (Tribe tribe : tribes) {
            if (tribe.isAlive()) {
                if (tribe.getState() == AIStateMachine.State.NEUTRAL) {
                    processAiReproduction(tribe, delta);
                }

                if (tribe.getTarget() == null) {
                    processAiAggression(tribe);
                }
                else {
                    processAiAttack(tribe, delta);
                }
            }
        }

        this.aggressionManager.processAgressions();

        removeDeadTribes();
    }

    /**
     * Process Tribe reproduction
     * @param tribe
     * @param delta
     */
    private void processAiReproduction(Tribe tribe, int delta) {
        if (delta % (this.framerate * (100/tribe.getReproductivity())) == 0 && Dice.winRoll(tribe.getReproductivity(), 100)) {
            tribe.newMembers(Dice.rollDice(tribe.getLitterSize()));
        }
    }

    /**
     * Process Tribe Aggression AI if NEUTRAL
     * @param tribe
     */
    private void processAiAggression(Tribe tribe) {
        for (Tribe t : tribes) {
            if (!tribe.equals(t) && t.isAlive()) {
                tribe.setTarget(t);
                if (t.getTarget() == null && tribe.isInAggressionRange()) {
                    this.aggressionManager.addAggression(tribe, t);
                    break;
                }
                else {
                    tribe.setTarget(null);
                }
            }
        }
    }

    private void processAiAttack(Tribe tribe, int delta) {
        if (tribe.getState() == AIStateMachine.State.FIGHT && (delta % (int)(this.framerate/tribe.getAttackSpeed()) == 0)) {
            if (tribe.getTarget() == null) return;

            // change this line for damages tweak
            int forceFactor = tribe.getForce() + tribe.getForce() * ((tribe.size()-tribe.getTarget().size())/5);
            if (forceFactor < 1) forceFactor = 1;

            int damages = Dice.rollDice(forceFactor + 1);

            tribe.attack(damages);
        }
    }

    public void update(float delta) {
        for (Tribe tribe : tribes) {
            if (tribe.isAlive()) updateTribeCoordinates((int) delta, tribe);
        }
        effectsDisplayer.update(delta);
    }

    private void updateTribeCoordinates(int delta, Tribe tribe) {
        Random random = new Random();
        float xd = tribe.getXd();
        float yd = tribe.getYd();
        float x = tribe.getX();
        float y = tribe.getY();
        AIStateMachine.State state = tribe.getState();

        if (state == AIStateMachine.State.NEUTRAL) {
            if (delta % (random.nextInt(30) + (this.framerate/2)) == 0) {
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
        else if (state == AIStateMachine.State.FIGHT) {
            xd = 0;
            yd = 0;
        }

        x += xd*tribe.getSpeed();
        y += yd*tribe.getSpeed();

        if (x > this.windowWidth) x = this.windowWidth;
        else if (x < 0) x = 0;
        if (y > this.windowHeight) y = this.windowHeight;
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
        for (Tribe tribe : tribes) {
            if (tribe.isAlive()) tribe.render(ctx);
        }
        this.effectsDisplayer.render(ctx);

        // Displaying framerate
        ctx.textSize(12);
        ctx.fill(255);
        ctx.text(ctx.frameRate, 10, 10);
        //ctx.filter(PConstants.BLUR, 2);
    }
}
