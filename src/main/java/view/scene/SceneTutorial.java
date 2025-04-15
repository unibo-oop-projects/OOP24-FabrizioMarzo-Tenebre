package view.scene;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import input.Controller;
import model.level.Level;

public class SceneTutorial {
    private JFrame frame;
    private SceneTutorialPanel panel;
    private Controller contrl;

    public SceneTutorial(final Level tutlevel,final int w, final int h,final Controller controller){

        frame = new JFrame("L'armata delle Tenebre");
        frame.setSize(w,h);
        frame.setMinimumSize(new Dimension(w,h));
        frame.setResizable(false);

        this.contrl = controller;
        panel = new SceneTutorialPanel(tutlevel,w,h,contrl);
        
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

    public void setInputController(Controller contrl){
        this.contrl = contrl;
    }

}
