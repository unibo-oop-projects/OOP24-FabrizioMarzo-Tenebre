package model.armory.munition;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;

public interface Munition {
    
    int getDamage();

    BoundingBox getBBox();

    void setShoot(final Pair<Double,Double> dirShoot, final Pair<Double,Double> posShoot);

    void moveShoot(final int dt);

    boolean isShoot();

    void setPos(final Pair<Double,Double> nextPos);

    Pair<Double, Double> getCurrentPos();

    int getWidth();
}
