package model.physics.physics_level;


import org.apache.commons.lang3.tuple.Pair;

import model.ai.behavior.AINPCBehavior;
import model.ai.behavior.AIZombieBehavior;
import model.ai.collision.DefaultCollisionResolver;
import model.ai.movement.DefaultMovementStrategy;
import model.ai.separation.DefaultSeparationBehavior;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import model.level.Level;

public class PhysicsLevelTutComponent implements PhysicsLevelComponent {

    AINPCBehavior<Survivor,Zombie> smartAI;

    public PhysicsLevelTutComponent(){
        this.smartAI = new AIZombieBehavior<>(new DefaultMovementStrategy<>(),
                                              new DefaultSeparationBehavior<>(), 
                                              new DefaultCollisionResolver<>());
    }
    
    @Override
    public void updateLevel(final Level lv,final int dt) {
        lv.getSurvivorOnLevel().updatePhysics(dt);
        lv.getZombieOnLevel().stream()
                             .forEach(zombie -> {
                                this.smartAI.updateAINPC(lv.getSurvivorOnLevel(), zombie, lv.getZombieOnLevel());
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
        lv.getSurvivorOnLevel().setVelocity(Pair.of(0.0, 0.0));
    }
  
}
