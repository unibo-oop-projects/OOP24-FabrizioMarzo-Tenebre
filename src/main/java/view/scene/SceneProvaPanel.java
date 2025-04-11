package view.scene;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.apache.commons.lang3.tuple.Pair;

import view.FactorySurvivorGame;
import view.survivor_game.IGameSurvivor;


public class SceneProvaPanel extends JPanel {
    private int centerX;
    private int centerY;
    
    // After i want SurvivorGameFactory.....
    //private SurvivorFactory surF = new SurvivorFactory();
    private FactorySurvivorGame facSur = new FactorySurvivorGame();

    public SceneProvaPanel(final int w, final int h){
        setSize(w,h);
        centerX = w/2;
        centerY = h/2;
    }

    public void paintComponent(Graphics g){

        IGameSurvivor gamSur = facSur.gameSurvivorCommon(1000,20, Pair.of(30.0,40.0),Pair.of(5.0,2.0));
        
        Graphics2D g2d = (Graphics2D) g ;

        //Stroke base = new BasicStroke(4f);
        //int x = (int) Math.round(gamSur.getSurvivor().getCurrentPos().getLeft());
        //int y = (int) Math.round(gamSur.getSurvivor().getCurrentPos().getRight());

        // I want this on a Method named draw on SurvivorGame class
        //g2d.setColor(Color.BLUE);
        //g2d.setStroke(base);
        //g2d.drawOval(x,y, 40, 40);
        gamSur.updateGraphics(g2d);
    }
}
