package view.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.apache.commons.lang3.tuple.Pair;

import view.FactorySurvivorGame;
import view.survivor_game.IGameSurvivor;


public class SceneProvaPanel extends JPanel {
    //private int centerX;
    //private int centerY;
    private int w;
    private int h;
    // After i want SurvivorGameFactory.....
    //private SurvivorFactory surF = new SurvivorFactory();
    private FactorySurvivorGame facSur = new FactorySurvivorGame();
    private IGameSurvivor gamSur,gamSur2;

    public SceneProvaPanel(final int w, final int h){
        //setSize(w,h); //Must to modify 
        //centerX = w/2;
        //centerY = h/2;
        this.gamSur = facSur.gameSurvivorCommon(1000,20, Pair.of(390.0,40.0),Pair.of(5.0,2.0));
        this.gamSur2 = facSur.gameSurvivorCommon(1000,20, Pair.of(0.0,0.0),Pair.of(5.0,2.0));
        this.w = w;
        this.h = h;
        setPanelSize();
    }

    public void setPanelSize(){
        Dimension size = new Dimension(w,h);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g){

        
        Graphics2D g2d = (Graphics2D) g ;

        
        System.out.println("Sto per disegnare un: "+gamSur.getSurvivor().getClass().getSimpleName());
        gamSur.updateGraphics(g2d);
        gamSur2.updateGraphics(g2d);
    }
}
