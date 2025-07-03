package model.armory.weapon;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.armory.charger.FactoryCharger;

/**
 * Factory for creating weapon instances.
 */
public class FactoryWeapon {

    private FactoryCharger factCharge = new FactoryCharger();

    /**
     * Creates a pistol weapon at the specified position.
     * 
     * @param posWeapon the initial position of the pistol
     * @return a new Pistol instance
     */
    public Weapon createPistol(final Pair<Double, Double> posWeapon) {
        final double cooldownMillis = 450;
        final int shotsPerFire = 1;
        return new Pistol(new MutablePair<>(posWeapon.getLeft(), posWeapon.getRight()),
                cooldownMillis,
                shotsPerFire,
                factCharge.createDrumCharger(posWeapon));
    }

}
