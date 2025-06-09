package view.graphics.graphics_swing;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.entities.survivor.Survivor;
import view.graphics.GraphicsSurvivor;
import view.graphics_util.IViewScale;

public class SwingGraphicsSurvivor implements GraphicsSurvivor{

    private Graphics2D g2d;
    private IViewScale viewScale;

    public SwingGraphicsSurvivor(final Graphics2D g2d, final IViewScale viewScale) {
        this.g2d = g2d;
        this.viewScale = viewScale;
    }

    @Override
    public void drawSurvivor(final Survivor sur, final BufferedImage image) {

        int scaleSurPosX = viewScale.getXinPixel(sur.getCurrentPos());
        int scaleSurPosY = viewScale.getViewHeight()-viewScale.getYinPixel(sur.getCurrentPos());

        int scaleSurWidth = (int) Math.round(sur.getWidth() * viewScale.getRatioX());
        int scaleSurHeight = (int) Math.round(sur.getHeight()* viewScale.getRatioY());
        
        // Draw the Survivor
        g2d.drawImage(image, scaleSurPosX, scaleSurPosY-scaleSurHeight, scaleSurWidth, scaleSurHeight, null);
        drawSurvivorHealth(sur, scaleSurPosX, scaleSurPosY, scaleSurHeight, scaleSurWidth);
    }

    private void drawSurvivorHealth(final Survivor sur,final int scaleSurPosX, final int scaleSurPosY,
                                        final int scaleSurHeight, final int scaleSurWidth){

        // Dimension of the rectangle (life bar)
        int rectWidth = scaleSurWidth;
        int rectHeight = 6;  // height rect
        
        // Position of the rectangle 
        int rectX = scaleSurPosX;
        int rectY = scaleSurPosY - scaleSurHeight - rectHeight - 5;  // 5 pixel up zombie head 
        
        // Calculate actual width according to Survivor life
        final int maxLife = sur.getMaxSurvivorHealth(); 
        int currentLife = sur.getLive();
        int currentWidth = (int) ((double) currentLife / maxLife * rectWidth);

        // Draw background (black) rectangle
        g2d.setColor(Color.BLACK);
        g2d.fillRect(rectX, rectY, rectWidth, rectHeight);

        // Decide life bar color based on health ratio
        float healthRatio = (float) currentLife / maxLife;
        if (healthRatio > 0.5) {
            g2d.setColor(Color.GREEN);
        } else if (healthRatio > 0.25) {
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(Color.RED);
        }

        // Draw life bar
        g2d.fillRect(rectX, rectY, currentWidth, rectHeight);
    }

    @SuppressWarnings("unused")
    private void drawBoundingBox(final Survivor sur, final int scaleSurHeight, final int scaleSurWidth){
        int scaleBboxUx = viewScale.getXinPixel(sur.getBBox().getULcorner());
        int scaleBboxUy = viewScale.getViewHeight() - viewScale.getYinPixel(sur.getBBox().getULcorner());
    
        // Draw the Survivor BoundingBox
        g2d.setColor(Color.blue);
        g2d.drawRect(scaleBboxUx, scaleBboxUy,scaleSurWidth,scaleSurHeight);
    }
}
