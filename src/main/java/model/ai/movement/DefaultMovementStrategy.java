package model.ai.movement;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.EntitieState;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import utils.PairUtils;

public class DefaultMovementStrategy<Z extends Zombie, S extends Survivor> implements MovementStrategy<Z,S> {

    private static final Pair<Double, Double> ZERO_VELOCITY = Pair.of(0.0, 0.0);


    @Override
    public Pair<Double,Double> computeVelocity(final Zombie zombie,final Survivor target) {

        Pair<Double, Double> direction = PairUtils.diff(target.getCurrentPos(), zombie.getCurrentPos());
        double distance = PairUtils.norm2(direction);

        if (distance == 0) {
            zombie.setState(EntitieState.IDLE);
            return ZERO_VELOCITY;
        }

        Pair<Double, Double> normalizedDirection = PairUtils.normalize(direction);

        double dx = direction.getLeft();
        double dy = direction.getRight();

        if (Math.abs(dx) > Math.abs(dy)) {
            zombie.setState(dx < 0 ? EntitieState.MOVE_RIGHT : EntitieState.MOVE_LEFT);
        } else {
            zombie.setState(dy < 0 ? EntitieState.MOVE_DOWN : EntitieState.MOVE_UP);
        }

        double speed = PairUtils.norm2(zombie.getBaseZombieVel());
        return PairUtils.mulScale(normalizedDirection, speed);
    }
    
}
