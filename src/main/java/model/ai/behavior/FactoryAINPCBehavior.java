package model.ai.behavior;

import model.ai.collision.DefaultCollisionResolver;
import model.ai.movement.DefaultMovementStrategy;
import model.ai.separation.DefaultSeparationBehavior;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;

public class FactoryAINPCBehavior {

    public AINPCBehavior<Survivor,Zombie> createBaseNPCBehavior(){
        return new AIZombieBehavior<>(new DefaultMovementStrategy<>(),
                                      new DefaultSeparationBehavior<>(), 
                                      new DefaultCollisionResolver<>());
    }




}
