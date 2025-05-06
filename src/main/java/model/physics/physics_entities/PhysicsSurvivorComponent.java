package model.physics.physics_entities;

import model.entities.survivor.Survivor;

public interface PhysicsSurvivorComponent {
    public void updateSurvivor(final Survivor sur, final int dt);
}
