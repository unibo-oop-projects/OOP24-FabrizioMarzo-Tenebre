package game.game_entities;

import model.entities.zombie.Zombie;
import view.graphics_component.zombie.GraphicsClickerComponent;

public class FactoryZombieGame {
    
    public IGameZombie gameSurvivorClicker(final Zombie clicker){
        return new GameZombie(clicker,
                            new GraphicsClickerComponent(clicker.getClass().getSimpleName()));
    }
}
