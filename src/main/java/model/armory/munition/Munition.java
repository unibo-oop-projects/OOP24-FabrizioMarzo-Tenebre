package model.armory.munition;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;

public interface Munition {
    
    int getDamage();

    BoundingBox getBBox();

    void moveOn(final int dt);

    void setDir(final Pair<Double,Double> dirShoot);

}
