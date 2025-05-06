package physics.physics_component;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.entities_base.survivor_base.Survivor;
import utils.PairUtils;

public class PhysicsCommonComponent implements PhysicsSurvivorComponent {

    @Override
    public void updateSurvivor(final Survivor sur,final int dt) {
        sur.setPosition(PairUtils.sum(sur.getCurrentPos(),nextPos(dt,sur.getCurrentVel())));
        sur.updateBBox(sur.getCurrentPos());
        System.out.println("For the Survivor" + PairUtils.toIntPair(sur.getBBox().getULcorner()) +
                           " and " + PairUtils.toIntPair(sur.getBBox().getBRcorner()));
        System.out.println("I am modifing my state with th PhsycsComponent");
    }
    
    private Pair<Double,Double> nextPos(final int dt, final Pair<Double,Double> vel){
        return PairUtils.mul(vel,0.001*dt);
    }

}
