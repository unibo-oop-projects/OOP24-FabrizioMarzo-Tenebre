package game.game_model.game_entities;

import model.entities.zombie.Zombie;
import view.graphics_component.zombie.GraphicsClickerComponent;

/**
 * Factory class to create game zombie entities with their graphical components.
 */
public class FactoryZombieGame {

    /**
     * Creates a {@link IGameZombie} instance representing a "clicker" zombie,
     * initializing it with the given {@link Zombie} model and the appropriate
     * graphics component.
     * 
     * @param clicker the {@link Zombie} instance representing the clicker zombie
     * @return a new {@link IGameZombie} configured with the clicker zombie and its
     *         graphics component
     */
    public IGameZombie gameZombieClicker(final Zombie clicker) {
        return new GameZombie(clicker,
                new GraphicsClickerComponent(clicker.getClass().getSimpleName()));
    }
}
