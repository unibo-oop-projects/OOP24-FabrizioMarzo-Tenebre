package view.graphics;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import model.entities.zombie.Zombie;
import view.graphics_util.IViewScale;

public class SwingGraphicsZombie implements GraphicsZombie {

    private Graphics2D g2d;
    private IViewScale viewScale;

    public SwingGraphicsZombie(final Graphics2D g2d, final IViewScale viewScale){
        this.g2d = g2d;
        this.viewScale = viewScale;
    }

    @Override
    public void drawZombie(Zombie zob, BufferedImage image) {
    }
    
}
