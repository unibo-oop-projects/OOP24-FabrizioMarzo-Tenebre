package model.level.types;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.RectBoundingBox;
import model.physics.physics_level.PhysicsLevelTutComponent;

public class LevelFactory implements ILevelFactory {
    
    private static final double LEVEL_MIN_X = 0.0;
    private static final double LEVEL_MIN_Y = 0.0;

    private final Map<LevelType, Supplier<Level>> levels  = Map.of(
        LevelType.LEVEL_TUTORIAL, this::createTutorialLevel
    );

    private Level createTutorialLevel(){
        final Double lvlWidth = 4000d;
        final Double lvlHeight = 2500d;
        return new LevelTutorial(lvlWidth,
                                lvlHeight,
                                new RectBoundingBox(Pair.of(LEVEL_MIN_X,lvlHeight), Pair.of(lvlWidth,LEVEL_MIN_Y)),
                                new PhysicsLevelTutComponent());
    }

    @Override
    public Optional<Level> createLevel(final LevelType lvlType) {
        return Optional.ofNullable(levels.get(lvlType))
                .map(Supplier::get);
    }

}
