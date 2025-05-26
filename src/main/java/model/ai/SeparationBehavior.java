package model.ai;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.zombie.Zombie;
import utils.PairUtils;

public class SeparationBehavior< T extends Zombie > {

    private static final double MAX_FORCE = 0.5;
    private static final int VALUE = 2; // Is how moltiplicate the distance 

    public Pair<Double, Double> computeForce(T npc, List<T> npcs) {
        Pair<Double, Double> separationForce = Pair.of(0.0, 0.0);
        int countNPCnear = 0;
        double minSeparationDist = npc.getWidth() * VALUE;

        for (T other : npcs) {
            if (other != npc) {
                Pair<Double, Double> diff = PairUtils.diff(npc.getCurrentPos(), other.getCurrentPos());
                double dist = PairUtils.norm2(diff);

                if (dist < minSeparationDist && dist > 0) {
                    Pair<Double, Double> normalizedDiff = PairUtils.normalize(diff);
                    Pair<Double, Double> force = PairUtils.mulScale(normalizedDiff, MAX_FORCE);
                    separationForce = PairUtils.sum(separationForce, force);
                    countNPCnear++;
                }
            }
        }

        if (countNPCnear > 0) {
            separationForce = PairUtils.mulScale(separationForce, 1.0 / countNPCnear);
        }
        return separationForce;
    }
}
