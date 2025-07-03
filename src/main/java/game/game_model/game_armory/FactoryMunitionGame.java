package game.game_model.game_armory;

import model.armory.munition.Munition;
import view.graphics_component.armory.GraphicsParabellumComponent;

/**
 * Factory class responsible for creating {@link IGameMunition} instances.
 * <p>
 * This factory produces game munition objects configured with appropriate
 * graphics components.
 * </p>
 */
public class FactoryMunitionGame {

    /**
     * Creates a new {@link IGameMunition} instance for the given munition.
     *
     * @param munition the underlying {@link Munition} model to wrap
     * @return a new {@link IGameMunition} configured with the appropriate graphics
     *         component
     */
    public IGameMunition gameMunition(final Munition munition) {
        return new GameMunition(munition,
                new GraphicsParabellumComponent(munition.getClass().getSimpleName()));
    }

}
