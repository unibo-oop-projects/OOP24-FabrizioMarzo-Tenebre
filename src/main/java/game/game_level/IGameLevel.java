package game.game_level;

import game.game_entities.IGameSurvivor;
import game.game_entities.IGameZombie;
import model.level.Level;
import view.graphics.GraphicsLevel;

public interface IGameLevel {
    
    Level getLevel();

    IGameSurvivor getGameSurvivor();

    IGameZombie getGameZombie();

    void updateGraphics(final GraphicsLevel graphicLvl);
}
