package game.game_model.game_armory;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;

public interface IGameMunition {
    
    Munition getMunition();

    void updateGraphics(final GraphicsMunition graphicsMun);

}
