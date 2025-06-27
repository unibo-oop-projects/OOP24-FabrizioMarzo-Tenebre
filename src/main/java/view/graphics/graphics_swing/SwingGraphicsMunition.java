package view.graphics.graphics_swing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;
import view.graphics_util.Scaler;

public class SwingGraphicsMunition implements GraphicsMunition{

    private Graphics2D g2d;
    private Scaler viewScale;

    public SwingGraphicsMunition(final Graphics2D g2d, final Scaler viewScale){
        this.g2d = g2d;
        this.viewScale = viewScale;
    }

    @Override
    public void drawMunition(final Munition mun,final BufferedImage image) {


        int scaleSurPosX = viewScale.scaleX(mun.getCurrentPos());
        int scaleSurPosY = viewScale.getScaledHeight()-viewScale.scaleY(mun.getCurrentPos());

       // int scaleSurWidth = (int) Math.round(mun.getWidth() * viewScale.getRatioX());
        int scaleSurHeight = (int) Math.round(mun.getWidth()* viewScale.getRatioY());
        
        g2d.drawImage(image, scaleSurPosX, scaleSurPosY-scaleSurHeight, 6, 6, null);

    }
    
}
