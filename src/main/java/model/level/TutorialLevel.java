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
    private double lvlWidth;   // 80 meters
    private double lvlHeight;  // 20 meters

    // Level Bouding Box
    private BoundingBox bbox;
    
    // THe only Survivor on the Game
    private Survivor surLv;
    // The physic on lthe level 
    private PhysicsLevelComponent physicLvComp;

    /**
     * Constructs the tutorial level with a preconfigured {@link IGameSurvivor}.
     */
    public TutorialLevel(final Survivor surLv,final double lvlWidth,final double lvlHeight, final PhysicsLevelComponent physcLevel){
        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;
        this.surLv = surLv;
        this.physicLvComp = physcLevel;
        this.bbox = new RectBoundingBox(Pair.of(0.0,this.lvlHeight), Pair.of(this.lvlWidth,0.0));
    }

    /**
     * {@inheritDoc}
     * 
     * @return the survivor entity present in this tutorial level
     */
    public Survivor getSurvivorOnLevel(){
        return this.surLv;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Updates the survivor's internal state based on the elapsed time.
     *
     * @param dt the time delta in milliseconds since the last update
     */
    @Override
    public void updateState(final int dt){
        this.surLv.updatePhysics(dt);
        physicLvComp.updateLevel(this, dt);
    }

    @Override
    public BoundingBox getLevelBBox() {
        return this.bbox;
    }

    public double getTutorialLevelWidth() {
        return this.lvlWidth;
    }

    public double getTutorialLevelHeight() {
        return this.lvlHeight;
    }
}
