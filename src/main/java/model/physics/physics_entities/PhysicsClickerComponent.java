package model.physics.physics_entities;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.EntitieState;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import utils.PairUtils;

public class PhysicsClickerComponent implements PhysicsZombieComponent{

    @Override
    public void updateZombie(final Zombie zob,final Survivor sur,final int dt) {

        // Calcolate The difference between the Survivor Position and the Zombie Position
        var difPos = PairUtils.diff(sur.getCurrentPos(), zob.getCurrentPos());
        System.out.println("The difference between the Survivor Position and the Zombie Position is: " + PairUtils.toIntPair(difPos));


        var distanceNorm2 = PairUtils.norm2(difPos);
        System.out.println("The Distance form Zombie and Survivor is: " + (int) distanceNorm2);


        if (distanceNorm2 == 0) {
            // If the distance is 0 change the State of the Zombie
            zob.setState(EntitieState.IDLE);
        }

        var normaleDie = PairUtils.normalize(difPos);
        System.out.println("The Normale od direction  to Zombie on Survivor is: " + normaleDie);

        // Detect the direction of the move e upate the state
        if (Math.abs(difPos.getLeft()) > Math.abs(difPos.getRight())) {
            // If the orizontal diference is plus of the vertical, the move is on left or on right
            if (difPos.getLeft() < 0) {
                zob.setState(EntitieState.MOVE_RIGHT); // Move on right
            } else {
                zob.setState(EntitieState.MOVE_LEFT); // Move on left 
            }
        } else {
            // If the vertical diference is plus of the orizontal, the move is UP or Down
            if (difPos.getRight() < 0) {
                zob.setState(EntitieState.MOVE_DOWN); // Move on Down
            } else {
                zob.setState(EntitieState.MOVE_UP); // Move on plus
            }
        }

        var velocity =  PairUtils.mulScale(normaleDie, PairUtils.norm2(zob.getBaseZombieVel()));
        System.out.println("The Vector velocity with the diection on Survivor is: " + velocity);

        // Set the new position of the Zombie
        zob.setPosition(PairUtils.sum(zob.getCurrentPos(),nextPos(dt, velocity)));

    }
    
    private Pair<Double,Double> nextPos(final int dt, final Pair<Double,Double> vel){
        return PairUtils.mulScale(vel,0.001*dt);
    }


}
