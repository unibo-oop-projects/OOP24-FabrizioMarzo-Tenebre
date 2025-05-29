package model.armory.charger;

import org.apache.commons.lang3.tuple.Pair;

public class FactoryCharger {

    public Charger createDrumCharger(final Pair<Double,Double> pos){
        final int capacity=5;
        return new Drum(capacity, pos);
    }
}
