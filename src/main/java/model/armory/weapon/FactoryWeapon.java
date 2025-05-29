package model.armory.weapon;

import org.apache.commons.lang3.tuple.Pair;

public class FactoryWeapon {
    
    public Weapon createPistol(final Pair<Double,Double> posWeapon){
        final double cooldownMillis = 450; 
        return new Pistol(posWeapon, cooldownMillis);
    }

}
