package game.game_model.game_level;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum GameLevelType {
    LEVEL_TUTORIAL("LevelTutorial"),
    LEVEL_ONE("Level1"),
    BOSS("LevelBoss");

    private final String displayName;

    GameLevelType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Optional<GameLevelType> next() {
        List<GameLevelType> levels = Arrays.asList(GameLevelType.values());
        int nextIndex = levels.indexOf(this) + 1;
        if (nextIndex < levels.size()) {
            return Optional.of(levels.get(nextIndex));
        } else {
            return Optional.empty();
        }
    }
}
