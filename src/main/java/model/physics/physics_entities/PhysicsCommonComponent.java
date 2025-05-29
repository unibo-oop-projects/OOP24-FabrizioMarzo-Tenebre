package model.physics.physics_entities;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;
import model.entities.EntitieState;
import model.entities.survivor.Survivor;
import utils.PairUtils;

public class PhysicsCommonComponent implements PhysicsSurvivorComponent {

    @Override
    public void updateSurvivor(final Survivor sur,final int dt) {
        sur.setPosition(PairUtils.sum(sur.getCurrentPos(),nextPos(dt,sur.getCurrentVel())));

        // System.out.println("For the Survivor" + PairUtils.toIntPair(sur.getBBox().getULcorner()) +
        //                    " and " + PairUtils.toIntPair(sur.getBBox().getBRcorner()));
        // System.out.println("I am modifing my state with th PhsycsComponent");
        
        if (sur.getState() == EntitieState.ATTACK){
            for (Munition m : sur.shoot(dt) ){
                m.moveShoot(dt);
            }
        }
    }
    
    private Pair<Double,Double> nextPos(final int dt, final Pair<Double,Double> vel){
        return PairUtils.mulScale(vel,0.001*dt);
    }

}
