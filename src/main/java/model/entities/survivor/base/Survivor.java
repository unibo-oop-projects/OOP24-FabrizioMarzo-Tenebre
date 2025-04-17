package model.entities.survivor.base;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.SurvivorState;

public interface Survivor {
    public int attack();
    public void damageSuffer(final int dm);
    
    public void updateState(final int dt);
    
    public Pair<Double,Double> getCurrentPos();
    public Pair<Double,Double> getCurrentVel();
    public Pair<Double,Double> getBaseSurvivorVel();
    public SurvivorState getState();
    public int getLive();

    public void setVelocity(final Pair<Double,Double> vel);
    public void setState(final SurvivorState newState);
}
