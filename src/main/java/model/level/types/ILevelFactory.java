package model.level.types;

import java.util.Optional;

public interface ILevelFactory {
    Optional<Level> createLevel(final LevelType lvlType);
}
