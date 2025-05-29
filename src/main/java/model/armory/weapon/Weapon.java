package model.armory.weapon;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;

public interface Weapon {
    
    Optional<Munition> shoot(Pair<Double, Double> dirShoot, double deltaTime);

    int getAmmoCount();

}
