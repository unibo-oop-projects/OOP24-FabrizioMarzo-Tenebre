package view.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.entities_game.FactorySurvivorGame;
import game.entities_game.IGameSurvivor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import input.input_controller.InputController;
import model.level.Level;
import view.graphics_component.GraphicsSurvivor;
import view.graphics_component.SwingGraphicsSurvivor;


public class SwingSceneTutorial implements Scene {

    private JFrame frame;
    private SceneTutorialPanel panel;
    private Level tutLevel;
    private IGameSurvivor gamSur;
    private InputController inputContrl;
    private FactorySurvivorGame factSurGam = new FactorySurvivorGame();

    public SwingSceneTutorial(final Level tutlevel,final int w, final int h){

        frame = new JFrame("L'armata delle Tenebre");
        frame.setSize(w,h);
        frame.setMinimumSize(new Dimension(w,h));
        frame.setResizable(false);

        this.tutLevel = tutlevel;
        gamSur = setGameSurvivor();

        panel = new SceneTutorialPanel(w,h);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void render(){
        try {
            SwingUtilities.invokeAndWait(()->{
                frame.repaint();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private IGameSurvivor setGameSurvivor(){
        return factSurGam.gameSurvivorCommon(tutLevel.getSurvivorOnLevel());
    }

    public void setInputController(InputController inputContl){
        gamSur.updateInput(inputContl);
        this.inputContrl = inputContl;
    }

    public class SceneTutorialPanel extends JPanel implements KeyListener {

        private int w,h;

        public SceneTutorialPanel(final int w, final int h){
            this.w = w;
            this.h = h;
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
            GraphicsSurvivor graphSur = new SwingGraphicsSurvivor(g2d,this.h);

            gamSur.updateGraphics(graphSur);
        }

        @Override
	    public void keyPressed(KeyEvent e) {
            inputContrl.notifyInput(e.getKeyCode());
	    }

        @Override
        public void keyReleased(KeyEvent e) {
            inputContrl.notifyNoInput();
        }

        @Override
        public void keyTyped(KeyEvent e) {}

    }

}
