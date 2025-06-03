package model.level;

import java.util.List;

import game.game_entities.IGameSurvivor;
import model.armory.munition.Munition;
import model.bounding_box.BoundingBox;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;

/**
 * Interface representing a game level.
 * <p>
 * A level manages the game survivor and is responsible for updating the game state
 * over time.
 */
public interface Level {

    /**
     * Updates the state of the level.
     * <p>
     * This method is typically called to update all entities
     * within the level based on the time elapsed.
     *
     * @param dt the time delta in milliseconds since the last update
     */
    void updateLevelState(final int dt);

    double getLevelWidth();
   
    double getLevelHeight();

    BoundingBox getLevelBBox();
    
    /**
     * Returns the survivor entity associated with this level.
     *
     * @return the {@link IGameSurvivor} present in the level
     */
    Survivor getSurvivorOnLevel(); 

    List<Zombie> getZombieOnLevel();

    void setWeaponOnSurvivor();

    List<Munition> getProjectilesOnLevel();
}
