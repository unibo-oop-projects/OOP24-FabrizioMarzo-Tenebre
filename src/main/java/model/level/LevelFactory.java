package model.level;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.RectBoundingBox;
import model.physics.physics_level.PhysicsLevelTutComponent;

public class LevelFactory {
    
    public Level createTutorialLevel(){
        final Double lvlWidth = 4000d;
        final Double lvlHeight = 2500d;
        return new TutorialLevel(lvlWidth,
                                lvlHeight,
                                new RectBoundingBox(Pair.of(0.0,lvlHeight), Pair.of(lvlWidth,0.0)),
                                new PhysicsLevelTutComponent());
    }

}
