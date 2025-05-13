package model.level;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.RectBoundingBox;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorFactory;
import model.physics.physics_level.PhysicsLevelTutComponent;

public class LevelFactory {
    
    private SurvivorFactory surFact = new SurvivorFactory();

    public Level createTutorialLevel(){
        Survivor sur  = surFact.createCommonSurvivor(1000,20, Pair.of(1000.0,1000.0),Pair.of(200.0,0.0));
        final Double lvlWidth = 4000d;
        final Double lvlHeight = 2500d;
        return new TutorialLevel(lvlWidth,
                                lvlHeight,
                                new RectBoundingBox(Pair.of(0.0,lvlHeight), Pair.of(lvlWidth,0.0)),
                                new PhysicsLevelTutComponent(),
                                sur);
    }

}
