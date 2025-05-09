package view.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.entities_game.FactorySurvivorGame;
import game.entities_game.IGameSurvivor;
import game.level_game.FactoryLevelGame;
import game.level_game.IGameLevel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import input.input_controller.InputController;
import model.level.Level;
import view.graphics.GraphicsLevel;
import view.graphics.GraphicsSurvivor;
import view.graphics.SwingGraphicsLevel;
import view.graphics.SwingGraphicsSurvivor;
import view.graphics_util.IViewScale;
import view.graphics_util.ViewScale;


public class SwingSceneTutorial implements Scene {

    private JFrame frame;
    private SceneTutorialPanel panel;
    private IGameLevel gamLvl;
    private IGameSurvivor gamSur;
    private InputController inputContrl;
    private FactorySurvivorGame factSurGam = new FactorySurvivorGame();
    private FactoryLevelGame factLvlGam = new FactoryLevelGame();
    private IViewScale viewScale;
    private int w,h;

    public SwingSceneTutorial(final Level tutlevel,final int w, final int h){

        frame = new JFrame("L'armata delle Tenebre");
        frame.setMinimumSize(new Dimension(w,h));
        frame.setResizable(true);

        gamLvl = setGameLevel(tutlevel);
        gamSur = setGameSurvivor(tutlevel);

        this.w = w;
        this.h = h;
        this.viewScale = new ViewScale((int) tutlevel.getLevelHeight(), (int) tutlevel.getLevelWidth(), h, w);

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

    private IGameSurvivor setGameSurvivor(final Level lvl){
        return factSurGam.gameSurvivorCommon(lvl.getSurvivorOnLevel());
    }

    private IGameLevel setGameLevel(final Level lvl){
        return factLvlGam.gameLevelTutorial(lvl);
    }

    public void setInputController(InputController inputContl){
        gamSur.updateInput(inputContl);
        this.inputContrl = inputContl;
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

        public void paintComponent(Graphics g){
        
            Graphics2D g2d = (Graphics2D) g ;

            GraphicsLevel graphLvl = new SwingGraphicsLevel(g2d, viewScale);
            GraphicsSurvivor graphSur = new SwingGraphicsSurvivor(g2d,h,viewScale);
            gamSur.updateGraphics(graphSur);
            gamLvl.updateGraphics(graphLvl);
            System.out.println("New mode to Painting");
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
