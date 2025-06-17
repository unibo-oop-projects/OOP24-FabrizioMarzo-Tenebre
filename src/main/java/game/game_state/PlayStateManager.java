package game.game_state;

import java.util.Optional;

import game.game_model.game_level.FactoryLevelGame;
import game.game_model.game_level.IGameLevel;
import model.level.types.LevelType;

public class PlayStateManager {
    private LevelType currentLevel;
    private final FactoryLevelGame lvlFactoryGame;

    public PlayStateManager() {
        this.lvlFactoryGame = new FactoryLevelGame();
        this.currentLevel = LevelType.LEVEL_TUTORIAL;
    }

    public IGameLevel loadCurrentLevel() {
        return lvlFactoryGame.createLevelGame(currentLevel);
    }

    public Optional<IGameLevel> loadNextLevel() {
        Optional<LevelType> next = currentLevel.next();
        if (next.isPresent()) {
            currentLevel = next.get();
            return Optional.of(lvlFactoryGame.createLevelGame(currentLevel));
        }
        return Optional.empty();
    }

    public LevelType getCurrentLevelType() {
        return currentLevel;
    }
}
