package model.ai.separation;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.zombie.Zombie;
import utils.PairUtils;

public class DefaultSeparationBehavior<Z extends Zombie> implements SeparationStrategy<Z> {

    private static final double MAX_FORCE = 0.5;
    private static final int VALUE = 2; // // Minimum distance multiplier

    @Override
    public Pair<Double, Double> computeForce(Z zombie, List<Z> others) {
        Pair<Double, Double> separationForce = Pair.of(0.0, 0.0);
        int countNPCnear = 0;
        double minSeparationDist = zombie.getWidth() * VALUE;

        for (Z other : others) {
            if (other != zombie) {
                Pair<Double, Double> diff = PairUtils.diff(zombie.getCurrentPos(), other.getCurrentPos());
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
    

