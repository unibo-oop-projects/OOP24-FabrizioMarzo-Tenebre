package model.level;

import org.apache.commons.lang3.tuple.Pair;

import model.entities_game.survivor_game.FactorySurvivorGame;
import model.entities_game.survivor_game.IGameSurvivor;

public class TutorialLevel implements Level {
    private FactorySurvivorGame fact = new FactorySurvivorGame(); 
    private IGameSurvivor survivorCommon;


    public TutorialLevel(){
        this.survivorCommon = fact.gameSurvivorCommon(1000,20, Pair.of(390.0,40.0),Pair.of(250.0,0.0));
    }

    public IGameSurvivor getSurvivorOnLevel(){
            return this.survivorCommon;
    }

    public void updateState(final int dt){
        survivorCommon.getSurvivor().updateState(dt);
    }
}
