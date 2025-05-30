package model.armory.weapon;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.MutablePair;
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
    private Pair<Double,Double> dirWeapon;
    private Pair<Double,Double> posWeapon;


    public Pistol(final Pair<Double,Double> posWeapon, final double cooldownMillis, final int shotsPerFire) {
        this.posWeapon = posWeapon;
        this.charger = factCharge.createDrumCharger(posWeapon);
        this.cooldownMillis = cooldownMillis;
        this.shotsPerFire = shotsPerFire;
        this.dirWeapon = new MutablePair<>(0.0,0.0);
    }


    @Override
    public List<Munition> shoot(final double deltaTime) {
        timeSinceLastShot += deltaTime;

        if (timeSinceLastShot < cooldownMillis) {
            return List.of(); 
        } else {
            List<Munition> result = new ArrayList<>();
    
            for (int i = 0; i < shotsPerFire ; i++) {
                Munition m = charger.extractAmmunition();
                m.setShoot(this.dirWeapon, this.posWeapon);
                result.add(m);
            }
    
            timeSinceLastShot = 0;    
            return result;
        }
    }


    @Override
    public int getAmmoCount() {
        return this.charger.getCurrentLoad();
    }

    @Override
    public void aim(final Pair<Double,Double> dirWeapon, final Pair<Double,Double> posWeapon ){
        this.dirWeapon = dirWeapon;
        this.posWeapon = posWeapon;
    }


}
