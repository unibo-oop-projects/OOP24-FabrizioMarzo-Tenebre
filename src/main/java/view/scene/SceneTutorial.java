package view.scene;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import input.Controller;
import model.level.Level;

public class SceneTutorial {
    private JFrame frame;
    private SceneTutorialPanel panel;
    private Controller controller;

    public SceneTutorial(final Level tutlevel,final int w, final int h,final Controller c){

        frame = new JFrame("L'armata delle Tenebre");
        frame.setSize(w,h);
        frame.setMinimumSize(new Dimension(w,h));
        frame.setResizable(false);

        this.controller = c;
        System.out.println("Sto dicendo al Panel che il controller è il GameEngine");
        panel = new SceneTutorialPanel(tutlevel,w,h,controller);
        
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

}
