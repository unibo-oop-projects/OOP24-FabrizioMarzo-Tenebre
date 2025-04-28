package model.entities.survivor.base;

import org.apache.commons.lang3.tuple.Pair;
import model.entities.survivor.SurvivorState;

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
    public int attack();

    /**
     * Applies damage to the survivor.
     *
     * @param dm The amount of damage taken
     */
    public void damageSuffer(final int dm);
    
    /**
     * Updates the survivor’s internal logic, such as position,
     * based on the time elapsed since the last update.
     *
     * @param dt Time delta (in milliseconds)
     */
    public void updateSurvivor(final int dt);
    
    /**
     * Returns the current position of the survivor.
     *
     * @return A pair (x, y) representing the current position
     */
    public Pair<Double,Double> getCurrentPos();

    /**
     * Returns the current velocity of the survivor.
     *
     * @return A pair (vx, vy) representing the current velocity
     */
    public Pair<Double,Double> getCurrentVel();

    /**
     * Returns the base velocity of the survivor.
     *
     * @return A pair (vx, vy) representing the base velocity
     */    
    public Pair<Double,Double> getBaseSurvivorVel();

    /**
     * Returns the current state of the survivor.
     *
     * @return The current state
     */    
    public SurvivorState getState();

    /**
     * Returns the number of remaining health of the survivor.
     *
     * @return The current health value
     */
    public int getLive();

    /**
     * Sets the survivor's velocity.
     *
     * @param vel A pair (vx, vy) representing the new velocity
     */
    public void setVelocity(final Pair<Double,Double> vel);

    /**
     * Sets a new state for the survivor.
     *
     * @param newState The new state to apply
     */
    public void setState(final SurvivorState newState);
}
