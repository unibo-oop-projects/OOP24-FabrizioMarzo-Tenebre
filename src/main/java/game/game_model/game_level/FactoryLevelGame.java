package game.game_model.game_level;

import model.level.types.ILevelFactory;
import model.level.types.LevelFactory;
import model.level.types.LevelType;
import view.graphics_component.level.GrahicsTutlevelComponent;

/**
 * Factory class responsible for creating instances of {@link IGameLevel}.
 * 
 * <p>
 * It uses an underlying {@link ILevelFactory} to create the model level,
 * then wraps it into a {@link GameLevel} with an associated graphics component.
 * </p>
 */
public class FactoryLevelGame {

    private final ILevelFactory lvlFactory = new LevelFactory();

    /**
     * Creates a new game level instance for the specified level type.
     * 
     * @param lvlType the {@link LevelType} that identifies the desired level to
     *                create
     * @return a new {@link IGameLevel} instance representing the specified level
     */
    public IGameLevel createLevelGame(final LevelType lvlType) {
        return new GameLevel(lvlFactory.createLevel(lvlType).get(),
                new GrahicsTutlevelComponent());

    }
}
