package model.physics.physics_entities;

import model.entities.zombie.Zombie;
import utils.PairUtils;

public class PhysicsBaseZombie implements PhysicsZombieComponent{

    private static final double MILLISECONDS_TO_SECONDS = 0.001;

    @Override
    public void updateZombie(final Zombie zob, final int dt) {
        zob.setPosition(
            PairUtils.sum(
                zob.getCurrentPos(),
                PairUtils.mulScale(zob.getCurrentVel(), MILLISECONDS_TO_SECONDS * dt)
            )
        );
    }
}
