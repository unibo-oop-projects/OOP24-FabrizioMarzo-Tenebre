package model.ai.separation;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.zombie.Zombie;

public interface SeparationStrategy< Z extends Zombie > {

    Pair<Double,Double> computeForce(final Z zombie,final List<Z> others);  

}
