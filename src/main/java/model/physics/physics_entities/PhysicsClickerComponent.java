package model.physics.physics_entities;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.EntitieState;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import utils.PairUtils;

public class PhysicsClickerComponent implements PhysicsZombieComponent{

    @Override
    public void updateZombie(final Zombie zob,final Survivor sur,final int dt) {
        this.moveZombieTowardsSurvivor(zob, sur, dt);
    }
    

    private void moveZombieTowardsSurvivor(Zombie zob, Survivor sur, int dt){
        // Calcolate The difference between the Survivor Position and the Zombie Position
        Pair<Double,Double> difPos = PairUtils.diff(sur.getCurrentPos(), zob.getCurrentPos());

        double distanceNorm2 = PairUtils.norm2(difPos);

        if ((int) distanceNorm2 == 0) {
            // If the distance is 0 change the State of the Zombie
            zob.setState(EntitieState.IDLE);
            return;
        }
        
        // Normalize direction vector
        Pair<Double,Double> direction = PairUtils.normalize(difPos);

        // Set zombie state based on movement direction
        double dx = difPos.getLeft();
        double dy = difPos.getRight();

        if (Math.abs(dx) > Math.abs(dy)) {
            zob.setState(dx < 0 ? EntitieState.MOVE_RIGHT : EntitieState.MOVE_LEFT);
        } else {
            zob.setState(dy < 0 ? EntitieState.MOVE_DOWN : EntitieState.MOVE_UP);
        }

        // Compute movement velocity
        double speed = PairUtils.norm2(zob.getBaseZombieVel());
        Pair<Double,Double> velocity =  PairUtils.mulScale(direction, speed);

         // Apply movement
        zob.setPosition(PairUtils.sum(zob.getCurrentPos(),nextPos(dt, velocity)));
    }

    private Pair<Double,Double> nextPos(final int dt, final Pair<Double,Double> vel){
        return PairUtils.mulScale(vel,0.001*dt);
    }


}
