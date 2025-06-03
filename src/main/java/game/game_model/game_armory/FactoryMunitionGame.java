package game.game_model.game_armory;

import model.armory.munition.Munition;
import view.graphics_component.armory.GraphicsParabellumComponent;

public class FactoryMunitionGame {
 
    public IGameMunition gameMunition(final Munition munition){
        return new GameMunition(munition, 
                                new GraphicsParabellumComponent(munition.getClass().getSimpleName()));
    }


}
