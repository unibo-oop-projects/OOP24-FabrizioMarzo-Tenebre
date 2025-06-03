package model.entities.zombie;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;
import model.entities.EntitieState;

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

    void setState(final EntitieState newState);

    int getWidth();

    int getHeight();

    int getLive();

    Pair<Double,Double> getCurrentPos();

    Pair<Double,Double> getCurrentVel();

    Pair<Double,Double> getBaseZombieVel();

    EntitieState getState();

    BoundingBox getBBox();
}
