package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorFactory;

public class SceneProvaPanel extends JPanel {
    private int centerX;
    private int centerY;
    
    // After i want SurvivorGameFactory.....
    private SurvivorFactory surF = new SurvivorFactory();

    public SceneProvaPanel(final int w, final int h){
        setSize(w,h);
        centerX = w/2;
        centerY = h/2;
    }

    public void paintComponent(Graphics g){
        Survivor su1 = surF.createCommon(1000,20, Pair.of(30.0,40.0),Pair.of(5.0,2.0));
        Graphics2D g2d = (Graphics2D) g ;

        Stroke base = new BasicStroke(4f);
        int x = (int) Math.round(su1.getCurrentPos().getLeft());
        int y = (int) Math.round(su1.getCurrentPos().getRight());

        g2d.setColor(Color.BLUE);
        g2d.setStroke(base);
        g2d.drawOval(x,y, 40, 40);
    }
}
