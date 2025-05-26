package model.ai;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.zombie.Zombie;
import utils.PairUtils;

public class CollisionResolver< T extends Zombie > {

    private static final double OVERLAP = 1.0;


    public void resolveCollisions(T npc, List<T> npcs) {
        for (T other : npcs) {
            if (other != npc) {
                var bb1 = npc.getBBox();
                var bb2 = other.getBBox();

                if (bb1.isColliding(bb2.getULcorner(), bb2.getBRcorner())) {
                    Pair<Double, Double> diff = PairUtils.diff(npc.getCurrentPos(), other.getCurrentPos());

                    if (PairUtils.norm2(diff) == 0) {
                        diff = Pair.of(Math.random() - 0.5, Math.random() - 0.5);
                    }

                    Pair<Double, Double> dir = PairUtils.normalize(diff);

                    Pair<Double, Double> correction = PairUtils.mulScale(dir, OVERLAP / 2.0);

                    Pair<Double, Double> newPosA = PairUtils.sum(npc.getCurrentPos(), correction);
                    Pair<Double, Double> newPosB = PairUtils.diff(other.getCurrentPos(), correction);

                    npc.setPosition(newPosA);
                    other.setPosition(newPosB);
                }
            }
        }
    }
}
