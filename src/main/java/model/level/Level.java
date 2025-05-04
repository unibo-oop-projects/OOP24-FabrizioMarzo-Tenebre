package model.level;

import model.entities.entities_game.survivor_game.IGameSurvivor;

/**
 * Interface representing a game level.
 * <p>
 * A level manages the game survivor and is responsible for updating the game state
 * over time.
 */
public interface Level {

    /**
     * Returns the survivor entity associated with this level.
     *
     * @return the {@link IGameSurvivor} present in the level
     */
    public IGameSurvivor getSurvivorOnLevel(); 

    /**
     * Updates the state of the level.
     * <p>
     * This method is typically called to update all entities
     * within the level based on the time elapsed.
     *
     * @param dt the time delta in milliseconds since the last update
     */
    public void updateState(final int dt);
    
}
