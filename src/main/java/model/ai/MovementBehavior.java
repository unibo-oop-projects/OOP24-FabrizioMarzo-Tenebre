package model.ai;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.EntitieState;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import utils.PairUtils;

public class MovementBehavior<T extends Zombie > {
    
   public <E extends Survivor > Pair<Double, Double> computeVelocity(T npc, E target) {
        Pair<Double, Double> direction = PairUtils.diff(target.getCurrentPos(), npc.getCurrentPos());
        double distance = PairUtils.norm2(direction);

        if (distance == 0) {
            npc.setState(EntitieState.IDLE);
            return Pair.of(0.0, 0.0);
        }

        Pair<Double, Double> normalizedDirection = PairUtils.normalize(direction);

        double dx = direction.getLeft();
        double dy = direction.getRight();

        if (Math.abs(dx) > Math.abs(dy)) {
            npc.setState(dx < 0 ? EntitieState.MOVE_RIGHT : EntitieState.MOVE_LEFT);
        } else {
            npc.setState(dy < 0 ? EntitieState.MOVE_DOWN : EntitieState.MOVE_UP);
        }

        double speed = PairUtils.norm2(npc.getBaseZombieVel());
        return PairUtils.mulScale(normalizedDirection, speed);
    }
    
}
