package model.entities;

import org.apache.commons.lang3.tuple.Pair;

public class PairUtils {

    public static Pair<Double, Double> sum(Pair<Double, Double> pair1, Pair<Double, Double> pair2) {
        double x = pair1.getLeft() + pair2.getLeft();
        double y = pair1.getRight() + pair2.getRight();
        return Pair.of(x, y);
    }

    public static Pair<Double, Double> mul(Pair<Double, Double> pair, double value) {
        double x = pair.getLeft() * value;
        double y = pair.getRight() * value;
        return Pair.of(x, y);
    } 
}
