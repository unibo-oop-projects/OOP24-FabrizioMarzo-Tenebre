package model.armory.weapon;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;

public interface Weapon {
    
    List<Munition> shoot(final double deltaTime);
    
    int getAmmoCount();

    void setDir(final Pair<Double,Double> dirWeapon);

    void setPos(final Pair<Double,Double> posWeapon);

}
