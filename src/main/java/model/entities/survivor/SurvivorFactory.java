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

    /**
     * Creates a new instance of a {@link Common} survivor.
     *
     * @param live   the initial health of the survivor
     * @param attack the attack strength of the survivor
     * @param pos    the initial position of the survivor, represented as a pair of coordinates (x, y)
     * @param vel    the initial velocity of the survivor, represented as a pair of velocity components (vx, vy)
     * @return a new {@link Survivor} instance of type {@link Common}
     */
    public Survivor createCommonSurvivor(final int live,final int attack, final Pair<Double,Double> pos, final Pair<Double,Double> vel){
        final int width = 70;
        final int height = 175;
       return new Common(live,attack,
                         width,height,
                         new MutablePair<>(pos.getLeft(),pos.getRight()),
                         new MutablePair<>(vel.getLeft(),vel.getRight()),
                         new PhysicsCommonComponent(),
                         new RectBoundingBox(Pair.of(pos.getLeft(),pos.getRight()+height),
                                             Pair.of(pos.getLeft()+width ,pos.getRight())));
    }

}
