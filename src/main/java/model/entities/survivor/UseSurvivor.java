package model.entities.survivor;

import org.apache.commons.lang3.tuple.Pair;

public class UseSurvivor {

    public static void main(String[] args) {
        Survivor su1 = new Common(Pair.of(0.0,0.0), Pair.of(0.0,0.0), 2000, 10);
        System.out.println(su1.getLive());
        su1.damage(100);
        System.out.println(su1.getLive());
        System.out.println(su1.getCurrentPos());
    }
}
