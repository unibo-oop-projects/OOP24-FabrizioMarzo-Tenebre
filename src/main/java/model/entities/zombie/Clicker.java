package model.entities.zombie;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;
import model.physics.physics_entities.PhysicsZombieComponent;

public class Clicker implements Zombie{

    private static final int MIN_HEALTH = 0;

    private long damageStartTime = 0;
    private static final long DAMAGE_DURATION_MS = 500;  // tempo di durata animazione in millisecondi
    private boolean inDamage = false;

    private int width;
    private int height;
    private int live;
    private int attack;
    private Pair<Double,Double> pos;
    private Pair<Double,Double> vel;
    private final Pair<Double,Double> velBase;
    private final int maxLife;
    private ZombieState state;
    private BoundingBox bbox;
    private PhysicsZombieComponent physicComp;


    public Clicker(final int health, final int attack,
                   final int width,final int height,
                   final Pair<Double, Double> pos, Pair<Double, Double> vel,
                   final PhysicsZombieComponent physicComp,
                   final BoundingBox bbox) {

        this.live = health;
        this.maxLife = health;
        this.attack = attack;
        this.width = width;
        this.height = height;
        this.pos = pos;
        this.vel = vel;
        this.velBase = Pair.of(vel.getLeft(), vel.getRight());
        this.state = ZombieState.ZOMBIE_IDLE;
        this.bbox = bbox;
        this.physicComp = physicComp;
    }

    @Override
    public int attack() {
        return this.attack;
    }

    @Override
    public void damageSuffer(final int dm) {
        this.live = Math.max(MIN_HEALTH, this.live - dm);

        // Entry on Damage state only when im not on state Damage
        if (!inDamage) {
            this.state = ZombieState.ZOMBIE_SUFFER_DAMAGE;
            this.damageStartTime = System.currentTimeMillis();
            this.inDamage = true;
        }
    }

    @Override
    public void updatePhysics(final int dt) {
        if (inDamage) {
            long now = System.currentTimeMillis();
            if (now - damageStartTime >= DAMAGE_DURATION_MS) {
                inDamage = false;
    
                // Return on IDLE state
                this.state = ZombieState.ZOMBIE_IDLE;
    
                // Reset the velocity base if he become to move
                this.vel = this.velBase;
            }
            return; // Don't do updatePhysics if im on damage state 
        }
    
        // If im not on damage state do the update
        physicComp.updateZombie(this, dt);
    }

    @Override
    public void setPosition(final Pair<Double, Double> pos) {
        this.pos = pos;
        this.bbox.updateBBox(pos);
    }

    @Override
    public void setVelocity(final Pair<Double, Double> vel) {
        this.vel = vel; 
    }
    
    @Override
    public void setState(final ZombieState newState) {
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
    public Pair<Double, Double> getBaseZombieVel() {
        return this.velBase;
    }
    
    @Override
    public ZombieState getState() {
        return this.state;
    }

    @Override
    public BoundingBox getBBox() {
        return this.bbox;
    }


    @Override
    public int getLive() {
        return this.live;
    }

    @Override
    public int getMaxZombieHealth() {
       return this.maxLife;
    }

    
}
