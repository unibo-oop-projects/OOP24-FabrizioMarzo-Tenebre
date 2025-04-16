package model.entities_game.survivor_game;

import java.awt.Graphics2D;

import input.input_component.CompIntputSurvivor;
import input.input_controller.InputController;
import model.entities.survivor.base.Survivor;
import view.graphics_component.GraphicsSurvivorComponent;

public class GameSurvivor implements IGameSurvivor{

    private Survivor sur;
    private GraphicsSurvivorComponent imgSur;
    private CompIntputSurvivor inpSur;

    public GameSurvivor(final Survivor sur, final GraphicsSurvivorComponent imgS, final CompIntputSurvivor inpSur){
        this.sur = sur;
        this.imgSur = imgS;
        this.inpSur = inpSur;
    }

    @Override
    public Survivor getSurvivor() {
        return this.sur;    
    }

    @Override
    public void updateGraphics(final Graphics2D g2d) {
        imgSur.drawSurvivor(this.getSurvivor(),g2d);
    }

    @Override
    public void updateInput(InputController c) {
        inpSur.update(this.getSurvivor(),c);
    }

}
