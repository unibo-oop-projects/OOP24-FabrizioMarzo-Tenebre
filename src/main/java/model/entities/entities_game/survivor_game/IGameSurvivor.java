package model.entities.entities_game.survivor_game;

import java.awt.Graphics2D;

import input.input_controller.InputController;
import model.entities.entities_base.survivor_base.Survivor;
import model.level.Level;
import view.graphics_component.GraphicsSurvivor;

/**
 * Interface that defines the behavior of a game survivor in the game.
 * This interface provides methods for retrieving the survivor, updating the survivor based on user input,
 * and rendering the survivor's graphics.
 */
public interface IGameSurvivor {

    /**
     * Gets the survivor associated with this game entity.
     *
     * @return the {@link Survivor} object representing the game survivor
     */
    public Survivor getSurvivor();

    /**
     * Updates the survivor based on input from the controller.
     * This method processes the input and modifies the survivor's state accordingly.
     *
     * @param controller the {@link InputController} that handles user input
     */
    public void updateInput(final InputController controller);

    /**
     * Updates the graphics of the survivor.
     * This method is used to render the survivor's visual representation on the screen.
     *
     * @param g2d the {@link Graphics2D} object used for drawing the survivor's graphics
     */
    public void updateGraphics(final GraphicsSurvivor grapSur);

    public void updatePhysics(final int dt, final Level lvl);
}
