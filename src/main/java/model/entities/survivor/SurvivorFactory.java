package model.entities.survivor;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.RectBoundingBox;
import model.physics.physics_entities.PhysicsCommonComponent;

/**
 * Factory class responsible for creating instances of {@link Survivor} entities.
 * This class provides methods to create different types of survivors.
 */
public class SurvivorFactory {

    public Survivor createCommonSurvivor(final Pair<Double,Double> pos){
        final int live = 1000;
        final int attack = 20;
        final int width = 70;
        final int height = 175;
        final Pair<Double, Double> vel = Pair.of(200.0, 0.0);
       return new Common(live,attack,
                         width,height,
                         new MutablePair<>(pos.getLeft(),pos.getRight()),
                         new MutablePair<>(vel.getLeft(),vel.getRight()),
                         new PhysicsCommonComponent(),
                         new RectBoundingBox(Pair.of(pos.getLeft(),pos.getRight()+height),
                                             Pair.of(pos.getLeft()+width ,pos.getRight())));
    }

}
