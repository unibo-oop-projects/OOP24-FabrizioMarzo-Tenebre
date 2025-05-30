package model.armory.munition;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;
import utils.PairUtils;

public class Parabellum implements Munition{

    private int damage;
    private int width;
    private int velocity;
    private Boolean isShoot = false;
    private Pair<Double,Double> pos;
    private Optional<Pair<Double, Double>> dir;
    private BoundingBox bbox;


    public Parabellum(final int damage, final int velocity ,final int width,final Pair<Double, Double> pos,final BoundingBox bbox) {
        this.damage = damage;
        this.width = width;
        this.velocity = velocity;
        this.pos = pos;
        this.bbox = bbox;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public BoundingBox getBBox() {
        return this.bbox;
    }

    @Override
    public void setShoot(final Pair<Double,Double> dirShoot, final Pair<Double,Double> posShoot) {
        this.dir = Optional.ofNullable(PairUtils.normalize(dirShoot));
        this.pos = posShoot;
        this.isShoot = true;
    }

    @Override
    public void moveShoot(final int dt) {
        Pair<Double, Double> direction = dir.orElseThrow(() -> 
        new IllegalStateException("Direction not inizialize"));

        Pair<Double, Double> displacement = nextPos(dt, PairUtils.mulScale(direction, this.velocity));
        this.setPos(PairUtils.sum(this.pos, displacement));
        this.updateBBox(this.pos);
    }

    @Override
    public Boolean isShoot() {
        return this.isShoot;
    }

    @Override
    public void setPos(final Pair<Double,Double> nextPos){
        this.pos = nextPos;
    }

    private Pair<Double,Double> nextPos(final int dt, final Pair<Double,Double> vel){
        return PairUtils.mulScale(vel,0.001*dt);
    }

    private void updateBBox(final Pair<Double, Double> newPos) {
        this.bbox.setUlcorner(Pair.of(newPos.getLeft(),newPos.getRight()+this.width));
        this.bbox.setBRcorner(Pair.of(newPos.getLeft()+this.width ,newPos.getRight()));
    }

    
}
