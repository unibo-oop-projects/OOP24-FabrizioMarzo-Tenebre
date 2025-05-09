package view.graphics_entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.Survivor;

public class SwingGraphicsSurvivor implements GraphicsSurvivor{

    private Graphics2D g2d;
    private static final int WIDTH_IMAGE = 80; // Pixel 
    private static final int HEIGHT_IMAGE = 128; // Pixel 
    private int heightPixelPanel;
    private double ratioX,ratioY;

    public SwingGraphicsSurvivor(final Graphics2D g2d, final int heightPixelPanel,
                                final double ratioX ,final double ratioY) {
        this.g2d = g2d;
        this.heightPixelPanel = heightPixelPanel ;
        this.ratioX = ratioX;
        this.ratioY = ratioY;
    }

    @Override
    public void drawSurvivor(final Survivor sur, final BufferedImage image) {

        int scaleSurPosX = getXinPixel(sur.getCurrentPos());
        int scaleSurPosY = heightPixelPanel-getYinPixel(sur.getCurrentPos());


        System.out.println("Scale Width " + sur.getWidth());
        System.out.println("Scale Heigth" + sur.getHeight());

        int scaleSurWidth = (int) Math.round(sur.getWidth() * this.ratioX);
        int scaleSurHeight = (int) Math.round(sur.getHeight()* this.ratioY);

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
        
        int scaleBboxUx = getXinPixel(sur.getBBox().getULcorner());
        int scaleBboxUy = heightPixelPanel - getYinPixel(sur.getBBox().getULcorner());

        g2d.setColor(Color.blue);
        g2d.drawOval(scaleBboxUx, scaleBboxUy, 5, 5);

        g2d.setColor(Color.blue);
        g2d.drawRect(scaleBboxUx, scaleBboxUy,scaleSurWidth,scaleSurHeight);

    }

    private int getXinPixel(Pair<Double,Double> pos){
        return (int) Math.round(pos.getLeft() * this.ratioX);
    }

    private int getYinPixel(Pair<Double,Double> pos){
        return (int) Math.round(pos.getRight() * this.ratioY);
    }
    
}
