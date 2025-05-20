package model.entities.zombie;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.RectBoundingBox;
import model.physics.physics_entities.PhysicsClickerComponent;

public class ZombieFactory {

    public Zombie createClickerZombie(final Pair<Double,Double> pos, final Pair<Double,Double> vel){
        final int live = 1000;
        final int attack = 20;
        final int width = 90;
        final int height = 230;
        return new Clicker(live, attack, 
                            width, height, 
                            new MutablePair<>(pos.getLeft(),pos.getRight()),
                            new MutablePair<>(vel.getLeft(),vel.getRight()), 
                            new PhysicsClickerComponent(), 
                            new RectBoundingBox(Pair.of(pos.getLeft(),pos.getRight()+height),
                                                Pair.of(pos.getLeft()+width ,pos.getRight())));
    }
}
    