package view.graphics.graphics_swing;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import model.level.Level;
import view.graphics.GraphicsLevel;
import view.graphics_util.IViewScale;

public class SwingGraphicsLevel implements GraphicsLevel {

    private Graphics2D g2d;
    private IViewScale viewScale;

    public SwingGraphicsLevel(final Graphics2D g2d,final IViewScale viewScale) {
        this.g2d = g2d;
        this.viewScale = viewScale;
    }

    @Override
    public void drawLevel(final Level lvl,final List<BufferedImage> listLevelSprite, final List<List<Integer>> listLevelData) {
       int x0 = viewScale.getXinPixel(lvl.getLevelBBox().getULcorner());
       int y0 = viewScale.getYinPixel(lvl.getLevelBBox().getULcorner());
       int x1 = viewScale.getXinPixel(lvl.getLevelBBox().getBRcorner());
       int y1 = viewScale.getYinPixel(lvl.getLevelBBox().getBRcorner());

       g2d.setColor(Color.blue);
       g2d.setStroke(new BasicStroke(5));
       g2d.drawRect(0, 0, x1-x0, Math.abs(y1-y0));

        for (int j = 0 ; j < 14 ; j ++  ){
            for (int i=0 ; i<26 ; i++ ){
                int index = listLevelData.get(j).get(i);
                g2d.drawImage(listLevelSprite.get(index), 50 *i, 50*j , 50,50,null);
            }
        }
       

    }

}
