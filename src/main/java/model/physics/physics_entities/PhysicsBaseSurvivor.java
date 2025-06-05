package model.physics.physics_entities;

import model.entities.survivor.Survivor;
import utils.PairUtils;

public class PhysicsBaseSurvivor implements PhysicsSurvivorComponent {

    private static final double MILLISECONDS_TO_SECONDS = 0.001;

    @Override
    public void updateSurvivor(final Survivor sur, final int dt) {
        sur.setPosition(
            PairUtils.sum(
                sur.getCurrentPos(),
                PairUtils.mulScale(sur.getCurrentVel(), MILLISECONDS_TO_SECONDS * dt)
            )
        );
    }
}
