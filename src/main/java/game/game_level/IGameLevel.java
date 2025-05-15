package game.game_level;

import model.level.Level;
import view.graphics.GraphicsLevel;

public interface IGameLevel {
    
    Level getLevel();

    void updateGraphics(final GraphicsLevel graphicLvl);
}
