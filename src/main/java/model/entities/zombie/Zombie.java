package model.entities.zombie;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;

/**
 * Zombie interface representing a zombie in the game.
 * It defines the basic actions and state management for a zombie entity.
 */
public interface Zombie {
    
    int attack();

    void damageSuffer(final int dm);

    void updatePhysics(final int dt);

    void setPosition(final Pair<Double, Double> pos);

    void setVelocity(final Pair<Double,Double> vel);

    void setState(final ZombieState newState);

    int getWidth();

    int getHeight();

    int getLive();

    int getMaxZombieHealth();

    Pair<Double,Double> getCurrentPos();

    Pair<Double,Double> getCurrentVel();

    Pair<Double,Double> getBaseZombieVel();

    ZombieState getState();

    BoundingBox getBBox();
}
