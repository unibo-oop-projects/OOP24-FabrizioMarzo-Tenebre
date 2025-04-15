package view.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import input.Controller;
import input.MoveDown;
import input.MoveLeft;
import input.MoveRight;
import input.MoveUp;
import model.level.Level;
import view.survivor_game.IGameSurvivor;


public class SceneTutorialPanel extends JPanel implements KeyListener {
    private int w;
    private int h;
    private IGameSurvivor gamSur;
    private Level tutLevel;
    private Controller controller;

    public SceneTutorialPanel(final Level tutlevel,final int w, final int h, final Controller c){
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
        System.out.println("Tasto schiacciato..");
        if(e.getKeyCode() == 38){
            controller.notifyCommand(new MoveUp());
        } else if (e.getKeyCode() == 40){
            controller.notifyCommand(new MoveDown());
        } else if (e.getKeyCode() == 39){
            controller.notifyCommand(new MoveRight());
        } else if (e.getKeyCode() == 37){
            controller.notifyCommand(new MoveLeft());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
