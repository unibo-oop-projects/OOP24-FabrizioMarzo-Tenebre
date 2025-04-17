package view.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import input.input_controller.KeyboardInputController;
import model.entities_game.survivor_game.IGameSurvivor;
import model.level.Level;


public class SceneTutorialPanel extends JPanel implements KeyListener {
    private int w;
    private int h;
    private IGameSurvivor gamSur;
    private Level tutLevel;
    private KeyboardInputController controller;

    public SceneTutorialPanel(final Level tutlevel,final int w, final int h, final KeyboardInputController c){
        this.w = w;
        this.h = h;
        this.tutLevel = tutlevel;
        System.out.println("Ho settato il controller del panel");
        this.controller = c;
        setPanelSize();

        this.addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void setPanelSize(){
        Dimension size = new Dimension(w,h);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g){

        
        Graphics2D g2d = (Graphics2D) g ;

        gamSur = tutLevel.getSurvivorOnLevel();
        gamSur.updateGraphics(g2d);
    }

    @Override
	public void keyPressed(KeyEvent e) {
        controller.notifyMove(e.getKeyCode());
	}


    @Override
    public void keyReleased(KeyEvent e) {
        controller.notifyNoMove();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
