package game.game_entities;

import model.entities.zombie.Zombie;
import view.graphics.GraphicsZombie;

public interface IGameZombie {
    
    Zombie getZombie();

//    void updateInput();

    void updateGraphics(final GraphicsZombie graphZob);

}
