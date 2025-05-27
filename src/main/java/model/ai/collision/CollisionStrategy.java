package model.ai.collision;

import java.util.List;

import model.entities.zombie.Zombie;

public interface CollisionStrategy< Z extends Zombie > {

    void resolveCollisions(final Z zombie,final List<Z> others);
}
