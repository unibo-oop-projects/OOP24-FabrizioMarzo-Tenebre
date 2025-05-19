package view.graphics;

import java.awt.image.BufferedImage;
import model.entities.zombie.Zombie;

public interface GraphicsZombie {
    void drawZombie(final Zombie zob,final BufferedImage image);
}
