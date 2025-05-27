package model.ai;

import java.util.List;

import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;

public interface AINPCBehavior<E extends Survivor, B extends Zombie> {
    void updateAINPC(E target, B npc, List<B> npcs);
}

