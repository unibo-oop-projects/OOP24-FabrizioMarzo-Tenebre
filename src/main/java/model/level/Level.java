package model.level;

import model.entities_game.survivor_game.IGameSurvivor;

public interface Level {

    public IGameSurvivor getSurvivorOnLevel(); 

    public void updateState(final int dt);
    
}
