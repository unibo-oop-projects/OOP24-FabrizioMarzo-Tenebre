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
                             .forEach(zombie -> {
                                this.updateZombieAI(lv, zombie);
                                zombie.updatePhysics(dt);
                                resolveZombieCollisions(lv, zombie);

                             });

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
        Pair<Double, Double> separationForce = Pair.of(0.0, 0.0);
    int count = 0;

    for (Zombie other : lv.getZombieOnLevel()) {
        if (other != zombie) {
            // Prendi le bounding box di zombie e other
            var bb1 = zombie.getBBox();
            var bb2 = other.getBBox();

            // Se le bounding box sono in collisione o troppo vicine (con una certa tolleranza)
            if (bb1.isColliding(bb2.getULcorner(), bb2.getBRcorner())) {
                // Calcola differenza di posizione (vettore direzione separazione)
                Pair<Double, Double> diff = PairUtils.diff(zombie.getCurrentPos(), other.getCurrentPos());

                // Se i due zombie sono esattamente sovrapposti (posizione uguale), usa una separazione arbitraria
                if (PairUtils.norm2(diff) == 0) {
                    diff = Pair.of(Math.random() - 0.5, Math.random() - 0.5);
                }

                Pair<Double, Double> normalizedDiff = PairUtils.normalize(diff);

                // Forza separazione - qui puoi amplificare se vuoi, per esempio:
                Pair<Double, Double> force = PairUtils.mulScale(normalizedDiff, MAX_FORCE);

                separationForce = PairUtils.sum(separationForce, force);
                count++;
            }
        }
    }

    if (count > 0) {
        // Media della forza di separazione da tutti gli altri zombie vicini
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
     // Velocità verso il survivor (unità/secondo)
     Pair<Double, Double> targetVel = moveZombieTowardsSurvivor(zombie, lv.getSurvivorOnLevel());

     // Forza/velocità di separazione (unità/secondo)
     Pair<Double, Double> separationForce = computeSeparationForce(lv, zombie);
 
     double separationWeight = 2.0; // peso forze separation
 
     // Combina velocità, scala la separation per il peso
     Pair<Double, Double> combinedVel = PairUtils.sum(targetVel, PairUtils.mulScale(separationForce, separationWeight));
 
     // Ora, aggiorniamo la posizione nel metodo updatePhysics usando dt:
 
     // Qui limitiamo la velocità massima
     double maxSpeed = PairUtils.norm2(zombie.getBaseZombieVel());
     double speed = PairUtils.norm2(combinedVel);
 
     if (speed > maxSpeed) {
         combinedVel = PairUtils.mulScale(PairUtils.normalize(combinedVel), maxSpeed);
     }
 
     // Imposta la velocità combinata
     zombie.setVelocity(combinedVel);
 
     // NEL updatePhysics:
     // posizione_nuova = posizione_corrente + velocità * dtSeconds

    }

    private void resolveZombieCollisions(Level lv, Zombie zombie) {
        for (Zombie other : lv.getZombieOnLevel()) {
            if (other != zombie) {
                var bb1 = zombie.getBBox();
                var bb2 = other.getBBox();
    
                if (bb1.isColliding(bb2.getULcorner(), bb2.getBRcorner())) {
                    // Calcola il vettore che separa i due zombie
                    Pair<Double, Double> diff = PairUtils.diff(zombie.getCurrentPos(), other.getCurrentPos());
    
                    // Evita divisione per 0 se sono esattamente sovrapposti
                    if (PairUtils.norm2(diff) == 0) {
                        diff = Pair.of(Math.random() - 0.5, Math.random() - 0.5);
                    }
    
                    // Normalizza e calcola quanto spostarli (metà e metà)
                    Pair<Double, Double> dir = PairUtils.normalize(diff);
                    double overlap = 1.0; // puoi calcolarlo precisamente se vuoi
                    Pair<Double, Double> correction = PairUtils.mulScale(dir, overlap / 2.0);
    
                    // Sposta zombie A avanti e zombie B indietro
                    Pair<Double, Double> newPosA = PairUtils.sum(zombie.getCurrentPos(), correction);
                    Pair<Double, Double> newPosB = PairUtils.diff(other.getCurrentPos(), correction);
    
                    zombie.setPosition(newPosA);
                    other.setPosition(newPosB);
                }
            }
        }
    }
    
}
