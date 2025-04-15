package view.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.apache.commons.lang3.tuple.Pair;

import model.level.TutoriaLevel;
import view.survivor_game.FactorySurvivorGame;
import view.survivor_game.IGameSurvivor;


public class SceneProvaPanel extends JPanel {
    private int w;
    private int h;
    private IGameSurvivor gamSur;
    private TutoriaLevel tutLevel;

    public SceneProvaPanel(final TutoriaLevel tutlevel,final int w, final int h){
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

        gamSur = tutLevel.getSurvivor();
        System.out.println("Sto per disegnare un: "+gamSur.getSurvivor().getClass().getSimpleName());
        gamSur.updateGraphics(g2d);
    }
}
