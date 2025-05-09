package view.graphics_level;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import org.apache.commons.lang3.tuple.Pair;

import model.level.Level;

public class SwingGraphicsLevel implements GraphicsLevel {

    private Graphics2D g2d;
    private double ratioX,ratioY;

    public SwingGraphicsLevel(final Graphics2D g2d,final double ratioX, double ratioY) {
        this.g2d = g2d;
        this.ratioX = ratioX;
        this.ratioY = ratioY;
    }

    @Override
    public void drawLevel(final Level lvl) {
       int x0 = getXinPixel(lvl.getLevelBBox().getULcorner());
       int y0 = getYinPixel(lvl.getLevelBBox().getULcorner());
       int x1 = getXinPixel(lvl.getLevelBBox().getBRcorner());
       int y1 = getYinPixel(lvl.getLevelBBox().getBRcorner());

       g2d.setColor(Color.blue);
       g2d.setStroke(new BasicStroke(5));
       g2d.drawRect(0, 0, x1-x0, Math.abs(y1-y0));

    }

    private int getXinPixel(Pair<Double,Double> pos){
        return (int) Math.round(pos.getLeft() * this.ratioX);
    }

    private int getYinPixel(Pair<Double,Double> pos){
        return (int) Math.round(pos.getRight() * this.ratioY);
    }
    
}
