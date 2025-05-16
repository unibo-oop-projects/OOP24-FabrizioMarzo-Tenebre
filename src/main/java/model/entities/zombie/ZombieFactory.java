package model.entities.zombie;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.RectBoundingBox;
import model.physics.physics_entities.PhysicsClickerComponent;

public class ZombieFactory {

    public Zombie createClickerZombie(final int live,final int attack, final Pair<Double,Double> pos, final Pair<Double,Double> vel){
        final int width = 70;
        final int height = 175;
        return new Clicker(live, attack, 
                            width, height, 
                            new MutablePair<>(pos.getLeft(),pos.getRight()),
                            new MutablePair<>(vel.getLeft(),vel.getRight()), 
                            new PhysicsClickerComponent(), 
                            new RectBoundingBox(Pair.of(pos.getLeft(),pos.getRight()+height),
                                                Pair.of(pos.getLeft()+width ,pos.getRight())));
    }
}
