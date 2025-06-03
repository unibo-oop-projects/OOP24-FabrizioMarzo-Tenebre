package game.game_model.game_armory;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;
import view.graphics_component.armory.GraphicsMunitionComponent;

public class GameMunition implements IGameMunition{

    private Munition mun;
    private GraphicsMunitionComponent imgM;

    public GameMunition(final Munition mun, final GraphicsMunitionComponent imgM){
        this.mun = mun;
        this.imgM = imgM;
    }


    @Override
    public Munition getMunition() {
        return this.mun;
    }

    @Override
    public void updateGraphics(final GraphicsMunition graphicsMun) {
        imgM.update(this.getMunition(), graphicsMun);
    }
    
}
