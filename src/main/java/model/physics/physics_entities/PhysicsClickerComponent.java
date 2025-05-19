package model.physics.physics_entities;

import model.entities.zombie.Zombie;

public class PhysicsClickerComponent implements PhysicsZombieComponent{

    @Override
    public void updateZombie(Zombie zob, int dt) {
        System.out.println(zob.getClass().getSimpleName() + " Zombie " + dt + "Time Pass");
    }
}
