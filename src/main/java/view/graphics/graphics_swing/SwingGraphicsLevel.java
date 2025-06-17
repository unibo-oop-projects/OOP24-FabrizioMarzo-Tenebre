package view.graphics.graphics_swing;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import model.level.types.Level;
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
    public void drawLevel(final Level lvl,final List<List<BufferedImage>> allImage) {
       int x0 = viewScale.getXinPixel(lvl.getLevelBBox().getULcorner());
       int y0 = viewScale.getYinPixel(lvl.getLevelBBox().getULcorner());
       int x1 = viewScale.getXinPixel(lvl.getLevelBBox().getBRcorner());
       int y1 = viewScale.getYinPixel(lvl.getLevelBBox().getBRcorner());

       g2d.setColor(Color.blue);
       g2d.setStroke(new BasicStroke(5));
       g2d.drawRect(0, 0, x1-x0, Math.abs(y1-y0));

       
        int totalWidth_px = (int) Math.round(lvl.getLevelWidth() * viewScale.getRatioX());
        int totalHeight_px = (int) Math.round(lvl.getLevelHeight() * viewScale.getRatioY());

        int tileW_px = totalWidth_px / 26;
        int tileH_px = totalHeight_px / 14;

        for (int j = 0; j < allImage.size(); j++) {
            List<BufferedImage> row = allImage.get(j);
            for (int i = 0; i < row.size(); i++) {
                BufferedImage tileImg = row.get(i);

                int x = tileW_px * i;
                int y = tileH_px * j;

                int drawWidth = (i == 25) ? totalWidth_px - tileW_px * i : tileW_px;
                int drawHeight = (j == 13) ? totalHeight_px - tileH_px * j : tileH_px;

                if (tileImg != null) {
                    g2d.drawImage(tileImg, x, y, drawWidth, drawHeight, null);
                }
            }
        }
    
    }

}
