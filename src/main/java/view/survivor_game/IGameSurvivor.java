package view.survivor_game;

import java.awt.Graphics2D;

import input.input_controller.InputController;
import model.entities.survivor.base.Survivor;

public interface IGameSurvivor {
    public Survivor getSurvivor();
    // public void getBBox();
    public void updateInput(final InputController controller);
    public void updateGraphics(final Graphics2D g2d);
}
