package model.physics.physics_level;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.ai.behavior.AINPCBehavior;
import model.ai.behavior.FactoryAINPCBehavior;
import model.armory.munition.Munition;
import model.bounding_box.BoundingBox;
import model.entities.EntitieState;
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
    
        return !lv.getLevelBBox().isColliding(checkUL, checkBR);
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

        boolean isInside = levelBox.isColliding(
            munBox.getULcorner(),
            munBox.getBRcorner()
        );

        return !isInside;
    }

    private void updateMunitions(final int dt, final Level lv) {
        lv.getProjectilesOnLevel().stream()
                            .forEach(munition -> {
                                munition.moveShoot(dt);
                                System.out.println(munition.getBBox().getULcorner());
                            });
                            
        lv.getProjectilesOnLevel().removeIf(munition -> this.checkIfMunitionIsOut(munition, lv));
    }

    private void checkCollisionsProjectilesZombies(final Level lv) {
        var projectiles = lv.getProjectilesOnLevel();
        var zombies = lv.getZombieOnLevel();

        List<Zombie> toRemove = new ArrayList<>();

        var projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Munition munition = projectileIterator.next();

            for (Zombie zombie : zombies) {
                if (munition.getBBox().isColliding(zombie.getBBox().getULcorner(), zombie.getBBox().getBRcorner())) {
                    
                    zombie.damageSuffer(munition.getDamage());

                    projectileIterator.remove();

                    if (zombie.getLive() <= 0) {
                        toRemove.add(zombie);
                    }
                    break;
                }   
            }
        }

        zombies.removeAll(toRemove);
    }
}
