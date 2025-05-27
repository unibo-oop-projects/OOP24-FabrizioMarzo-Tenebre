package model.ai.movement;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;


public interface MovementStrategy< Z extends Zombie , S extends Survivor> {

    Pair<Double,Double> computeVelocity(final Z zombie,final S target);
   
}
