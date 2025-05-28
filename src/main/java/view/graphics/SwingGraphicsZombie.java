package view.graphics;

import java.awt.image.BufferedImage;
//import java.awt.Color;
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
        
        int scaleSurPosX = viewScale.getXinPixel(zob.getCurrentPos());
        int scaleSurPosY = viewScale.getViewHeight()-viewScale.getYinPixel(zob.getCurrentPos());

        int scaleSurWidth = (int) Math.round(zob.getWidth() * viewScale.getRatioX());
        int scaleSurHeight = (int) Math.round(zob.getHeight()* viewScale.getRatioY());

        g2d.drawImage(image, scaleSurPosX, scaleSurPosY-scaleSurHeight, scaleSurWidth, scaleSurHeight, null);

        // int scaleBboxUx = viewScale.getXinPixel(zob.getBBox().getULcorner());
        // int scaleBboxUy = viewScale.getViewHeight() - viewScale.getYinPixel(zob.getBBox().getULcorner());

        // // Draw the Survivor BoundingBox
        // g2d.setColor(Color.blue);
        // g2d.drawRect(scaleBboxUx, scaleBboxUy,scaleSurWidth,scaleSurHeight);

    }
    
}
