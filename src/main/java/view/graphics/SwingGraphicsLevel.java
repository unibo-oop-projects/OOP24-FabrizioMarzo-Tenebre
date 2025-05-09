package view.graphics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import model.level.Level;
import view.graphics_util.IViewScale;

public class SwingGraphicsLevel implements GraphicsLevel {

    private Graphics2D g2d;
    private IViewScale viewScale;

    public SwingGraphicsLevel(final Graphics2D g2d,final IViewScale viewScale) {
        this.g2d = g2d;
        this.viewScale = viewScale;
    }

    @Override
    public void drawLevel(final Level lvl) {
       int x0 = viewScale.getXinPixel(lvl.getLevelBBox().getULcorner());
       int y0 = viewScale.getYinPixel(lvl.getLevelBBox().getULcorner());
       int x1 = viewScale.getXinPixel(lvl.getLevelBBox().getBRcorner());
       int y1 = viewScale.getYinPixel(lvl.getLevelBBox().getBRcorner());

       g2d.setColor(Color.blue);
       g2d.setStroke(new BasicStroke(5));
       g2d.drawRect(0, 0, x1-x0, Math.abs(y1-y0));

    }

}
