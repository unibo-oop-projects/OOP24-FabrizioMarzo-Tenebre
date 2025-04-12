package view.graphics_survivor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;


public class CommonGraphics implements GraphicsSurvivor {

    @Override
    public void drawSurvivor(Graphics2D g2d) {
        Stroke base = new BasicStroke(4f);
        g2d.setColor(Color.BLUE);
        g2d.setStroke(base);
        g2d.drawOval(100,100, 40, 40);
        System.out.println("I am painting !!");
    }

}
