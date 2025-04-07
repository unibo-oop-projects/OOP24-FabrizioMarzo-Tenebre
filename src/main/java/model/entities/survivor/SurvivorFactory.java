package model.entities.survivor;

import org.apache.commons.lang3.tuple.Pair;

public class SurvivorFactory {

    public Survivor createCommon(final int live,final int attack, final Pair<Double,Double> pos, final Pair<Double,Double> vel){
       return new Common(live, attack, pos, vel);
    }

}
