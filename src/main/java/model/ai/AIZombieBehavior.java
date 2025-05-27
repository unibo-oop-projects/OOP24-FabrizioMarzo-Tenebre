package model.ai;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import utils.PairUtils;

public class AIZombieBehavior<S extends Survivor, Z extends Zombie> implements AINPCBehavior<S, Z> {

    private static final double SEPARATION_WEIGHT = 2.0;

    private final MovementStrategy<Z,S> movStrategy;
    private final SeparationStrategy<Z> sepStrategy;
    private final CollisionStrategy<Z> colStrategy;

    public AIZombieBehavior(MovementStrategy<Z, S> movStrategy, SeparationStrategy<Z> sepStrategy,
                            CollisionStrategy<Z> colStrategy) {
        this.movStrategy = movStrategy;
        this.sepStrategy = sepStrategy;
        this.colStrategy = colStrategy;
    }

    @Override
    public void updateAINPC(final S target, final Z npc, final List<Z> npcs) {
        for (Z z : npcs) {
            z.setVelocity(this.computeTotalEffect(z, target, npcs));
            colStrategy.resolveCollisions(npc, npcs);
        }
    }

    private Pair<Double, Double> computeTotalEffect(final Z npc, final S target, final List<Z> npcs) {
        Pair<Double, Double> targetVel = movStrategy.computeVelocity(npc, target);
        Pair<Double, Double> separationForce = sepStrategy.computeForce(npc, npcs);

        Pair<Double, Double> combinedVel = PairUtils.sum(
            targetVel, 
            PairUtils.mulScale(separationForce, SEPARATION_WEIGHT)
        );

        double maxSpeed = PairUtils.norm2(npc.getBaseZombieVel());
        double speed = PairUtils.norm2(combinedVel);

        if (speed > maxSpeed) {
            combinedVel = PairUtils.mulScale(PairUtils.normalize(combinedVel), maxSpeed);
        }

        return combinedVel;
    }
}
