package model.entities.survivor;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;

/**
 * Survivor interface representing a survivor in the game.
 * It defines the basic actions and state management for a survivor entity.
 */
public interface Survivor {

    /**
     * Performs an attack and returns the damage dealt.
     *
     * @return The amount of damage inflicted by the attack
     */
    int attack();

    /**
     * Applies damage to the survivor.
     *
     * @param dm The amount of damage taken
     */
    void damageSuffer(final int dm);
    
    void updatePhysics(final int dt);
    
    /**
     * Sets the survivor's velocity.
     *
     * @param vel A pair (vx, vy) representing the new velocity
     */
    void setVelocity(final Pair<Double,Double> vel);

    void setPosition(final Pair<Double, Double> pos);

    /**
     * Sets a new state for the survivor.
     *
     * @param newState The new state to apply
     */
    void setState(final EntitieState newState);

    /**
     * Returns the number of remaining health of the survivor.
     *
     * @return The current health value
     */
    int getLive();
    
    int getWidth();

    int getHeight();

    /**
     * Returns the current position of the survivor.
     *
     * @return A pair (x, y) representing the current position
     */
    Pair<Double,Double> getCurrentPos();

    /**
     * Returns the current velocity of the survivor.
     *
     * @return A pair (vx, vy) representing the current velocity
     */
    Pair<Double,Double> getCurrentVel();

    /**
     * Returns the base velocity of the survivor.
     *
     * @return A pair (vx, vy) representing the base velocity
     */    
    Pair<Double,Double> getBaseSurvivorVel();

    /**
     * Returns the current state of the survivor.
     *
     * @return The current state
     */    
    EntitieState getState();

    BoundingBox getBBox();

}
