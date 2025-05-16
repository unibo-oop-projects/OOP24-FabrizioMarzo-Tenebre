package model.physics.physics_entities;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.zombie.Zombie;
import utils.PairUtils;

public class PhysicsClickerComponent implements PhysicsZombieComponent{

    @Override
    public void updateZombie(Zombie zob, int dt) {
        zob.setPosition(PairUtils.sum(zob.getCurrentPos(), nextPos(dt, zob.getCurrentVel())));
    }
    
    private Pair<Double,Double> nextPos(final int dt, final Pair<Double,Double> vel){
        return PairUtils.mul(vel,0.001*dt);
    }
}
