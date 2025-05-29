package model.armory.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.charger.Charger;
import model.armory.charger.FactoryCharger;
import model.armory.munition.Munition;

public class Pistol implements Weapon{

    private FactoryCharger factCharge = new FactoryCharger();
    private Charger charger;
    private final double cooldownMillis;
    private final int shotsPerFire;
    private double timeSinceLastShot = 0;


    public Pistol(final Pair<Double,Double> posWeapon, final double cooldownMillis, final int shotsPerFire) {
        this.charger = factCharge.createDrumCharger(posWeapon);
        this.cooldownMillis = cooldownMillis;
        this.shotsPerFire = shotsPerFire;
    }


    @Override
    public List<Munition> shoot(final Pair<Double, Double> dirShoot,final double deltaTime) {
        timeSinceLastShot += deltaTime;

        if (timeSinceLastShot < cooldownMillis || charger.getCurrentLoad() == 0) {
            return List.of(); // in cooldown o senza munizioni
        }

        List<Munition> result = new ArrayList<>();
        for (int i = 0; i < shotsPerFire && charger.getCurrentLoad() > 0; i++) {
            Munition m = charger.extractAmmunition();
            m.setShot(dirShoot);
            result.add(m);
        }

        timeSinceLastShot = 0;
        return result;
    }


    @Override
    public int getAmmoCount() {
        return this.charger.getCurrentLoad();
    }
    

}
