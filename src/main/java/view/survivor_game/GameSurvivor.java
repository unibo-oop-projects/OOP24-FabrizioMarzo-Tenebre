package view.survivor_game;

import java.awt.Graphics2D;

import model.entities.survivor.base.Survivor;
import view.graphics_survivor.GraphicsSurvivor;

public class GameSurvivor implements IGameSurvivor{
    private Survivor sur;
    private GraphicsSurvivor imgSur;

    public GameSurvivor(final Survivor sur,final GraphicsSurvivor imgS ){
        this.sur = sur;
        this.imgSur = imgS;
    }

    @Override
    public Survivor getSurvivor() {
        return this.sur;    
    }

    @Override
    public void updateGraphics(final Graphics2D g2d) {
        imgSur.drawSurvivor(g2d);
    }

}
