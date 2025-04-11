package model.entities.survivor;

import model.entities.survivor.base.Survivor;

public class GameSurvivor implements IGameSurvivor{
    private Survivor sur;

    @Override
    public Survivor getSurvivor() {
        return this.sur;    
    }

    @Override
    public void updateGraphics() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGraphics'");
    }

}
