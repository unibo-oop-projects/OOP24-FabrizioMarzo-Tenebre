package view.graphics_component.zombie;

import model.entities.zombie.Zombie;
import view.graphics.GraphicsZombie;

public interface GraphicsZombieComponent {
    void update(final Zombie zob, final GraphicsZombie graphZob);
}
