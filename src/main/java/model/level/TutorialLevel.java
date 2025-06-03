package model.level;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.armory.munition.Munition;
import model.armory.weapon.FactoryWeapon;
import model.bounding_box.BoundingBox;
import model.entities.EntitieState;
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

    private static final int ZOMBIES_LEVEL = 10;
    
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

    private List<Munition> activeMunitions = new ArrayList<>();
    private List<Munition> newMunitions = new ArrayList<>();


    private FactoryWeapon factWeapon = new FactoryWeapon();

    private Survivor surLv;
    private List<Zombie> listZombie;

    
    public TutorialLevel(final double lvlWidth,final double lvlHeight,
                         final BoundingBox bbox,
                         final PhysicsLevelComponent physcLevel){
        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;
        this.bbox = bbox;
        this.physicLvComp = physcLevel;
        this.setSurvivorOnLevel();
        this.setZombieOnLevel();
        this.setWeaponOnSurvivor();
    }
    
    private void setSurvivorOnLevel(){
        this.surLv = surFact.createCommonSurvivor(Pair.of(1000.0,1000.0));
    }

    private Pair<Double,Double> posZombie(){
        double randomNumW = ThreadLocalRandom.current().nextDouble(-this.lvlWidth, 2 * this.lvlWidth);
        double randomNumH = ThreadLocalRandom.current().nextDouble(-this.lvlHeight, 2 * this.lvlHeight);
        return Pair.of(randomNumW, randomNumH);
    }

    private void setZombieOnLevel(){
        this.listZombie = IntStream.range(0, ZOMBIES_LEVEL)
                            .mapToObj(i -> zobFact.createClickerZombie(posZombie()))
                            .collect(Collectors.toList());
    }

    private void checkSurvivorAttack(final Survivor sur, final int dt){
        if (sur.getState() == EntitieState.ATTACK) {
            System.out.println("The Survivor is Shooting");
            this.newMunitions.addAll(sur.getWeapon().shoot(dt));
            System.out.println("!!!!!!!!!!!!!!!!!!" + this.newMunitions);
            if (!newMunitions.isEmpty()) {
                this.activeMunitions.addAll(newMunitions);
                System.out.println("New Munitions are: " + newMunitions.size());
            }
            this.newMunitions.clear();
        }
    }

    @Override
    public void updateLevelState(final int dt){
        this.checkSurvivorAttack(surLv, dt);
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

    @Override
    public void setWeaponOnSurvivor() {
        this.getSurvivorOnLevel().setWeapon(factWeapon.createPistol(this.getSurvivorOnLevel().getCurrentPos()));
    }

    @Override
    public List<Munition> getProjectilesOnLevel(){
        return this.activeMunitions;
    }

}
