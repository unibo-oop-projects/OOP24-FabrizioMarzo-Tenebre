package view.survivor_game.survivor_graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import model.entities.survivor.base.Survivor;


public class CommonGraphics implements GraphicsSurvivor {

    @Override
    public void drawSurvivor(final Survivor sur,final Graphics2D g2d) {
        Stroke base = new BasicStroke(4f);
        int x = sur.getCurrentPos().getLeft().intValue();
        int y = sur.getCurrentPos().getRight().intValue();
        g2d.setColor(Color.BLUE);
        g2d.setStroke(base);
        g2d.drawOval(x,y, 40, 40);
        System.out.println("I am painting !!");
    }

}
