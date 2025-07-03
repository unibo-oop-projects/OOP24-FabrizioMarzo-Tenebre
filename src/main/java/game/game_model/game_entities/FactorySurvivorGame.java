package game.game_model.game_entities;

import input.input_component.InputCommonComponent;
import model.entities.survivor.Survivor;
import view.graphics_component.survivor.GraphicsCommonComponent;

/**
 * Factory class responsible for creating instances of {@link IGameSurvivor}.
 * <p>
 * Provides methods to create different types of game survivors with
 * their associated graphics and input components.
 * </p>
 */
public class FactorySurvivorGame {

    /**
     * Creates a common type of {@link IGameSurvivor} using the provided
     * {@link Survivor} instance.
     * <p>
     * This method initializes the game survivor with standard graphics and input
     * components.
     * </p>
     *
     * @param common the {@link Survivor} instance to wrap in a game survivor
     * @return a new {@link IGameSurvivor} instance configured with common
     *         components
     */
    public IGameSurvivor gameSurvivorCommon(final Survivor common) {
        return new GameSurvivor(common,
                new GraphicsCommonComponent(common.getClass().getSimpleName()),
                new InputCommonComponent());
    }
}
