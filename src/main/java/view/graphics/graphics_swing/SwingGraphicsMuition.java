package view.graphics.graphics_swing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;
import view.graphics_util.IViewScale;

public class SwingGraphicsMuition implements GraphicsMunition{

    private Graphics2D g2d;
    private IViewScale viewScale;

    public SwingGraphicsMuition(final Graphics2D g2d, final IViewScale viewScale){
        this.g2d = g2d;
        this.viewScale = viewScale;
    }

    @Override
    public void drawMunition(Munition mun, BufferedImage image) {

        int scaleSurPosX = viewScale.getXinPixel(mun.getCurrentPos());
        int scaleSurPosY = viewScale.getViewHeight()-viewScale.getYinPixel(mun.getCurrentPos());

        int scaleSurWidth = (int) Math.round(mun.getWidth() * viewScale.getRatioX());
        int scaleSurHeight = (int) Math.round(mun.getWidth()* viewScale.getRatioY());
        
        g2d.drawImage(image, scaleSurPosX, scaleSurPosY-scaleSurHeight, scaleSurWidth, scaleSurHeight, null);

    }
    
}
