package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

public class SceneProvaPanel extends JPanel {
    private int centerX;
    private int centerY;


    public SceneProvaPanel(final int w, final int h){
        setSize(w,h);
        centerX = w/2;
        centerY = h/2;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g ;

        Stroke base = new BasicStroke(4f);

        g2d.setColor(Color.BLUE);
        g2d.setStroke(base);
        g2d.drawOval(centerX, centerY, 40, 40);
    }
}
