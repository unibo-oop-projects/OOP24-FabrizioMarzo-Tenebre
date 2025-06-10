package model.entities.zombie;

import org.apache.commons.lang3.tuple.Pair;

public interface IZombieFactory {
    Zombie createClickerZombie(final Pair<Double,Double> pos);
}
