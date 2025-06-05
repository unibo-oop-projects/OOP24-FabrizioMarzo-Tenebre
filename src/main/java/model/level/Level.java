package model.level;

import java.util.List;

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
    
    Survivor getSurvivorOnLevel(); 

    List<Zombie> getZombieOnLevel();
    
    List<Munition> getProjectilesOnLevel();
}
