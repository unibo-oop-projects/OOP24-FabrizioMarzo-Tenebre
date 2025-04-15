package model.level;

import org.apache.commons.lang3.tuple.Pair;

import view.survivor_game.FactorySurvivorGame;
import view.survivor_game.IGameSurvivor;

public class TutoriaLevel {
    private FactorySurvivorGame fact = new FactorySurvivorGame(); 
    private IGameSurvivor survivorCommon;


    public TutoriaLevel(){
        this.survivorCommon = fact.gameSurvivorCommon(1000,20, Pair.of(390.0,40.0),Pair.of(30.0,0.0));
    }

    public IGameSurvivor getSurvivor(){
            return this.survivorCommon;
    }

    public void updateState(final int dt){
        survivorCommon.getSurvivor().updateState(dt);
    }
}
