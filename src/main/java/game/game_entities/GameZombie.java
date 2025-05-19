package game.game_entities;

import model.entities.zombie.Zombie;
import view.graphics.GraphicsZombie;
import view.graphics_component.zombie.GraphicsZombieComponent;

public class GameZombie implements IGameZombie{

    private Zombie zob;
    private GraphicsZombieComponent imgZob;

    public GameZombie(final Zombie zob, final GraphicsZombieComponent imgZob){
        this.zob = zob;
        this.imgZob = imgZob;
    }

    @Override
    public Zombie getZombie() {
        return this.zob;
    }

    @Override
    public void updateGraphics(final GraphicsZombie graphZob) {
        imgZob.update(this.getZombie(), graphZob);
    }
    
}
