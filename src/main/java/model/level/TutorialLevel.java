package model.level;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;


import model.armory.munition.Munition;
import model.armory.weapon.FactoryWeapon;
import model.bounding_box.BoundingBox;
import model.entities.EntitieState;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorFactory;
import model.entities.zombie.Zombie;
import model.level.manager.LevelManager;
import model.level.manager.LevelManagerBase;
import model.physics.physics_level.PhysicsLevelComponent;


public class TutorialLevel implements Level {

    
    private double lvlWidth;   
    private double lvlHeight;  
    private BoundingBox bbox;
    private PhysicsLevelComponent physicLvComp;
    private LevelManager levelManager;

    private SurvivorFactory surFact = new SurvivorFactory();

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
        this.listZombie = new ArrayList<>();
        this.setSurvivorOnLevel();
        this.setWeaponOnSurvivor();
        this.levelManager = new LevelManagerBase(this);
    }
    
    private void setSurvivorOnLevel(){
        this.surLv = surFact.createCommonSurvivor(Pair.of(1000.0,1000.0));
    }

    private void checkSurvivorAttack(final Survivor sur, final int dt){
        if (sur.getState() == EntitieState.ATTACK) {
            this.newMunitions.addAll(sur.getWeapon().shoot(dt));
            if (!newMunitions.isEmpty()) {
                this.activeMunitions.addAll(newMunitions);
            }
            this.newMunitions.clear();
        }
    }

    @Override
    public void updateLevelState(final int dt){
        this.checkSurvivorAttack(surLv, dt);
        this.physicLvComp.updateLevel(this, dt);
        this.levelManager.update(dt);
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
