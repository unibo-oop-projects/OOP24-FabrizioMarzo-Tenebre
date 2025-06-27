package view.graphics.graphics_swing;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;

import model.entities.zombie.Zombie;
import view.graphics.GraphicsZombie;
import view.graphics_util.Scaler;

public class SwingGraphicsZombie implements GraphicsZombie {

    private static final Color COLOR_RED = new Color(255, 0, 0);
    private static final Color COLOR_ORANGE = new Color(255, 140, 0);
    private static final Color COLOR_DARK_GRAY = new Color(255, 140, 0);

    private Graphics2D g2d;
    private Scaler viewScale;

    public SwingGraphicsZombie(final Graphics2D g2d, final Scaler viewScale){
        this.g2d = g2d;
        this.viewScale = viewScale;
    }

    @Override
    public void drawZombie(final Zombie zob,final BufferedImage image) {
        
        int scaleSurPosX = viewScale.scaleX(zob.getCurrentPos());
        int scaleSurPosY = viewScale.getScaledHeight()-viewScale.scaleY(zob.getCurrentPos());

        int scaleSurWidth = (int) Math.round(zob.getWidth() * viewScale.getRatioX());
        int scaleSurHeight = (int) Math.round(zob.getHeight()* viewScale.getRatioY());

        g2d.drawImage(image, scaleSurPosX, scaleSurPosY-scaleSurHeight, scaleSurWidth, scaleSurHeight, null);
        drawZombieHealth(zob, scaleSurPosX, scaleSurPosY, scaleSurHeight, scaleSurWidth);
        //drawBoundingBox(zob, scaleSurHeight, scaleSurWidth);

    }
    
    private void drawZombieHealth(final Zombie zob, final int scaleSurPosX, final int scaleSurPosY, 
                                    final int scaleSurHeight, final int scaleSurWidth){

        int rectWidth = scaleSurWidth;
        int rectHeight = 6;
        int rectX = scaleSurPosX;
        int rectY = scaleSurPosY - scaleSurHeight - rectHeight - 5;
                                
        final int maxLife = zob.getMaxZombieHealth(); 
        int currentLife = zob.getLive();
        int currentWidth = (int) ((double) currentLife / maxLife * rectWidth);
                                
        // Background bar
        g2d.setColor(Color.BLACK);
        g2d.fillRect(rectX, rectY, rectWidth, rectHeight);
                                
        // Choose life bar color (starts red, fades to gray)
        float healthRatio = (float) currentLife / maxLife;
                                
        if (healthRatio > 0.5) {
            g2d.setColor(COLOR_RED);  // Red
        } else if (healthRatio > 0.25) {
            g2d.setColor(COLOR_ORANGE);  // Orange
        } else {
            g2d.setColor(COLOR_DARK_GRAY);  // Dark Gray (almost dead)
        }
        g2d.fillRect(rectX, rectY, currentWidth, rectHeight);
    }
    
    @SuppressWarnings("unused")
    private void drawBoundingBox(final Zombie zob, final int scaleSurHeight, final int scaleSurWidth){
        int scaleBboxUx = viewScale.scaleX(zob.getBBox().getULcorner());
        int scaleBboxUy = viewScale.getScaledHeight() - viewScale.scaleY(zob.getBBox().getULcorner());
    
        // Draw the Survivor BoundingBox
        g2d.setColor(Color.blue);
        g2d.drawRect(scaleBboxUx, scaleBboxUy,scaleSurWidth,scaleSurHeight);
    }

}
