package physics.physics_component;

import org.apache.commons.lang3.tuple.Pair;

import model.PairUtils;
import model.entities.survivor.base.Survivor;

public class PhysicsCommonComponent implements PhysicsSurvivorComponent {

    @Override
    public void update(final Survivor sur,final int dt) {
        sur.setPosition(PairUtils.sum(sur.getCurrentPos(),nextPos(dt,sur.getCurrentVel())));
        System.out.println("I am modifing my state with th PhsycsComponent");
    }
    
    private Pair<Double,Double> nextPos(final int dt, final Pair<Double,Double> vel){
        return PairUtils.mul(vel,0.001*dt);
    }

}
