package view.scene;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import input.input_controller.KeyboardInputController;
import model.level.Level;

/**
 * Graphical scene responsible for rendering the tutorial level.
 * 
 * <p>{@code SceneTutorial} sets up the main game window using Swing, and 
 * integrates the game panel that handles rendering and user input.</p>
 */
public class SceneTutorial {
    private JFrame frame;
    private SceneTutorialPanel panel;

    /**
     * Constructs the tutorial scene and initializes the window and rendering panel.
     *
     * @param tutlevel the tutorial level to be displayed
     * @param w the width of the game window
     * @param h the height of the game window
     * @param c the keyboard input controller to handle user input
     */
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

    /**
     * Repaints the game window.
     *
     * <p>This method uses {@link SwingUtilities#invokeAndWait} to ensure
     * that repainting happens on the Event Dispatch Thread.</p>
     */
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
