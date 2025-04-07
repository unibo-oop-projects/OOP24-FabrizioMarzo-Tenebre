package model.entities.survivor;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Common implements Survivor{

    private int live;
    private int attack;
    private Pair<Double,Double> pos;
    private Pair<Double,Double> vel;


    public Common(final Pair<Double,Double> pos, final Pair<Double,Double> vel, final int live,final int attack) {
        this.live = live;
        this.attack = attack;
        this.pos = new MutablePair<>(pos.getLeft(),pos.getRight());
        this.vel = new MutablePair<>(vel.getLeft(),vel.getRight());

    }


    @Override
    public int attack() {
        return this.attack;
    }

    @Override
    public void damage(final int dm) {
        this.live = this.live-dm;
    }

    public int getLive() {
        return this.live;
    }

    @Override
    public void updateState(int dt) {
    }

    @Override
    public Pair<Double, Double> getCurrentPos() {
        return this.pos;
    }


    @Override
    public Pair<Double, Double> getCurrentVel() {
        return this.vel;
    }
    
}
