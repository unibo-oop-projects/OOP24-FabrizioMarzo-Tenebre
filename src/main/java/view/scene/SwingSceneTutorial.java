package view.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.game_model.game_level.IGameLevel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import input.input_controller.InputController;
import view.graphics.GraphicsLevel;
import view.graphics.GraphicsMunition;
import view.graphics.GraphicsSurvivor;
import view.graphics.GraphicsZombie;
import view.graphics.graphics_swing.SwingGraphicsLevel;
import view.graphics.graphics_swing.SwingGraphicsMunition;
import view.graphics.graphics_swing.SwingGraphicsSurvivor;
import view.graphics.graphics_swing.SwingGraphicsZombie;
import view.graphics_util.IViewScale;
import view.graphics_util.ViewScale;


public class SwingSceneTutorial implements Scene {

    private JFrame frame;
    private SceneTutorialPanel panel;
    private IGameLevel tutLevel;
    private InputController inputContrl;
    private IViewScale viewScale;
    private int w,h;

    public SwingSceneTutorial(final IGameLevel tutLevel,final InputController inputContrl ,final int w, final int h){

        frame = new JFrame("L'armata delle Tenebre");
        frame.setMinimumSize(new Dimension(w,h));
        frame.setResizable(true);

        this.tutLevel = tutLevel;
        this.inputContrl = inputContrl;
        this.w = w;
        this.h = h;
        this.viewScale = new ViewScale((int) tutLevel.getLevel().getLevelHeight(), (int) tutLevel.getLevel().getLevelWidth(), h, w);

        panel = new SceneTutorialPanel();

        frame.getContentPane().add(panel);
        frame.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent ev){
                                System.exit(0);
                        }
                        public void windowClosed(WindowEvent ev){
                                System.exit(0);
                        }
                });
        frame.pack();
        frame.setVisible(true);
    }

    public void render(){
        try {
            SwingUtilities.invokeAndWait(()->{
                frame.repaint();
                viewScale.setNewRatio(panel.getHeight(), panel.getWidth());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class SceneTutorialPanel extends JPanel implements KeyListener {

        public SceneTutorialPanel(){
            setPanelSize();
            this.addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
        }

        public void setPanelSize(){
            Dimension size = new Dimension(w,h);
            setPreferredSize(size);
        }

        public void paintComponent(final Graphics g){
        
            Graphics2D g2d = (Graphics2D) g ;

            GraphicsLevel graphLvl = new SwingGraphicsLevel(g2d, viewScale);
            GraphicsSurvivor graphSur = new SwingGraphicsSurvivor(g2d,viewScale);
            GraphicsZombie graphZob = new SwingGraphicsZombie(g2d, viewScale);
            GraphicsMunition graphMun = new SwingGraphicsMunition(g2d, viewScale);

            tutLevel.getGameZombie().stream()
                                    .forEach( gameZombie -> gameZombie.updateGraphics(graphZob));
            tutLevel.getGameMunitions().stream()
                                       .forEach(gameMunitions -> gameMunitions.updateGraphics(graphMun));
            tutLevel.getGameSurvivor().updateGraphics(graphSur);
            tutLevel.updateGraphics(graphLvl);
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
