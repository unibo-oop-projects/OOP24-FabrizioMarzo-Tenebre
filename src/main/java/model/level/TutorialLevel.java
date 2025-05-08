package model.level;

import org.apache.commons.lang3.tuple.Pair;

import game.entities_game.IGameSurvivor;
import model.bounding_box.BoundingBox;
import model.bounding_box.RectBoundingBox;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorFactory;
import model.physics.physics_level.PhysicsLevelComponent;
import model.physics.physics_level.PhysicsLevelTutComponent;

/**
 * Represents the tutorial level of the game.
 * <p>
 * This level contains a single common survivor and initializes it with
 * predefined attributes such as health, attack power, position, and velocity.
 * It also handles updating the survivor's internal state over time.
 */
public class TutorialLevel implements Level {

    // Size of the Level
    private static final double TUTORIAL_LEVEL_WIDTH = 8000;   // 80 meters
    private static final double TUTORIAL_LEVEL_HEIGHT = 2000;  // 20 meters

    // Level Bouding Box
    private BoundingBox bbox;
    // Factory for create all Survivor in th elevel
    private SurvivorFactory fact = new SurvivorFactory(); 
    // THe only Survivor on the Game
    private Survivor survivorCommon;
    // The physic on lthe level 
    private PhysicsLevelComponent physicLvComp;

    /**
     * Constructs the tutorial level with a preconfigured {@link IGameSurvivor}.
     */
    public TutorialLevel(){
        this.bbox = new RectBoundingBox(Pair.of(0.0,TUTORIAL_LEVEL_HEIGHT), Pair.of(TUTORIAL_LEVEL_WIDTH,0.0));
        this.survivorCommon = fact.createCommonSurvivor(1000,20, Pair.of(100.0,550.0),Pair.of(50.0,0.0));
        this.physicLvComp = new PhysicsLevelTutComponent();
    }

    /**
     * {@inheritDoc}
     * 
     * @return the survivor entity present in this tutorial level
     */
    public Survivor getSurvivorOnLevel(){
        return this.survivorCommon;
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
        this.survivorCommon.updatePhysics(dt);
        physicLvComp.updateLevel(this, dt);
    }

    @Override
    public BoundingBox getLevelBBox() {
        return this.bbox;
    }

    public double getTutorialLevelWidth() {
        return TUTORIAL_LEVEL_WIDTH;
    }

    public double getTutorialLevelHeight() {
        return TUTORIAL_LEVEL_HEIGHT;
    }
}
