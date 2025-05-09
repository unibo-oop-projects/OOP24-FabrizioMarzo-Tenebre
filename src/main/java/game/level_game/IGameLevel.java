package game.level_game;

import model.level.Level;
import view.graphics.GraphicsLevel;

public interface IGameLevel {
    
    Level getLevel();

    void updateGraphics(final GraphicsLevel graphicLvl);
}
