package model.level;

import org.apache.commons.lang3.tuple.Pair;

import model.entities_game.survivor_game.FactorySurvivorGame;
import model.entities_game.survivor_game.IGameSurvivor;

/**
 * Represents the tutorial level of the game.
 * <p>
 * This level contains a single common survivor and initializes it with
 * predefined attributes such as health, attack power, position, and velocity.
 * It also handles updating the survivor's internal state over time.
 */
public class TutorialLevel implements Level {
    private FactorySurvivorGame fact = new FactorySurvivorGame(); 
    private IGameSurvivor survivorCommon;

    /**
     * Constructs the tutorial level with a preconfigured {@link IGameSurvivor}.
     */
    public TutorialLevel(){
        this.survivorCommon = fact.gameSurvivorCommon(1000,20, Pair.of(390.0,40.0),Pair.of(50.0,0.0));
    }

    /**
     * {@inheritDoc}
     * 
     * @return the survivor entity present in this tutorial level
     */
    public IGameSurvivor getSurvivorOnLevel(){
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
        survivorCommon.getSurvivor().updateSurvivor(dt);
    }
}
