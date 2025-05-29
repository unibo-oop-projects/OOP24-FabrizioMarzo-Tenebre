package model.armory.weapon;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;

public interface Weapon {
    
    List<Munition> shoot(final Pair<Double, Double> dirShoot,final double deltaTime);
    
    int getAmmoCount();

}
