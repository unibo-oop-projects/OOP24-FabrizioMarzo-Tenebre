package model.entities.entities_game.survivor_game;

import java.awt.Graphics2D;

import input.input_component.InputSurvivorComponent;
import input.input_controller.InputController;
import model.entities.entities_base.survivor_base.Survivor;
import physics.physics_component.PhysicsSurvivorComponent;
import view.graphics_component.GraphicsSurvivor;
import view.graphics_component.GraphicsSurvivorComponent;

/**
 * Implementation of the {@link IGameSurvivor} interface.
 * This class represents a game survivor entity, managing the survivor's state,
 * input handling, and graphical representation.
 */
public class GameSurvivor implements IGameSurvivor{

    private Survivor sur;
    private GraphicsSurvivorComponent imgSur;
    private InputSurvivorComponent inpSur;
    private PhysicsSurvivorComponent phySur;

    /**
     * Constructs a {@link GameSurvivor} object with the given survivor, graphics component, and input component.
     *
     * @param sur    the {@link Survivor} object that represents the survivor
     * @param imgS   the {@link GraphicsSurvivorComponents} responsible for rendering the survivor's graphics
     * @param inpSur the {@link InputSurvivorComponent} component that handles input for the survivor
     */
    public GameSurvivor(final Survivor sur, final GraphicsSurvivorComponent imgS, 
                        final InputSurvivorComponent inpSur, final PhysicsSurvivorComponent phySur){
        this.sur = sur;
        this.imgSur = imgS;
        this.inpSur = inpSur;
        this.phySur = phySur;
    }


    /**
     * {@inheritDoc}
     * 
     * Returns the survivor object associated with this game survivor.
     *
     * @return the {@link Survivor} object
     */
    @Override
    public Survivor getSurvivor() {
        return this.sur;    
    }

    /**
     * {@inheritDoc}
     * 
     * Updates the graphics of the survivor by drawing its visual representation.
     *
     * @param g2d the {@link Graphics2D} object used for rendering the survivor
     */
    @Override
    public void updateGraphics(final GraphicsSurvivor graphicsSur) {
        imgSur.update(this.getSurvivor(), graphicsSur);
    }

    /**
     * {@inheritDoc}
     * 
     * Updates the survivor's state based on the provided input.
     * This method processes the input and adjusts the survivor's behavior accordingly.
     *
     * @param c the {@link InputController} used to capture user input
     */
    @Override
    public void updateInput(final InputController c) {
        inpSur.update(this.getSurvivor(),c);
    }

    @Override
    public void updatePhysics(final int dt){
		phySur.update(this.getSurvivor(), dt);
	}

}
