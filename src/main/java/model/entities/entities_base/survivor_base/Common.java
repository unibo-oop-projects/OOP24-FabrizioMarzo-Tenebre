package model.entities.entities_base.survivor_base;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import model.bounding_box.BoundingBox;
import model.bounding_box.RectBoundingBox;


/**
 * Implementation of the {@link Survivor} interface.
 * <p>
 * This class represents a common type of survivor in the game with
 * basic movement, attack capabilities, and state management.
 */
public class Common implements Survivor{

    private int live;
    private int attack;
    private final static int WIDTH=50;
    private final static int HEIGHT=175;
    private BoundingBox bbox;
    private Pair<Double,Double> pos;
    private Pair<Double,Double> vel;
    private final Pair<Double,Double> velBase;
    private SurvivorState state;

    /**
     * Constructs a new {@code Common} survivor with specified attributes.
     *
     * @param health   initial health
     * @param attack attack strength of the survivor
     * @param pos    initial position (x, y)
     * @param vel    initial velocity (vx, vy); also used as base velocity
     */
    public Common(final int health,final int attack, final Pair<Double,Double> pos, 
                    final Pair<Double,Double> vel) {
        this.live = health;
        this.attack = attack;
        this.pos = new MutablePair<>(pos.getLeft(),pos.getRight());
        this.vel = new MutablePair<>(vel.getLeft(),vel.getRight());
        this.velBase = vel;
        this.state = SurvivorState.IDLE;
        this.bbox = new RectBoundingBox(Pair.of(pos.getLeft(),pos.getRight()+HEIGHT),
                                        Pair.of(pos.getLeft()+WIDTH ,pos.getRight()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int attack() {
        return this.attack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void damageSuffer(final int dm) {
        this.live = this.live-dm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLive() {
        return this.live;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getCurrentPos() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getCurrentVel() {
        return this.vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SurvivorState getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getBaseSurvivorVel() {
        return this.velBase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final Pair<Double, Double> vel) {
        this.vel = vel; 
    }

    @Override
    public void setPosition(Pair<Double, Double> pos) {
        this.pos = pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(final SurvivorState newState) {
        this.state = newState;
    }
    
    /**
     * Generates a hash code based on survivor's health and attack.
     *
     * @return a hash code value for the object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + live;
        result = prime * result + attack;
        return result;
    }

    /**
     * Compares this survivor with another for equality.
     * Survivors are considered equal if they have the same health and attack values.
     *
     * @param obj the object to compare with
     * @return true if the two survivors are equal, false otherwise
     */
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

    @Override
    public BoundingBox getBBox() {
        return this.bbox;
    }

}
