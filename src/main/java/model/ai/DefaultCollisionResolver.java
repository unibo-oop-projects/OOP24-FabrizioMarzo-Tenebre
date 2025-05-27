package model.ai;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.zombie.Zombie;
import utils.PairUtils;

public class DefaultCollisionResolver< Z extends Zombie> implements CollisionStrategy<Z>{

    private static final double OVERLAP = 1.0;

    @Override
    public void resolveCollisions(final Z zombie,final List<Z> others) {
        for (Z other : others) {
            if (other != zombie) {
                var bb1 = zombie.getBBox();
                var bb2 = other.getBBox();

                if (bb1.isColliding(bb2.getULcorner(), bb2.getBRcorner())) {
                    Pair<Double, Double> diff = PairUtils.diff(zombie.getCurrentPos(), other.getCurrentPos());

                    if (PairUtils.norm2(diff) == 0) {
                        diff = Pair.of(Math.random() - 0.5, Math.random() - 0.5);
                    }

                    Pair<Double, Double> dir = PairUtils.normalize(diff);
                    Pair<Double, Double> correction = PairUtils.mulScale(dir, OVERLAP / 2.0);

                    Pair<Double, Double> newPosA = PairUtils.sum(zombie.getCurrentPos(), correction);
                    Pair<Double, Double> newPosB = PairUtils.diff(other.getCurrentPos(), correction);

                    zombie.setPosition(newPosA);
                    other.setPosition(newPosB);
                }
            }
        }
    }
    
}
