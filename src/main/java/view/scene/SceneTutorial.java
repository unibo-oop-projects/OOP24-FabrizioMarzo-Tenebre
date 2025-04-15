package view.scene;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import input.input_survivor.KeyboardInputController;
import model.level.Level;

public class SceneTutorial {
    private JFrame frame;
    private SceneTutorialPanel panel;

    public SceneTutorial(final Level tutlevel,final int w, final int h,final KeyboardInputController c){

        frame = new JFrame("L'armata delle Tenebre");
        frame.setSize(w,h);
        frame.setMinimumSize(new Dimension(w,h));
        frame.setResizable(false);

        System.out.println("Sto dicendo al Panel che il controller è il GameEngine");
        panel = new SceneTutorialPanel(tutlevel,w,h,c);
        
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
