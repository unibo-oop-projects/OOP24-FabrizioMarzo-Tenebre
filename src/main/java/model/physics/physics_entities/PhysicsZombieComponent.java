package model.physics.physics_entities;

import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;

public interface PhysicsZombieComponent {
    public void updateZombie(final Zombie zob, final Survivor sur, final int dt);
}
