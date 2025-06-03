package view.graphics.graphics_swing;

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
            
        // int scaleBboxUx = viewScale.getXinPixel(sur.getBBox().getULcorner());
        // int scaleBboxUy = viewScale.getViewHeight() - viewScale.getYinPixel(sur.getBBox().getULcorner());

        // // Draw the Survivor BoundingBox
        // g2d.setColor(Color.blue);
        // g2d.drawRect(scaleBboxUx, scaleBboxUy,scaleSurWidth,scaleSurHeight);

    }

}
