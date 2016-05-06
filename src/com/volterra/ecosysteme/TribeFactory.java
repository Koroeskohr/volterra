package com.volterra.ecosysteme;

import com.volterra.ecosysteme.species.*;
import com.volterra.ecosysteme.utils.Dice;
import com.volterra.engine.Simulation;
import com.volterra.ecosysteme.traits.Trait;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Victor on 26/04/2016.
 */
public class TribeFactory {
  enum SpeciesEnum { HUMAN, ORC, GOBLIN, ELF }
  private static String[] traitClassNames = { "Aggressive", "Coward", "Fast", "Fertile", "Heavy", "Infertile", "Light", "Slow" };

  private static final HashMap<SpeciesEnum, Class> enumClassMap;
  static
  {
    enumClassMap = new HashMap<>();
    enumClassMap.put(SpeciesEnum.HUMAN, Human.class);
    enumClassMap.put(SpeciesEnum.ORC, Orc.class);
    enumClassMap.put(SpeciesEnum.GOBLIN, Goblin.class);
    enumClassMap.put(SpeciesEnum.ELF, Elf.class);
  }

  private static final int BASE_TRIBE_SIZE = 15;

  public static Tribe create(SpeciesEnum species) {
    return create(species, 0);
  }

  /**
   * Handles all the creation process for tribe, factory pattern. Full static methods for maximal laziness.
   * @param species Species we want to instantiate.
   * @return Tribe with members (? extends Species) and decorators (Trait)
   */
  public static Tribe create(SpeciesEnum species, int number) {
    Tribe tribe = null;

    Random rand = new Random();
    int size;
    if (number == 0) {
      int variation = rand.nextInt(10 + 1) - 5;
      size = BASE_TRIBE_SIZE + variation;
    }
    else {
      size = number;
    }
    float x = rand.nextFloat() * Simulation.getSimulation().getWindowWidth();
    float y = rand.nextFloat() * Simulation.getSimulation().getWindowHeight();
    System.out.println(x + "\t" + y);

    Class associatedClass = enumClassMap.get(species);
    ArrayList<Species> members = null;
    members = generateMembers(associatedClass, size);

    tribe = new TribeWithTraits(members, x, y, associatedClass);

    // New version : no species traits, just random ones
    String[] shuffled = traitClassNames.clone();
    ArrayList<String> chosenTraits = new ArrayList<>();
    Collections.shuffle(Arrays.asList(shuffled));

    int amountOfTraits = Dice.rollDice(traitClassNames.length/2);
    for(int i = 0; i < amountOfTraits; ++i) {
      chosenTraits.add(shuffled[i]);
    }

    for(String s : chosenTraits) {
      Class<?> klass = null;
      try {
        klass = Class.forName("com.volterra.ecosysteme.traits." + s);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }

      Constructor constructor = null;
      try {
        constructor = klass.getConstructor(Tribe.class);
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      }

      try {
        tribe = (Trait) constructor.newInstance(tribe);
      } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }

    }

    return tribe;
  }

  /**
   * Returns an array of a specified amount for the specified amount.
   * @param klass Class of the subclass of Species we want the members of the tribe to be
   * @param amount Amount of members in the tribe. Computed in create.
   * @return ArrayList of species
   */
  private static ArrayList<Species> generateMembers(Class<? extends Species> klass, int amount){
    ArrayList<Species> list = new ArrayList<>();

    for( int i = 0 ; i < amount ; ++i ) {
      try {
        java.lang.reflect.Constructor<?> ctor = klass.getConstructor();
        Species dude = ((Species) ctor.newInstance());
        list.add(klass.cast(dude));
      } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
        e.printStackTrace();
      }
    }

    return list;
  }

  /**
   * Get the number of <i>Species</i> contained in SpeciesEnum.
   * @return An int that represent the number of existing <i>Species</i>
     */
  public static int numberOfSpecies() {
    return SpeciesEnum.values().length;
  }

  /**
   * Return an element of <i>SpeciesEnum</i> corresponding to the integer passed in parameter
   * @param index An int corresponding to the index of an element in <i>SpeciesEnum</i>
   * @return The SpeciesEnum corresponding
     */
  public static SpeciesEnum getSpeciesFromInt(int index) {
    return SpeciesEnum.values()[index];
  }

  /**
   * Return an element of <i>SpeciesEnum</i> corresponding to the given Species
   * @param species The species
   * @return The SpeciesEnum corresponding
   */
  public static SpeciesEnum getSpeciesFromClass(Class species) {
    SpeciesEnum r = null;

    for ( Map.Entry<SpeciesEnum, Class> entry : enumClassMap.entrySet() ) {
      if (entry.getValue().equals(species)) {
        r = entry.getKey();
      }
    }

    return r;
  }

  public static void main(String[] args) {
    //Tribe<Human> t = TribeFactory.create(SpeciesEnum.HUMAN);
    //SpeciesEnum.HUMAN.name();
  }
}
