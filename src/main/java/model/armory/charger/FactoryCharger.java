package model.armory.charger;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Factory class for creating different types of {@link Charger} instances.
 */
public class FactoryCharger {

    /**
     * Creates a new {@link Drum} charger with a fixed capacity.
     * 
     * @param pos the initial position of the charger
     * @return a new {@link Drum} instance with capacity 5
     */
    public Charger createDrumCharger(final Pair<Double, Double> pos) {
        final int capacity = 5;
        return new Drum(capacity, pos);
    }
}
