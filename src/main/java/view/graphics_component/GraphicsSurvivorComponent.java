package view.graphics_component;

import java.awt.Graphics2D;

import model.entities.survivor.base.Survivor;

/**
 * Interface representing a graphical component responsible for rendering a {@link Survivor}.
 * <p>
 * Implementations of this interface provide the visual representation of a survivor
 * in the game.
 */
public interface GraphicsSurvivorComponent {

    /**
     * Draws the given survivor
     *
     * @param sur the {@link Survivor} entity to be drawn
     * @param g2d the {@link Graphics2D} object used for rendering the survivor on the screen
     */
    void drawSurvivor(final Survivor sur,final Graphics2D g2d);
}
