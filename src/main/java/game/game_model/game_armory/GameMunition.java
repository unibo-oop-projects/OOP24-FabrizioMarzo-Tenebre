package game.game_model.game_armory;

import model.armory.munition.Munition;

public class GameMunition implements IGameMunition{

    private Munition mun;

    public GameMunition(final Munition mun){
        this.mun = mun;
    }


    @Override
    public Munition getMunition() {
        return this.mun;
    }

    @Override
    public void updateGraphics() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGraphics'");
    }
    
}
