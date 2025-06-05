package model.entities.survivor;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;
import model.armory.weapon.Weapon;
import model.bounding_box.BoundingBox;
import model.entities.EntitieState;
import model.physics.physics_entities.PhysicsSurvivorComponent;

/**
 * Implementation of the {@link Survivor} interface.
 * <p>
 * This class represents a common type of survivor in the game with
 * basic movement, attack capabilities, and state management.
 */
public class Common implements Survivor{

    private int width;
    private int height;
    private int live;
    private int attack;
    private Pair<Double,Double> pos;
    private Pair<Double,Double> vel;
    private final Pair<Double,Double> velBase;
    private EntitieState state;
    private BoundingBox bbox;
    private PhysicsSurvivorComponent physicComp;
    private Weapon weapon;

    /**
     * Constructs a new {@code Common} survivor with specified attributes.
     *
     * @param health   initial health
     * @param attack attack strength of the survivor
     * @param pos    initial position (x, y)
     * @param vel    initial velocity (vx, vy); also used as base velocity
     */
    public Common(final int health,final int attack, 
                 final int width, final int height,
                 final Pair<Double,Double> pos,final Pair<Double,Double> vel,
                 final PhysicsSurvivorComponent physicComp,
                 final BoundingBox bbox) {

        this.live = health;
        this.attack = attack;
        this.width = width;
        this.height = height;
        this.pos = pos;
        this.vel = vel;
        this.velBase = vel;
        this.state = EntitieState.IDLE;
        this.bbox = bbox;
        this.physicComp = physicComp; 
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
        this.live = Math.max(0, this.live - dm);
    }
    
    @Override
    public void updatePhysics(final int dt){
        physicComp.updateSurvivor(this, dt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final Pair<Double, Double> vel) {
        this.vel = vel; 
    }
    
    @Override
    public void setPosition(final Pair<Double, Double> pos) {
        this.pos = pos;
        this.bbox.updateBBox(pos);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(final EntitieState newState) {
        this.state = newState;
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLive() {
        return this.live;
    }


    @Override
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public int getHeight() {
        return this.height;
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
    public Pair<Double, Double> getBaseSurvivorVel() {
        return this.velBase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitieState getState() {
        return this.state;
    }

    @Override
    public Weapon getWeapon() {
        return this.weapon;
    }


    @Override
    public BoundingBox getBBox() {
        return this.bbox;
    }

    @Override
    public List<Munition> shoot(final double deltaTime) {
        return this.weapon != null ? this.weapon.shoot(deltaTime) : List.of();
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
    public boolean equals(final Object obj) {
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
