package model.entities.survivor;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.base.Survivor;

public class UseSurvivor {

    public static void main(String[] args) {
        SurvivorFactory suFac = new SurvivorFactory();
        Survivor su1 = suFac.createCommon(1000, 30, Pair.of(0.0,0.0), Pair.of(0.0,0.0));
        System.out.println(SurvivorState.IDLE.getIndex());
        su1.setState(SurvivorState.ATTACK);
        System.out.println(su1.getState());
    }
}
