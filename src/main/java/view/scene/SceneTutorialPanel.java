package view.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import input.Controller;
import model.level.Level;
import view.survivor_game.IGameSurvivor;


public class SceneTutorialPanel extends JPanel implements KeyListener {
    private int w;
    private int h;
    private IGameSurvivor gamSur;
    private Level tutLevel;

    public SceneTutorialPanel(final Level tutlevel,final int w, final int h, final Controller controller){
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

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
}
