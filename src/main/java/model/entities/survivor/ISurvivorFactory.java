package model.entities.survivor;

import org.apache.commons.lang3.tuple.Pair;

public interface ISurvivorFactory {
    Survivor createCommonSurvivor(final Pair<Double,Double> pos);
}
