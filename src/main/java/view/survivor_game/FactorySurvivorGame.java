package view.survivor_game;

import org.apache.commons.lang3.tuple.Pair;

import input.input_survivor.CommonInput;
import model.entities.survivor.SurvivorFactory;
import model.entities.survivor.base.Survivor;
import view.survivor_game.survivor_graphics.CommonGraphicsImage;

public class FactorySurvivorGame {
    private SurvivorFactory sur = new SurvivorFactory();

    public IGameSurvivor gameSurvivorCommon(final int live,final int attack, final Pair<Double,Double> pos, final Pair<Double,Double> vel){
        Survivor common = sur.createCommon(live, attack, pos, vel);
        return new GameSurvivor(common,
                     new CommonGraphicsImage(common.getClass().getSimpleName()),
                     new CommonInput());
    }
}
