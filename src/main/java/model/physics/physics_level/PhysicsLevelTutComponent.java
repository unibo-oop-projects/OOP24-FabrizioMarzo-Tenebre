package model.physics.physics_level;


import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.EntitieState;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import model.level.Level;
import utils.PairUtils;

public class PhysicsLevelTutComponent implements PhysicsLevelComponent {

    private static final double MAX_FORCE = 0.5;

    @Override
    public void updateLevel(final Level lv,final int dt) {
        lv.getSurvivorOnLevel().updatePhysics(dt);
        lv.getZombieOnLevel().stream()
                             .forEach(zombie -> {
                                this.updateZombieAI(lv, zombie);
                                zombie.updatePhysics(dt);
                                resolveZombieCollisions(lv.getZombieOnLevel(), zombie);
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

    private Pair<Double,Double> computeSeparationForce(List<Zombie> listZombie, Zombie zombie) {
        Pair<Double, Double> separationForce = Pair.of(0.0, 0.0);
        int count = 0;

        double minSeparationDist = zombie.getWidth() * 2;

        for (Zombie other : listZombie) {
            if (other != zombie) {

                Pair<Double, Double> diff = PairUtils.diff(zombie.getCurrentPos(), other.getCurrentPos());
                double dist = PairUtils.norm2(diff);

                if (dist < minSeparationDist && dist > 0) {
                    
                    Pair<Double, Double> normalizedDiff = PairUtils.normalize(diff);

                    Pair<Double, Double> force = PairUtils.mulScale(normalizedDiff, MAX_FORCE);

                    separationForce = PairUtils.sum(separationForce, force);
                    count++;
                }
            }
        }

        if (count > 0) {
            separationForce = PairUtils.mulScale(separationForce, 1.0 / count);
        }

        return separationForce;
    }


    private Pair<Double,Double> moveZombieTowardsSurvivor(final Zombie zob, final Survivor sur){
        // Calcolate The difference between the Survivor Position and the Zombie Position
        Pair<Double,Double> difPos = PairUtils.diff(sur.getCurrentPos(), zob.getCurrentPos());

        double distanceNorm2 = PairUtils.norm2(difPos);

        if ((int) distanceNorm2 == 0) {
            // If the distance is 0 change the State of the Zombie
            zob.setState(EntitieState.IDLE);
        }
        
        // Normalize direction vector
        Pair<Double,Double> direction = PairUtils.normalize(difPos);

        // Set zombie state based on movement direction
        double dx = difPos.getLeft();
        double dy = difPos.getRight();

        if (Math.abs(dx) > Math.abs(dy)) {
            zob.setState(dx < 0 ? EntitieState.MOVE_RIGHT : EntitieState.MOVE_LEFT);
        } else {
            zob.setState(dy < 0 ? EntitieState.MOVE_DOWN : EntitieState.MOVE_UP);
        }

        // Compute movement velocity
        double speed = PairUtils.norm2(zob.getBaseZombieVel());
        Pair<Double,Double> velocity =  PairUtils.mulScale(direction, speed);
        return velocity;
    }
    

    private void updateZombieAI(Level lv, Zombie zombie) {
    
        Pair<Double, Double> targetVel = moveZombieTowardsSurvivor(zombie, lv.getSurvivorOnLevel());

    
        Pair<Double, Double> separationForce = computeSeparationForce(lv.getZombieOnLevel(), zombie);
 
        double separationWeight = 2.0;
 
        
        Pair<Double, Double> combinedVel = PairUtils.sum(targetVel, PairUtils.mulScale(separationForce, separationWeight));
 
    
        double maxSpeed = PairUtils.norm2(zombie.getBaseZombieVel());
        double speed = PairUtils.norm2(combinedVel);
 
        if (speed > maxSpeed) {
             combinedVel = PairUtils.mulScale(PairUtils.normalize(combinedVel), maxSpeed);
        }
 
        zombie.setVelocity(combinedVel);
    }

    private void resolveZombieCollisions(List<Zombie> listZombie, Zombie zombie) {

        for (Zombie other : listZombie) {
            if (other != zombie) {
                var bb1 = zombie.getBBox();
                var bb2 = other.getBBox();
    
                if (bb1.isColliding(bb2.getULcorner(), bb2.getBRcorner())) {
                
                    Pair<Double, Double> diff = PairUtils.diff(zombie.getCurrentPos(), other.getCurrentPos());
    
                
                    if (PairUtils.norm2(diff) == 0) {
                        diff = Pair.of(Math.random() - 0.5, Math.random() - 0.5);
                    }
    
                
                    Pair<Double, Double> dir = PairUtils.normalize(diff);
                    double overlap = 1.0; 
                    Pair<Double, Double> correction = PairUtils.mulScale(dir, overlap / 2.0);
    
                    Pair<Double, Double> newPosA = PairUtils.sum(zombie.getCurrentPos(), correction);
                    Pair<Double, Double> newPosB = PairUtils.diff(other.getCurrentPos(), correction);
    
                    zombie.setPosition(newPosA);
                    other.setPosition(newPosB);
                }
            }
        }
    }
    
}
