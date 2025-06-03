package game.game_model.game_level;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import game.game_model.game_armory.FactoryMunitionGame;
import game.game_model.game_armory.IGameMunition;
import game.game_model.game_entities.FactorySurvivorGame;
import game.game_model.game_entities.FactoryZombieGame;
import game.game_model.game_entities.IGameSurvivor;
import game.game_model.game_entities.IGameZombie;
import model.armory.munition.Munition;
import model.entities.zombie.Zombie;
import model.level.Level;
import view.graphics.GraphicsLevel;
import view.graphics_component.level.GraphicsLevelComponent;

public class GameLevel implements IGameLevel {

    private Level lvl;
    private GraphicsLevelComponent imgLvl;

    private FactorySurvivorGame factSurGam = new FactorySurvivorGame();
    private FactoryZombieGame factZobGam = new FactoryZombieGame();
    private FactoryMunitionGame factMunGam = new FactoryMunitionGame();

    private final Map<Zombie, IGameZombie> gameZombieMap = new  LinkedHashMap<>();
    private final Map<Munition, IGameMunition> gameMunitionMap = new LinkedHashMap<>();
    private IGameSurvivor gamSur;


    public GameLevel(final Level lvl,final GraphicsLevelComponent imgLvl) {
        this.lvl = lvl;
        this.imgLvl = imgLvl;
        this.gamSur = setGameSurvivor();
        this.syncGameZombies();
    }

    private IGameSurvivor setGameSurvivor(){
        return factSurGam.gameSurvivorCommon(this.lvl.getSurvivorOnLevel());
    }

    private void syncGameZombies() {
        List<Zombie> currentZombies = this.lvl.getZombieOnLevel();

        // Remove Zombie if there are dead 
        gameZombieMap.keySet().removeIf(z -> !currentZombies.contains(z));

        // Add new Zombie if there are born
        for (Zombie z : currentZombies) {
            gameZombieMap.computeIfAbsent(z, factZobGam::gameZombieClicker);
        }
    }    

    private void syncGameMunitions() {
        List<Munition> currentMunitions = lvl.getProjectilesOnLevel();
    
        // Remove all Munitions deleted 
        gameMunitionMap.keySet().removeIf(m -> !currentMunitions.contains(m));
    
        // Add new Munitions
        for (Munition m : currentMunitions) {
            gameMunitionMap.computeIfAbsent(m, factMunGam::gameMunition);
        }
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
        return Collections.unmodifiableList(lvl.getZombieOnLevel().stream()
                                                                .map(gameZombieMap::get)
                                                                .filter(Objects::nonNull)
                                                                .collect(Collectors.toList()));
    }
    
    @Override
    public List<IGameMunition> getGameMunitions() {
        return Collections.unmodifiableList(lvl.getProjectilesOnLevel().stream()
                                                                .map(gameMunitionMap::get)
                                                                .filter(Objects::nonNull)
                                                                .collect(Collectors.toList()));
    }
    

    @Override
    public void updateStateGameLevel() {
        this.syncGameMunitions();
        this.syncGameZombies();
    }

}
