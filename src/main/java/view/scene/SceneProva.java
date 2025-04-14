package view.scene;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.level.TutoriaLevel;

public class SceneProva {
    private JFrame frame;
    private SceneProvaPanel panel;

    public SceneProva(final TutoriaLevel tutlevel,final int w, final int h){
        frame = new JFrame("L'armata delle Tenebre");
        frame.setSize(w,h);
        frame.setMinimumSize(new Dimension(w,h));
        frame.setResizable(false);
        panel = new SceneProvaPanel(tutlevel,w,h);
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
