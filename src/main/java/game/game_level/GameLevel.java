package game.game_level;

import game.game_entities.FactorySurvivorGame;
import game.game_entities.FactoryZombieGame;
import game.game_entities.IGameSurvivor;
import game.game_entities.IGameZombie;
import model.level.Level;
import view.graphics.GraphicsLevel;
import view.graphics_component.level.GraphicsLevelComponent;

public class GameLevel implements IGameLevel {

    private Level lvl;
    private GraphicsLevelComponent imgLvl;
    private FactorySurvivorGame factSurGam = new FactorySurvivorGame();
    private FactoryZombieGame factZobGam = new FactoryZombieGame();
    private IGameSurvivor gamSur;
    private IGameZombie gamZob;


    public GameLevel(final Level lvl,final GraphicsLevelComponent imgLvl) {
        this.lvl = lvl;
        this.imgLvl = imgLvl;
        this.gamSur = setGameSurvivor();
        this.gamZob = setGameZombie();
    }

    private IGameSurvivor setGameSurvivor(){
        return factSurGam.gameSurvivorCommon(this.lvl.getSurvivorOnLevel());
    }

    private IGameZombie setGameZombie(){
        return factZobGam.gameSurvivorClicker(this.lvl.getZombieOnLevel());
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
    
    @Override
    public IGameZombie getGameZombie() {
        return this.gamZob;
    }
}
