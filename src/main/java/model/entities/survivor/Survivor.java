package model.entities.survivor;

import org.apache.commons.lang3.tuple.Pair;

public interface Survivor {
    public int attack();
    public void damage(final int dm);
    public int getLive();

    public void updateState(final int dt);
    public Pair<Double,Double> getCurrentPos();
    public Pair<Double,Double> getCurrentVel();
}
