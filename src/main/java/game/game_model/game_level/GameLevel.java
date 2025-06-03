package game.game_model.game_level;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game.game_model.game_armory.FactoryMunitionGame;
import game.game_model.game_armory.IGameMunition;
import game.game_model.game_entities.FactorySurvivorGame;
import game.game_model.game_entities.FactoryZombieGame;
import game.game_model.game_entities.IGameSurvivor;
import game.game_model.game_entities.IGameZombie;
import model.level.Level;
import view.graphics.GraphicsLevel;
import view.graphics_component.level.GraphicsLevelComponent;

public class GameLevel implements IGameLevel {

    private Level lvl;
    private GraphicsLevelComponent imgLvl;
    private FactorySurvivorGame factSurGam = new FactorySurvivorGame();
    private FactoryZombieGame factZobGam = new FactoryZombieGame();
    private FactoryMunitionGame factMunGam = new FactoryMunitionGame();
    private IGameSurvivor gamSur;
    private List<IGameZombie> listGameZombie = new ArrayList<>();
    private List<IGameMunition> listMunitions = new ArrayList<>();


    public GameLevel(final Level lvl,final GraphicsLevelComponent imgLvl) {
        this.lvl = lvl;
        this.imgLvl = imgLvl;
        this.gamSur = setGameSurvivor();
        this.setGameZombie();
    }

    private IGameSurvivor setGameSurvivor(){
        return factSurGam.gameSurvivorCommon(this.lvl.getSurvivorOnLevel());
    }

    private void setGameZombie(){  
         this.listGameZombie = this.lvl.getZombieOnLevel().stream()
                                    .map(zombie -> this.factZobGam.gameSurvivorClicker(zombie))
                                    .collect(Collectors.toList());
        
    }

    private void setGameMunition(){
        this.listMunitions = this.lvl.getProjectilesOnLevel().stream()
                                                            .map(munition -> this.factMunGam.gameMunition(munition))
                                                            .collect(Collectors.toList());
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
    public List<IGameZombie> getGameZombie() {
        return this.listGameZombie;
    }
    
    @Override
    public List<IGameMunition> getGameMunitions() {
       return this.listMunitions;
    }

    @Override
    public void updateStateGameLevel() {
        this.setGameMunition();
    }

}
