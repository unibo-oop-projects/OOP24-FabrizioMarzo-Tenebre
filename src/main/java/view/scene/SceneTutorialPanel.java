package view.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import input.input_controller.KeyboardInputController;
import model.entities.entities_game.survivor_game.IGameSurvivor;
import model.level.Level;
import view.graphics_component.GraphicsSurvivor;
import view.graphics_component.SwingGraphicsSurvivor;

/**
 * Custom JPanel responsible for rendering the tutorial level and handling keyboard input.
 * 
 * <p>This panel interacts with the game level and the survivor object to update
 * the display based on user input. It also listens for key events to update the
 * movement direction of the survivor.</p>
 */
public class SceneTutorialPanel extends JPanel implements KeyListener {
    /** w, h are te weight and height of the panel on jframe */
    private int w,h;
    /** gamsur is the {@link IGameSurvivor} on the Scene */
    private IGameSurvivor gamSur;
    /** tutLevel is the {@link Level} that this Scene representing */
    private Level tutLevel;
    /** controller is the {@link KeyboardInputController} associated with the Scene */
    private KeyboardInputController controller;


    /**
     * Constructs a new {@code SceneTutorialPanel} with the given level, dimensions, and input controller.
     *
     * @param tutlevel the level to be displayed in the panel
     * @param w the width of the panel
     * @param h the height of the panel
     * @param c the keyboard input controller to handle user input
     */
    public SceneTutorialPanel(final Level tutlevel,final int w, final int h, final KeyboardInputController c){
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

    /**
     * Sets the preferred size of the panel to the given width and height.
     */
    public void setPanelSize(){
        Dimension size = new Dimension(w,h);
        setPreferredSize(size);
    }

    /**
     * Paints the component by rendering the survivor graphics from the current level.
     *
     * @param g the {@link Graphics} object used to paint the panel
     */
    public void paintComponent(Graphics g){
        
        Graphics2D g2d = (Graphics2D) g ;
        GraphicsSurvivor graphSur = new SwingGraphicsSurvivor(g2d);

        gamSur = tutLevel.getSurvivorOnLevel();
        gamSur.updateGraphics(graphSur);
    }

    /**
     * Handles key press events and notifies the controller of the movement action.
     *
     * @param e the {@link KeyEvent} representing the key that was pressed
     */
    @Override
	public void keyPressed(KeyEvent e) {
        controller.notifyMove(e.getKeyCode());
	}

    /**
     * Handles key release events and notifies the controller that there is no longer any movement input.
     *
     * @param e the {@link KeyEvent} representing the key that was released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        controller.notifyNoMove();
    }

    /**
     * This method is not used but must be implemented as part of the {@link KeyListener} interface.
     *
     * @param e the {@link KeyEvent} representing the typed key
     */
    @Override
    public void keyTyped(KeyEvent e) {}
}
