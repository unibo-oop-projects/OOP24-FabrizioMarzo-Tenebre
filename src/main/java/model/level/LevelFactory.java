package model.level;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorFactory;
import model.physics.physics_level.PhysicsLevelTutComponent;

public class LevelFactory {
    
    private SurvivorFactory surFact = new SurvivorFactory();

    public Level createTutorialLevel(){
        Survivor sur  = surFact.createCommonSurvivor(1000,20, Pair.of(100.0,550.0),Pair.of(50.0,0.0));
        Double lvlWidth = 8000d;
        Double lvlHeight = 2000d;
        return new TutorialLevel(sur,
                                lvlWidth,
                                lvlHeight,
                                new PhysicsLevelTutComponent());
    }



}
