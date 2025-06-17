package model.level.types;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum LevelType {
    LEVEL_TUTORIAL("LevelTutorial"),
    LEVEL_ONE("Level1"),
    BOSS("LevelBoss");

    private final String displayName;

    LevelType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Optional<LevelType> next() {
        List<LevelType> levels = Arrays.asList(LevelType.values());
        int nextIndex = levels.indexOf(this) + 1;
        if (nextIndex < levels.size()) {
            return Optional.of(levels.get(nextIndex));
        } else {
            return Optional.empty();
        }
    }
}
