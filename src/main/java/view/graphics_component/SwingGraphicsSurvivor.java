package view.graphics_component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.entities.entities_base.survivor_base.Survivor;

public class SwingGraphicsSurvivor implements GraphicsSurvivor{

    private Graphics2D g2d;
    private static final int WIDTH_IMAGE = 80;
    private static final int HEIGHT_IMAGE = 128;
    

    public SwingGraphicsSurvivor(final Graphics2D g2d) {
        this.g2d = g2d;
    }

    @Override
    public void drawSurvivor(final Survivor sur, final BufferedImage image) {

        int surPosX = sur.getCurrentPos().getLeft().intValue();
        int surPosY = sur.getCurrentPos().getRight().intValue();
        g2d.drawImage(image, surPosX, surPosY, WIDTH_IMAGE, HEIGHT_IMAGE, null);
    }
    
}
