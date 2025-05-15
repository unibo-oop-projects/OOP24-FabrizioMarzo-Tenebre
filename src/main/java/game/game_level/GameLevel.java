package game.game_level;

import game.game_entities.FactorySurvivorGame;
import game.game_entities.IGameSurvivor;
import model.level.Level;
import view.graphics.GraphicsLevel;
import view.graphics_component.level.GraphicsLevelComponent;

public class GameLevel implements IGameLevel {

    private Level lvl;
    private GraphicsLevelComponent imgLvl;
    private FactorySurvivorGame factSurGam = new FactorySurvivorGame();
    private IGameSurvivor gamSur;
    
    public GameLevel(Level lvl, GraphicsLevelComponent imgLvl) {
        this.lvl = lvl;
        this.imgLvl = imgLvl;
        this.gamSur = setGameSurvivor();
    }

    private IGameSurvivor setGameSurvivor(){
        return factSurGam.gameSurvivorCommon(this.lvl.getSurvivorOnLevel());
    }

    @Override
    public Level getLevel() {
        return this.lvl;
    }

    @Override
    public void updateGraphics(final GraphicsLevel graphicLvl) {
        imgLvl.update(this.lvl,graphicLvl);
    }

    @Override
    public IGameSurvivor getGameSurvivor() {
        return this.gamSur;
    }
    
}
