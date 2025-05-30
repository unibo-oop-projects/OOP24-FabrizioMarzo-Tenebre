package model.physics.physics_entities;

import org.apache.commons.lang3.tuple.Pair;
import model.entities.survivor.Survivor;
import utils.PairUtils;

public class PhysicsCommonComponent implements PhysicsSurvivorComponent {

    @Override
    public void updateSurvivor(final Survivor sur,final int dt) {
        sur.setPosition(PairUtils.sum(sur.getCurrentPos(),nextPos(dt,sur.getCurrentVel())));
    }
    
    private Pair<Double,Double> nextPos(final int dt, final Pair<Double,Double> vel){
        return PairUtils.mulScale(vel,0.001*dt);
    }

}
