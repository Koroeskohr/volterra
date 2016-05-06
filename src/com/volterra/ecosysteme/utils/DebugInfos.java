package com.volterra.ecosysteme.utils;

import com.volterra.ecosysteme.AIStateMachine;
import com.volterra.ecosysteme.AggressionManager;
import com.volterra.ecosysteme.Tribe;
import com.volterra.ecosysteme.species.Elf;
import com.volterra.ecosysteme.species.Goblin;
import com.volterra.ecosysteme.species.Human;
import com.volterra.ecosysteme.species.Orc;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by YellowFish on 06/05/2016.
 */
public class DebugInfos {
    private int time;

    private int nbHumans = 0;
    private int nbElves = 0;
    private int nbOrcs = 0;
    private int nbGoblins = 0;

    private int nbHumansMembers = 0;
    private int nbElvesMembers = 0;
    private int nbOrcsMembers = 0;
    private int nbGoblinsMembers = 0;

    private int nbHumansInAggression = 0;
    private int nbElvesInAggression = 0;
    private int nbOrcsInAggression = 0;
    private int nbGoblinsInAggression = 0;

    public DebugInfos(int time) {
        this.time = time;
    }

    public void debugInfos(PApplet ctx, ArrayList<Tribe> tribes, AggressionManager aggressionManager) {
        if (time % (int)(ctx.frameRate*2) == 0) {
            nbHumans = 0;
            nbElves = 0;
            nbOrcs = 0;
            nbGoblins = 0;
            nbHumansMembers = 0;
            nbElvesMembers = 0;
            nbOrcsMembers = 0;
            nbGoblinsMembers = 0;
            nbHumansInAggression = 0;
            nbElvesInAggression = 0;
            nbOrcsInAggression = 0;
            nbGoblinsInAggression = 0;

            for (Tribe tribe : tribes) {
                if (tribe.getSpecies() == Human.class) {
                    nbHumans++;
                    nbHumansMembers += tribe.size();
                    if (tribe.getState() != AIStateMachine.State.MIGRATING) nbHumansInAggression++;
                }
                else if (tribe.getSpecies() == Elf.class) {
                    nbElves++;
                    nbElvesMembers += tribe.size();
                    if (tribe.getState() != AIStateMachine.State.MIGRATING) nbElvesInAggression++;
                }
                else if (tribe.getSpecies() == Orc.class) {
                    nbOrcs++;
                    nbOrcsMembers += tribe.size();
                    if (tribe.getState() != AIStateMachine.State.MIGRATING) nbOrcsInAggression++;
                }
                else if (tribe.getSpecies() == Goblin.class) {
                    nbGoblins++;
                    nbGoblinsMembers += tribe.size();
                    if (tribe.getState() != AIStateMachine.State.MIGRATING) nbGoblinsInAggression++;
                }
            }
        }

        ctx.text("Framerate: " + ctx.frameRate, 10, 20);
        ctx.text("Elapsed time: " + (time/(60*60)) + ":" + (time%(60*60))/60, 10, 40);

        ctx.text("Humans: " + nbHumans + ", members: " + nbHumansMembers + ", aggression: " + nbHumansInAggression, 10, 80);
        ctx.text("Elves: " + nbElves + ", members: " + nbElvesMembers + ", aggression: " + nbElvesInAggression, 10, 100);
        ctx.text("Orcs: " + nbOrcs + ", members: " + nbOrcsMembers + ", aggression: " + nbOrcsInAggression, 10, 120);
        ctx.text("Goblins: " + nbGoblins + ", members: " + nbGoblinsMembers + ", aggression: " + nbGoblinsInAggression, 10, 140);

        time++;
    }
}
