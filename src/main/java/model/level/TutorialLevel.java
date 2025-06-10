package model.level;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;


import model.armory.munition.Munition;
import model.armory.weapon.FactoryWeapon;
import model.bounding_box.BoundingBox;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorFactory;
import model.entities.survivor.ISurvivorFactory;
import model.entities.zombie.Zombie;
import model.level.manager.LevelManager;
import model.level.manager.LevelManagerBase;
import model.physics.physics_level.PhysicsLevelComponent;


public class TutorialLevel implements Level {

    private static final Pair<Double, Double> SURVIVOR_START_POS = Pair.of(1000.0, 1000.0);

    private double lvlWidth;   
    private double lvlHeight;  
    private BoundingBox bbox;
    private PhysicsLevelComponent physicLvComp;
    private LevelManager levelManager;

    private ISurvivorFactory surFact = new SurvivorFactory();
    private Survivor surLv;
    private FactoryWeapon factWeapon = new FactoryWeapon();
    private List<Munition> activeMunitions = new ArrayList<>();
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
        this.levelManager = new LevelManagerBase(this);
    }
    
    private void setSurvivorOnLevel(){
        this.surLv = surFact.createCommonSurvivor(Pair.of(SURVIVOR_START_POS));
        this.surLv.setWeapon(factWeapon.createPistol(this.surLv.getCurrentPos()));
    }

    @Override
    public void updateLevelState(final int dt){
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
    public List<Munition> getProjectilesOnLevel(){
        return this.activeMunitions;
    }
}
