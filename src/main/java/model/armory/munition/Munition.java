package model.armory.munition;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;

public interface Munition {
    
    int getDamage();

    BoundingBox getBBox();

    void setShot(final Pair<Double,Double> dirShoot);

    void moveShoot(final int dt);

    Boolean isShot();

    void setPos(final Pair<Double,Double> pos);
}
