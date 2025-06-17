package model.level.types;

import java.util.ArrayList;
import java.util.List;

import model.armory.munition.Munition;
import model.bounding_box.BoundingBox;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import model.level.manager.LevelManager;
import model.level.manager.LevelManagerBase;
import model.physics.physics_level.PhysicsLevelComponent;


public class LevelTutorial implements Level {

    private double lvlWidth;   
    private double lvlHeight;  
    private BoundingBox bbox;
    private PhysicsLevelComponent physicLvComp;
    private LevelManager levelManager;
    private boolean isLevelCompleted;

    private Survivor surLv;
    private List<Munition> activeMunitions = new ArrayList<>();
    private List<Zombie> listZombie;

    public LevelTutorial(final double lvlWidth,final double lvlHeight,
                         final BoundingBox bbox,
                         final PhysicsLevelComponent physcLevel){
        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;
        this.bbox = bbox;
        this.physicLvComp = physcLevel;
        this.isLevelCompleted = false;
        this.listZombie = new ArrayList<>();
        this.levelManager = new LevelManagerBase(this);
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

    @Override
    public void setSurvivorOnLevel(final Survivor sur) {
        this.surLv = sur;
    }

    @Override
    public boolean isLevelCompleted() {
        return this.isLevelCompleted;
    }

    @Override
    public void setLevelCompleted(final boolean completed) {
        this.isLevelCompleted = completed;
        
    }
}
