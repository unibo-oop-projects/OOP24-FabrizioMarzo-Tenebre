package view;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.SurvivorFactory;
import model.entities.survivor.base.Survivor;
// import view.graphics_survivor.CommonGraphics;
import view.graphics_survivor.CommonGraphicsImage;
import view.survivor_game.GameSurvivor;
import view.survivor_game.IGameSurvivor;

public class FactorySurvivorGame {
    private SurvivorFactory sur = new SurvivorFactory();

    public IGameSurvivor gameSurvivorCommon(final int live,final int attack, final Pair<Double,Double> pos, final Pair<Double,Double> vel){
        Survivor common = sur.createCommon(live, attack, pos, vel);
        return new GameSurvivor(common,
                     new CommonGraphicsImage(common.getClass().getSimpleName()));
    }
}
