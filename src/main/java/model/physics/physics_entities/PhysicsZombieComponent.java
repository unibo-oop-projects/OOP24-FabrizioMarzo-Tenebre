package model.physics.physics_entities;

import model.entities.zombie.Zombie;

public interface PhysicsZombieComponent {
    public void updateZombie(final Zombie zob, final int dt);
}
