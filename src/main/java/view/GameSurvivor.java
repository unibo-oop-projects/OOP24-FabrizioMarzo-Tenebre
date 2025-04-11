package view;

import java.awt.Graphics2D;

import model.entities.survivor.base.Survivor;

public class GameSurvivor implements IGameSurvivor{
    private Survivor sur;
    private ImgSurvivor imgSur;

    public GameSurvivor(final Survivor sur,final ImgSurvivor imgS ){
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
