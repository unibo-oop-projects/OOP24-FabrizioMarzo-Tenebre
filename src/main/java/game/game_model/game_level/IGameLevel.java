package game.game_model.game_level;

import java.util.List;

import game.game_model.game_armory.IGameMunition;
import game.game_model.game_entities.IGameSurvivor;
import game.game_model.game_entities.IGameZombie;
import model.level.Level;
import view.graphics.GraphicsLevel;

public interface IGameLevel {
    
    Level getLevel();

    IGameSurvivor getGameSurvivor();

    List<IGameZombie> getGameZombie();

    List<IGameMunition> getGameMunitions();

    void updateGraphics(final GraphicsLevel graphicLvl);

    void updateStateGameLevel();
}
