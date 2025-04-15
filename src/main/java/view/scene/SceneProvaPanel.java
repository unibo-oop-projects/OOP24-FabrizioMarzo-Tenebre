package view.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.level.Level;
import view.survivor_game.IGameSurvivor;


public class SceneProvaPanel extends JPanel {
    private int w;
    private int h;
    private IGameSurvivor gamSur;
    private Level tutLevel;

    public SceneProvaPanel(final Level tutlevel,final int w, final int h){
        this.w = w;
        this.h = h;
        this.tutLevel = tutlevel;
        setPanelSize();
    }

    public void setPanelSize(){
        Dimension size = new Dimension(w,h);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g){

        
        Graphics2D g2d = (Graphics2D) g ;

        gamSur = tutLevel.getSurvivorOnLevel();
        System.out.println("Sto per disegnare un: "+gamSur.getSurvivor().getClass().getSimpleName());
        gamSur.updateGraphics(g2d);
    }
}
