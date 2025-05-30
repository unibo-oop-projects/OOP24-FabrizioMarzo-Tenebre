package model.physics.physics_level;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.ai.behavior.AINPCBehavior;
import model.ai.behavior.FactoryAINPCBehavior;
import model.armory.munition.Munition;
import model.entities.EntitieState;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import model.level.Level;

public class PhysicsLevelTutComponent implements PhysicsLevelComponent {

    FactoryAINPCBehavior factAINPC = new FactoryAINPCBehavior();
    AINPCBehavior<Survivor,Zombie> baseAIZombie = factAINPC.createBaseNPCBehavior();
    private List<Munition> activeMunitions = new ArrayList<>();
    private List<Munition> newMunitions = new ArrayList<>();

    @Override
    public void updateLevel(final Level lv,final int dt) {

        this.checkSurvivorShoot(lv.getSurvivorOnLevel(), dt);
        
        System.out.println("Number of Munitions on level: " + this.activeMunitions.size());
        
        this.updateMunitions(dt);

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
        lv.getSurvivorOnLevel().setVelocity(Pair.of(0.0, 0.0));
    }
  

    private void checkSurvivorShoot(final Survivor sur, final int dt){
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

    private void updateMunitions(final int dt) {
        this.activeMunitions.stream()
                            .forEach(munition -> {
                                munition.moveShoot(dt);
                                System.out.println(munition.getBBox().getULcorner());
                            });
    }


}
