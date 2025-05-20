package model.level;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.ThreadLocalRandom;
import model.bounding_box.BoundingBox;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorFactory;
import model.entities.zombie.Zombie;
import model.entities.zombie.ZombieFactory;
import model.physics.physics_level.PhysicsLevelComponent;

/**
 * Represents the tutorial level of the game.
 * <p>
 * This level contains a single common survivor and initializes it with
 * predefined attributes such as health, attack power, position, and velocity.
 * It also handles updating the survivor's internal state over time.
 */
public class TutorialLevel implements Level {

    // Size of the Level
    private double lvlWidth;   
    private double lvlHeight;  

    // Level Bouding Box
    private BoundingBox bbox;
    // The Survivor Factory
    private SurvivorFactory surFact = new SurvivorFactory();
    // The only Zombie on the game
    private ZombieFactory zobFact = new ZombieFactory();
    // The physic on lthe level 
    private PhysicsLevelComponent physicLvComp;

    private Survivor surLv;
    private Zombie zobLv;
    private List<Zombie> listZombie = new ArrayList<>();

    
    public TutorialLevel(final double lvlWidth,final double lvlHeight,
                         final BoundingBox bbox,
                        final PhysicsLevelComponent physcLevel){
        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;
        this.bbox = bbox;
        this.physicLvComp = physcLevel;
        this.setSurvivorOnLevel();
        this.setZombieOnLevel();
    }
    
    private void setSurvivorOnLevel(){
        this.surLv = surFact.createCommonSurvivor(1000,20, Pair.of(1000.0,1000.0),Pair.of(200.0,0.0));
    }

    private void setZombieOnLevel(){
        for (int i=0 ; i < 4 ; i++){
            double randomNumW = ThreadLocalRandom.current().nextDouble(0.0, this.lvlWidth);
            double randomNumH = ThreadLocalRandom.current().nextDouble(0.0, this.lvlHeight);
            listZombie.add(zobFact.createClickerZombie(1000,20, Pair.of(randomNumW,randomNumH),Pair.of(150.0,0.0)));
        }
    }

    @Override
    public void updateLevelState(final int dt){
        this.surLv.updatePhysics(dt);
        this.listZombie.stream()
                        .forEach(zombie -> zombie.updatePhysics( dt, this.getSurvivorOnLevel()));
        this.zobLv.updatePhysics(dt,this.getSurvivorOnLevel());
        physicLvComp.updateLevel(this, dt);
    }

    @Override
    public double getLevelWidth() {
        return this.lvlWidth;
    }

    @Override
    public double getLevelHeight() {
        return this.lvlHeight;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Updates the survivor's internal state based on the elapsed time.
     *
     * @param dt the time delta in milliseconds since the last update
     */
    @Override
    public BoundingBox getLevelBBox() {
        return this.bbox;
    }

    /**
     * {@inheritDoc}
     * 
     * @return the survivor entity present in this tutorial level
     */
    @Override
    public Survivor getSurvivorOnLevel(){
        return this.surLv;
    }

    @Override
    public List<Zombie> getZombieOnLevel(){
        return this.listZombie;
    }

}
