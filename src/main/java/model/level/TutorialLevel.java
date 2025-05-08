package model.level;

import org.apache.commons.lang3.tuple.Pair;

import game.entities_game.IGameSurvivor;
import model.bounding_box.BoundingBox;
import model.bounding_box.RectBoundingBox;
import model.entities.survivor.Survivor;
import model.physics.physics_level.PhysicsLevelComponent;

/**
 * Represents the tutorial level of the game.
 * <p>
 * This level contains a single common survivor and initializes it with
 * predefined attributes such as health, attack power, position, and velocity.
 * It also handles updating the survivor's internal state over time.
 */
public class TutorialLevel implements Level {

    // Size of the Level
    private double lvlWidth;   
    private double lvlHeight;  

    // Level Bouding Box
    private BoundingBox bbox;
    
    // THe only Survivor on the Game
    private Survivor surLv;
    // The physic on lthe level 
    private PhysicsLevelComponent physicLvComp;

    /**
     * Constructs the tutorial level with a preconfigured {@link IGameSurvivor}.
     */
    public TutorialLevel(final double lvlWidth,final double lvlHeight,
                         final BoundingBox bbox,
                        final PhysicsLevelComponent physcLevel,
                        final Survivor surLv){

        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;
        this.bbox = bbox;
        this.physicLvComp = physcLevel;
        this.surLv = surLv;
    }

    @Override
    public void updateLevelState(final int dt){
        this.surLv.updatePhysics(dt);
        physicLvComp.updateLevel(this, dt);
    }

    @Override
    public double getTutorialLevelWidth() {
        return this.lvlWidth;
    }

    @Override
    public double getTutorialLevelHeight() {
        return this.lvlHeight;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Updates the survivor's internal state based on the elapsed time.
     *
     * @param dt the time delta in milliseconds since the last update
     */
    @Override
    public BoundingBox getLevelBBox() {
        return this.bbox;
    }

    /**
     * {@inheritDoc}
     * 
     * @return the survivor entity present in this tutorial level
     */
    @Override
    public Survivor getSurvivorOnLevel(){
        return this.surLv;
    }


}
