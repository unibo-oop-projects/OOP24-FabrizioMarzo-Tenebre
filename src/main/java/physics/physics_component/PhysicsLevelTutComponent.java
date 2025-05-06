package physics.physics_component;

import model.level.Level;

public class PhysicsLevelTutComponent implements PhysicsLevelComponent {

    @Override
    public void updateLevel(Level lv, int dt) {
        if(!lv.getLevelBBox().isColliding(lv.getSurvivorOnLevel().getBBox().getULcorner(),lv.getSurvivorOnLevel().getBBox().getBRcorner())){
            System.out.println("Final level");
        }
    }
    
}
