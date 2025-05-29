package model.armory.charger;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.FactoryMunition;
import model.armory.munition.Munition;

public class Drum implements Charger{

    private List<Munition> munitions = new ArrayList<>();
    private int capacity;
    private Pair<Double,Double> pos;
    private FactoryMunition factory = new FactoryMunition();

    public Drum(final int capacity, final Pair<Double,Double> pos) {
        this.capacity = capacity;
        this.pos = pos;
        this.fillMagazine();
    }

    private void fillMagazine(){
        while (munitions.size() < capacity) {
        Munition newMunition = factory.createParabellum(this.pos); 
        munitions.add(newMunition);
        }        
    }
    
    @Override
    public Munition extractAmmunition() {
        if (munitions.isEmpty()){
            this.fillMagazine();
        } 
        return munitions.remove(munitions.size()-1);
    }

    @Override
    public void setPos(final Pair<Double, Double> pos) {
        this.pos = pos;
        for (Munition m : munitions) {
            if (!m.isShot()) {
                m.setPos(pos);
            }
        }
    }

    @Override
    public int getCurrentLoad() {
        return munitions.size();
    }
    
}
