package model.physics.physics_level;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import model.ai.behavior.AINPCBehavior;
import model.ai.behavior.FactoryAINPCBehavior;
import model.armory.munition.Munition;
import model.bounding_box.BoundingBox;
import model.bounding_box.RectBoundingBox;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import model.level.Level;

public class PhysicsLevelTutComponent implements PhysicsLevelComponent {

    private static final Pair<Double, Double> ZERO_VELOCITY = Pair.of(0.0, 0.0);

    FactoryAINPCBehavior factAINPC = new FactoryAINPCBehavior();
    AINPCBehavior<Survivor,Zombie> baseAIZombie = factAINPC.createBaseNPCBehavior();

    @Override
    public void updateLevel(final Level lv,final int dt) {

        
        System.out.println("Numbers of Munitions on level: " + lv.getProjectilesOnLevel().size());
        System.out.println("Numbers of Zombies on Level: " + lv.getZombieOnLevel().size());
        
        this.updateMunitions(dt,lv);
        this.checkCollisionsProjectilesZombies(lv);

        lv.getSurvivorOnLevel().updatePhysics(dt);
        lv.getZombieOnLevel().stream()
                             .forEach(zombie -> {
                                this.baseAIZombie.updateAINPC(lv.getSurvivorOnLevel(), zombie, lv.getZombieOnLevel());
                                zombie.updatePhysics(dt);
                             });

        if (isOutsideLevelBounds(lv)) {
            resetSurvivorToValidPosition(lv);
        }
    }

    private boolean isOutsideLevelBounds(final Level lv) {

        Pair<Double, Double> checkUL = Pair.of(
            lv.getSurvivorOnLevel().getBBox().getULcorner().getLeft() + lv.getSurvivorOnLevel().getWidth(),
            lv.getSurvivorOnLevel().getBBox().getULcorner().getRight() - lv.getSurvivorOnLevel().getHeight()
        );
    
        Pair<Double, Double> checkBR = Pair.of(
            lv.getSurvivorOnLevel().getBBox().getBRcorner().getLeft() - lv.getSurvivorOnLevel().getWidth(),
            lv.getSurvivorOnLevel().getBBox().getBRcorner().getRight() + lv.getSurvivorOnLevel().getHeight()
        );

        var bbox = new RectBoundingBox(checkUL, checkBR);
    
        return !lv.getLevelBBox().isColliding(bbox);
    }

    private void resetSurvivorToValidPosition(final Level lv) {
        System.out.println("Final level");
    
        double posX = lv.getSurvivorOnLevel().getCurrentPos().getLeft();
        double posY = lv.getSurvivorOnLevel().getCurrentPos().getRight();
    
        double levelWidth = lv.getLevelWidth();
        double levelHeight = lv.getLevelHeight();
    
        int survivorWidth = lv.getSurvivorOnLevel().getWidth();
        int survivorHeight = lv.getSurvivorOnLevel().getHeight();
    
        posX = Math.max(0, Math.min(posX, levelWidth - survivorWidth));
        posY = Math.max(0, Math.min(posY, levelHeight - survivorHeight));
    
        lv.getSurvivorOnLevel().setPosition(Pair.of(posX, posY));
        lv.getSurvivorOnLevel().setVelocity(ZERO_VELOCITY);
    }
  
    private Boolean checkIfMunitionIsOut(final Munition munition, final Level lv) {
        BoundingBox levelBox = lv.getLevelBBox();
        BoundingBox munBox = munition.getBBox();

        boolean isInside = levelBox.isColliding(munBox);

        return !isInside;
    }

    private void updateMunitions(final int dt, final Level lv) {
        lv.getProjectilesOnLevel().stream()
                            .forEach(munition -> munition.moveShoot(dt));
                            
        lv.getProjectilesOnLevel().removeIf(munition -> this.checkIfMunitionIsOut(munition, lv));
    }

    private void checkCollisionsProjectilesZombies(final Level lv) {

        var zombies = lv.getZombieOnLevel();
        var projectiles = lv.getProjectilesOnLevel();

        Set<Munition> toRemoveProjectiles = new HashSet<>();

        projectiles.stream().forEach(munition -> 
            zombies.stream()
                .filter(zob -> munition.getBBox().isColliding(zob.getBBox()))
                .forEach(zob -> {
                    zob.damageSuffer(munition.getDamage());
                    toRemoveProjectiles.add(munition); 
                })   
        );

        projectiles.removeIf(toRemoveProjectiles::contains);

        zombies.removeIf(Zombie::isZombieDead);
    }
}
