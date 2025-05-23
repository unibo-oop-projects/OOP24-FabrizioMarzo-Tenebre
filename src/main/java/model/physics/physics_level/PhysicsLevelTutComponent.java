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
    private static final double MAX_FORCE = 0.5;

    @Override
    public void updateLevel(final Level lv,final int dt) {
        lv.getSurvivorOnLevel().updatePhysics(dt);
        // lv.getZombieOnLevel().stream()
        //                      .forEach(zombie -> {
        //                         this.moveZombieTowardsSurvivor(zombie, lv.getSurvivorOnLevel());
        //                         zombie.updatePhysics(dt);
        //                      });

        for (Zombie z : lv.getZombieOnLevel()) {
            Pair<Double, Double> newVel = computeZombieVelocity(lv, z);
            z.setVelocity(newVel);
            z.updatePhysics(dt);
            System.out.println("Change the Physics");
        }


        if (isOutsideLevelBounds(lv)) {
            resetSurvivorToValidPosition(lv);
        }

        for (Zombie z : lv.getZombieOnLevel()) {
            computeSeparationForce(lv, z);
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

    private Pair<Double,Double> computeSeparationForce(Level lv, Zombie zombie) {
        List<Zombie> allZombie = lv.getZombieOnLevel();

        Pair<Double,Double> separationForce = Pair.of(0.0,0.0);
         int count = 0;

        for (Zombie other : allZombie){
            if (other != zombie) {
                var diff  = PairUtils.diff(zombie.getCurrentPos(), other.getCurrentPos());
                double distance = PairUtils.norm2(diff);

                if (distance > 0 && distance < DESIRED_SEPARATION){
                    var normalizedDiff = PairUtils.normalize(diff);
                    // Nota: la forza di separazione dovrebbe essere proporzionale a 1/distanza per allontanarsi di più se molto vicini
                    separationForce = PairUtils.sum(separationForce, PairUtils.mulScale(normalizedDiff, 1.0/distance));
                    count++;
                }
            }
        }
        if (count > 0){
            separationForce = PairUtils.mulScale(separationForce, 1.0/count);
        }

        double mag = PairUtils.norm2(separationForce);
        if (mag > 0){
            separationForce = PairUtils.mulScale(separationForce, MAX_FORCE / mag);
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
    
    private Pair<Double, Double> computeZombieVelocity(Level lv, Zombie zombie) {
        Pair<Double, Double> targetVel = moveZombieTowardsSurvivor(zombie, lv.getSurvivorOnLevel());
        Pair<Double, Double> separationForce = computeSeparationForce(lv, zombie);
    
        Pair<Double, Double> finalVel = PairUtils.sum(targetVel, separationForce);
        // Normalizza o limita la velocità qui se vuoi (ad esempio con MAX_SPEED)
    
        return finalVel;
    }
}
