package view.graphics_component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.entities.survivor.Survivor;

public class SwingGraphicsSurvivor implements GraphicsSurvivor{

    private Graphics2D g2d;
    private static final int WIDTH_IMAGE = 80;
    private static final int HEIGHT_IMAGE = 128;
    private int heightPixelPanel;

    public SwingGraphicsSurvivor(final Graphics2D g2d, final int heightPixelPanel) {
        this.g2d = g2d;
        this.heightPixelPanel = heightPixelPanel ;
    }

    @Override
    public void drawSurvivor(final Survivor sur, final BufferedImage image) {

        int surPosX = sur.getCurrentPos().getLeft().intValue();
        int surPosY = heightPixelPanel-sur.getCurrentPos().getRight().intValue();

        
        // Draw the Survivor
        g2d.drawImage(image, surPosX, surPosY-sur.getHeight(), WIDTH_IMAGE, HEIGHT_IMAGE, null);

        // Draw the BoundingBox
        g2d.setColor(Color.red);
        g2d.drawRect(surPosX, surPosY,WIDTH_IMAGE, HEIGHT_IMAGE);
        
        g2d.setColor(Color.red);
        g2d.drawOval(surPosX, surPosY, 5, 5);
        
        int bboxUx = sur.getBBox().getULcorner().getLeft().intValue();
        int bboxUy = heightPixelPanel - sur.getBBox().getULcorner().getRight().intValue();

        g2d.setColor(Color.blue);
        g2d.drawRect(bboxUx, bboxUy,sur.getWidth(), sur.getHeight());

        g2d.setColor(Color.blue);
        g2d.drawOval(bboxUx, bboxUy, 5, 5);
    }
    
}
