package model.level;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.RectBoundingBox;
import model.physics.physics_level.PhysicsLevelTutComponent;

public class LevelFactory {
    
    private static final double LEVEL_MIN_X = 0.0;
    private static final double LEVEL_MIN_Y = 0.0;

    public Level createTutorialLevel(){
        final Double lvlWidth = 4000d;
        final Double lvlHeight = 2500d;
        return new LevelTutorial(lvlWidth,
                                lvlHeight,
                                new RectBoundingBox(Pair.of(LEVEL_MIN_X,lvlHeight), Pair.of(lvlWidth,LEVEL_MIN_Y)),
                                new PhysicsLevelTutComponent());
    }

}
