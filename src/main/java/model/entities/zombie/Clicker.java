package model.entities.zombie;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;
import model.entities.EntitieState;
import model.entities.survivor.Survivor;
import model.physics.physics_entities.PhysicsZombieComponent;

public class Clicker implements Zombie{

    private int width;
    private int height;
    private int live;
    private int attack;
    private Pair<Double,Double> pos;
    private Pair<Double,Double> vel;
    private EntitieState state;
    private BoundingBox bbox;
    private PhysicsZombieComponent physicComp;


    public Clicker(final int health, final int attack,
                   final int width,final int height,
                   final Pair<Double, Double> pos, Pair<Double, Double> vel,
                   final PhysicsZombieComponent physicComp,
                   final BoundingBox bbox) {

        this.live = health;
        this.attack = attack;
        this.width = width;
        this.height = height;
        this.pos = pos;
        this.vel = vel;
        this.state = EntitieState.IDLE;
        this.bbox = bbox;
        this.physicComp = physicComp;
    }

    @Override
    public int attack() {
        return this.attack;
    }

    @Override
    public void damageSuffer(final int dm) {
        this.live = this.live - dm;
    }

    @Override
    public void updatePhysics(final int dt, final Survivor sur) {
        physicComp.updateZombie(this, sur, dt);
    }

    @Override
    public void setPosition(final Pair<Double, Double> pos) {
        this.pos = pos;
        this.updateBBox(pos);
    }

    private void updateBBox(final Pair<Double, Double> newPos) {
        this.bbox.setUlcorner(Pair.of(newPos.getLeft(),newPos.getRight()+this.height));
        this.bbox.setBRcorner(Pair.of(newPos.getLeft()+this.width ,newPos.getRight()));
    }

    @Override
    public void setState(final EntitieState newState) {
        this.state = newState;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
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
    public EntitieState getState() {
        return this.state;
    }

    @Override
    public BoundingBox getBBox() {
        return this.bbox;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + width;
        result = prime * result + height;
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
        Clicker other = (Clicker) obj;
        if (width != other.width)
            return false;
        if (height != other.height)
            return false;
        if (live != other.live)
            return false;
        if (attack != other.attack)
            return false;
        return true;
    }
    
}
