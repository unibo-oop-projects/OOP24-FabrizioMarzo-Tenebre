package model.physics.physics_entities;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.zombie.Zombie;

public interface PhysicsZombieComponent {
    public void updateZombie(final Zombie zob, final int dt, final Pair<Double, Double> vel);
}
