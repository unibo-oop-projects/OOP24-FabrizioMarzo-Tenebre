package model.entities.survivor;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.entities.PairUtils;

public class Common implements Survivor{

    private int live;
    private int attack;
    private Pair<Double,Double> pos;
    private Pair<Double,Double> vel;

    public Common(final int live,final int attack, final Pair<Double,Double> pos, final Pair<Double,Double> vel) {
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
    public void damageSuffer(final int dm) {
        this.live = this.live-dm;
    }

    @Override
    public int getLive() {
        return this.live;
    }

    @Override
    public void updateState(int dt) {
        this.pos = PairUtils.sum(this.getCurrentPos(),nextPos(dt));
    }

    private Pair<Double,Double> nextPos(final int dt){
        return PairUtils.mul(this.vel,dt);
    }

    @Override
    public Pair<Double, Double> getCurrentPos() {
        return this.pos;
    }

    @Override
    public Pair<Double, Double> getCurrentVel() {
        return this.vel;
    }

    @Override
    public void setVelocity(Pair<Double, Double> vel) {
        this.vel = vel; 
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + live;
        result = prime * result + attack;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Common other = (Common) obj;
        if (live != other.live)
            return false;
        if (attack != other.attack)
            return false;
        return true;
    }
}
