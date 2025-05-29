package model.armory.charger;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;

public interface Charger {

    Munition extractAmmunition();

    void setPos(final Pair<Double,Double> pos);

    int getCurrentLoad();
    
}
