package model.level;

import view.survivor_game.IGameSurvivor;

public interface Level {

    public IGameSurvivor getSurvivor(); 

    public void updateState(final int dt);
    
}
