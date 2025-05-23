package model.physics.physics_level;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.EntitieState;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import model.level.Level;
import utils.PairUtils;

public class PhysicsLevelTutComponent implements PhysicsLevelComponent {

    private static final double DESIRED_SEPARATION = 20.0;
    private static final double MAX_FORCE = 0.05;

    @Override
    public void updateLevel(final Level lv,final int dt) {
        lv.getSurvivorOnLevel().updatePhysics(dt);
        lv.getZombieOnLevel().stream()
                             .forEach(zombie -> zombie.updatePhysics(dt, this.moveZombieTowardsSurvivor(zombie, lv.getSurvivorOnLevel())));
        if (isOutsideLevelBounds(lv)) {
            resetSurvivorToValidPosition(lv);
        }

        for (Zombie z : lv.getZombieOnLevel()) {
            updateZombieAI(lv, z);
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

    private void updateZombieAI(Level lv, Zombie zombie) {
        List<Zombie> allZombie = lv.getZombieOnLevel();

        // Create the vector separation force
        Pair<Double,Double> separationForce = Pair.of(0.0,0.0);
        int count = 0;

        for (Zombie other : allZombie){
            if (other != zombie) {
                var diff  = PairUtils.diff(zombie.getCurrentPos(), other.getCurrentPos());
                double distance = PairUtils.norm2(diff);

                if (distance > 0 && distance < DESIRED_SEPARATION){
                    var nomalizeDist = PairUtils.normalize(diff);
                    separationForce = Pair.of(separationForce.getLeft()+nomalizeDist.getLeft(),
                                              separationForce.getRight()+nomalizeDist.getRight());
                    count++;
                }

            }
        }
                if (count > 0){
                    separationForce = Pair.of(separationForce.getLeft()/count,
                                              separationForce.getRight()/count);
                }

                double mag = PairUtils.norm2(separationForce);
                if (mag > 0){
                    separationForce = Pair.of((separationForce.getLeft() / mag) * MAX_FORCE, (separationForce.getRight() / mag) * MAX_FORCE);
                }

                Pair<Double,Double> currentVel = zombie.getCurrentVel();
                Pair<Double, Double> newVel = Pair.of(currentVel.getLeft() + separationForce.getLeft(), currentVel.getRight() + separationForce.getRight());
                zombie.setVelocity(newVel);
    }


    private Pair<Double,Double>  moveZombieTowardsSurvivor(final Zombie zob, final Survivor sur){
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
    
}
