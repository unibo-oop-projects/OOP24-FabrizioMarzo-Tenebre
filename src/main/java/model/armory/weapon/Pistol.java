package model.armory.weapon;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.charger.Charger;
import model.armory.charger.FactoryCharger;
import model.armory.munition.Munition;

public class Pistol implements Weapon{

    private FactoryCharger factCharge = new FactoryCharger();
    private Charger charger;
    private final double cooldownMillis;
    private double timeSinceLastShot = 0;


    public Pistol(final Pair<Double,Double> posWeapon, final double cooldownMillis) {
        this.charger = factCharge.createDrumCharger(posWeapon);
        this.cooldownMillis = cooldownMillis;
    }


    @Override
    public Optional<Munition> shoot(Pair<Double, Double> dirShoot, double deltaTime) {
        timeSinceLastShot += deltaTime;

        if (timeSinceLastShot < cooldownMillis) {
            return Optional.empty();
        }

        Munition munition = charger.extractAmmunition();
        munition.setShot(dirShoot);
        timeSinceLastShot = 0;
        return Optional.of(munition);
        
    }


    @Override
    public int getAmmoCount() {
        return this.charger.getCurrentLoad();
    }
    

}
