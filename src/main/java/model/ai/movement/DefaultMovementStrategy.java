package model.ai.movement;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import model.entities.zombie.ZombieState;
import utils.PairUtils;

public class DefaultMovementStrategy<Z extends Zombie, S extends Survivor> implements MovementStrategy<Z,S> {

    private static final Pair<Double, Double> ZERO_VELOCITY = Pair.of(0.0, 0.0);


    @Override
    public Pair<Double,Double> computeVelocity(final Zombie zombie,final Survivor target) {

        if (zombie.getState() == ZombieState.ZOMBIE_SUFFER_DAMAGE) {
            return ZERO_VELOCITY;
        }

        Pair<Double, Double> direction = PairUtils.diff(target.getCurrentPos(), zombie.getCurrentPos());
        double distance = PairUtils.norm2(direction);

        if (distance == 0) {
            zombie.setState(ZombieState.ZOMBIE_IDLE);
            return ZERO_VELOCITY;
        }

        Pair<Double, Double> normalizedDirection = PairUtils.normalize(direction);

        double dx = direction.getLeft();
        double dy = direction.getRight();

        if (Math.abs(dx) > Math.abs(dy)) {
            zombie.setState(dx < 0 ? ZombieState.ZOMBIE_MOVE_RIGHT : ZombieState.ZOMBIE_MOVE_LEFT);
        } else {
            zombie.setState(dy < 0 ? ZombieState.ZOMBIE_MOVE_DOWN : ZombieState.ZOMBIE_MOVE_UP);
        }

        double speed = PairUtils.norm2(zombie.getBaseZombieVel());
        return PairUtils.mulScale(normalizedDirection, speed);
    }
    
}
