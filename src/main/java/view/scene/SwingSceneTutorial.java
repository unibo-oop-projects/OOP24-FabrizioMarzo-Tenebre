package view.scene;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.tuple.Pair;

import game.entities_game.FactorySurvivorGame;
import game.entities_game.IGameSurvivor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import input.input_controller.InputController;
import model.bounding_box.BoundingBox;
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
        private int levelw,levelh;
        private double ratioX,ratioY;
        BoundingBox bbox = tutLevel.getLevelBBox();
        int x0 = this.getXinPixel(bbox.getULcorner());
        int y0 = this.getYinPixel(bbox.getULcorner());
        int x1 = this.getXinPixel(bbox.getBRcorner());
        int y1 = this.getYinPixel(bbox.getBRcorner());

        public SceneTutorialPanel(final int w, final int h){

            this.h = h;
            this.w = w;

            System.out.println("Height panel " + h);
            System.out.println("Width panel " + w);

            this.levelh = (int) tutLevel.getTutorialLevelHeight();
            this.levelw = (int) tutLevel.getTutorialLevelWidth();
            
            System.out.println("Height level " + levelh);
            System.out.println("Width level " + levelw);

            this.ratioX = (double) w / levelw;
            this.ratioY = (double) h / levelh;

            System.out.println("RatioX " + ratioX);
            System.out.println("RatioiY" + ratioY);

            this.addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
        }

        public void paintComponent(Graphics g){
        
            int x0 = this.getXinPixel(bbox.getULcorner());
            int y0 = this.getYinPixel(bbox.getULcorner());
            int x1 = this.getXinPixel(bbox.getBRcorner());
            int y1 = this.getYinPixel(bbox.getBRcorner());
    
            System.out.println("Altezza panel scale  " + (x1-x0));
            System.out.println("Grandezza panel scale " + Math.abs(y1-y0));

            Graphics2D g2d = (Graphics2D) g ;
            GraphicsSurvivor graphSur = new SwingGraphicsSurvivor(g2d,this.h,this.ratioX,this.ratioY);
            gamSur.updateGraphics(graphSur);

            g2d.setColor(Color.blue);
            g2d.setStroke(new BasicStroke(5));
            g2d.drawRect(0, 0, x1-x0, Math.abs(y1-y0));

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

        private int getXinPixel(Pair<Double,Double> pos){
            return (int) Math.round(pos.getLeft() * ratioX);
        }

        private int getYinPixel(Pair<Double,Double> pos){
            return (int) Math.round(pos.getRight() * ratioY);
        }
 

    }

}
