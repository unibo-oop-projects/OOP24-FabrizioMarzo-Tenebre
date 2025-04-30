package model.entities_game.survivor_game;

import org.apache.commons.lang3.tuple.Pair;

import input.input_component.CompInputCommon;
import model.entities.survivor.SurvivorFactory;
import model.entities.survivor.base.Survivor;
import view.graphics_component.GraphicsCommonComponent;

/**
 * Factory class responsible for creating instances of {@link IGameSurvivor} entities.
 * This class uses the {@link SurvivorFactory} to create a survivor and then wraps it
 * with the necessary input and graphics components for the game.
 */
public class FactorySurvivorGame {
    private SurvivorFactory sur = new SurvivorFactory();

    /**
     * Creates a {@link GameSurvivor} object representing a common survivor with the given attributes.
     * It also initializes the necessary graphics and input components for the survivor.
     *
     * @param health   the initial health of the survivor
     * @param attack the attack strength of the survivor
     * @param pos    the initial position of the survivor, represented as a pair of coordinates (x, y)
     * @param vel    the initial velocity of the survivor, represented as a pair of velocity components (vx, vy)
     * @return a new {@link IGameSurvivor} instance, specifically a {@link GameSurvivor} with common attributes
     */
    public IGameSurvivor gameSurvivorCommon(final int health,final int attack, final Pair<Double,Double> pos, final Pair<Double,Double> vel){
        Survivor common = sur.createCommon(health, attack, pos, vel);
        return new GameSurvivor(common,
                     new GraphicsCommonComponent(common.getClass().getSimpleName()),
                     new CompInputCommon());
    }
}
