package view.survivor_game;

import java.awt.Graphics2D;

import input.input_survivor.InputController;
import input.command.Command;
import input.input_survivor.InputComponent;
import model.entities.survivor.base.Survivor;
import view.survivor_game.survivor_graphics.GraphicsSurvivor;

public class GameSurvivor implements IGameSurvivor{

    private Survivor sur;
    private GraphicsSurvivor imgSur;
    private InputComponent inpSur;

    public GameSurvivor(final Survivor sur, final GraphicsSurvivor imgS, final InputComponent inpSur){
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
        Command cmd = inpSur.update(c);
    if (cmd != null) {
        cmd.execute(this.getSurvivor());
    }
    }

}
