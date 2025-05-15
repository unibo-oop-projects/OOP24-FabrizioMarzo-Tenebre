package game.game_level;

import game.game_entities.IGameSurvivor;
import model.level.Level;
import view.graphics.GraphicsLevel;

public interface IGameLevel {
    
    Level getLevel();

    IGameSurvivor getGameSurvivor();

    void updateGraphics(final GraphicsLevel graphicLvl);
}
