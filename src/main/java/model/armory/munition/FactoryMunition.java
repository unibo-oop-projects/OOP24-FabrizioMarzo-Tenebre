package model.armory.munition;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.RectBoundingBox;

/**
 * Factory class responsible for creating various types of {@link Munition}.
 * <p>
 * Currently supports creation of Parabellum ammunition with preset parameters.
 */
public class FactoryMunition {

    /**
     * Creates a new {@link Parabellum} munition instance at the specified initial
     * position.
     * <p>
     * The Parabellum is initialized with default values:
     * <ul>
     * <li>Damage: 400</li>
     * <li>Width: 3</li>
     * <li>Velocity: 900 units/second</li>
     * </ul>
     * The bounding box is also initialized based on the given position and width.
     * 
     * @param initPos the initial position of the munition (x, y)
     * @return a new instance of {@link Parabellum} munition
     */
    public Munition createParabellum(final Pair<Double, Double> initPos) {
        final int damage = 400;
        final int width = 3;
        final int velocity = 900;
        return new Parabellum(damage,
                velocity,
                width,
                new MutablePair<>(initPos.getLeft(), initPos.getRight()),
                new RectBoundingBox(Pair.of(initPos.getLeft(), initPos.getRight() + width),
                        Pair.of(initPos.getLeft() + width, initPos.getRight())));
    }

}
