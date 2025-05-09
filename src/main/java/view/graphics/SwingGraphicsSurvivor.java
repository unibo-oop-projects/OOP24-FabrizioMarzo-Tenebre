package view.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.entities.survivor.Survivor;
import view.graphics_util.IViewScale;

public class SwingGraphicsSurvivor implements GraphicsSurvivor{

    private Graphics2D g2d;
    private static final int WIDTH_IMAGE = 80; // Pixel 
    private static final int HEIGHT_IMAGE = 128; // Pixel 
    private int heightPixelPanel;
    private IViewScale viewScale;

    public SwingGraphicsSurvivor(final Graphics2D g2d, final int heightPixelPanel,
                                final IViewScale viewScale) {
        this.g2d = g2d;
        this.heightPixelPanel = heightPixelPanel ;
        this.viewScale = viewScale;
    }

    @Override
    public void drawSurvivor(final Survivor sur, final BufferedImage image) {

        int scaleSurPosX = viewScale.getXinPixel(sur.getCurrentPos());
        int scaleSurPosY = heightPixelPanel-viewScale.getYinPixel(sur.getCurrentPos());


        System.out.println("Scale Width " + sur.getWidth());
        System.out.println("Scale Heigth" + sur.getHeight());

        int scaleSurWidth = (int) Math.round(sur.getWidth() * viewScale.getRatioX());
        int scaleSurHeight = (int) Math.round(sur.getHeight()* viewScale.getRatioY());

        System.out.println("Scale Width " + scaleSurWidth);
        System.out.println("Scale Heigth" + scaleSurHeight);

        // Draw the Survivor
        g2d.drawImage(image, scaleSurPosX, scaleSurPosY-scaleSurHeight, WIDTH_IMAGE, HEIGHT_IMAGE, null);

        // Draw the Image BoundingBox
        g2d.setColor(Color.red);
        g2d.drawRect(scaleSurPosX, scaleSurPosY-scaleSurHeight,WIDTH_IMAGE, HEIGHT_IMAGE);

        // Draw the position of the Survivor
        g2d.setColor(Color.red);
        g2d.drawOval(scaleSurPosX, scaleSurPosY, 5, 5);
        
        int scaleBboxUx = viewScale.getXinPixel(sur.getBBox().getULcorner());
        int scaleBboxUy = heightPixelPanel - viewScale.getYinPixel(sur.getBBox().getULcorner());

        g2d.setColor(Color.blue);
        g2d.drawOval(scaleBboxUx, scaleBboxUy, 5, 5);

        g2d.setColor(Color.blue);
        g2d.drawRect(scaleBboxUx, scaleBboxUy,scaleSurWidth,scaleSurHeight);

    }

}
