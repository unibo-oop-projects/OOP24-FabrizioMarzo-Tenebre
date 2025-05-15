package game.game_level;

import model.level.Level;
import view.graphics.GraphicsLevel;
import view.graphics_component.level.GraphicsLevelComponent;

public class GameLevel implements IGameLevel {

    private Level lvl;
    private GraphicsLevelComponent imgLvl;

    
    public GameLevel(Level lvl, GraphicsLevelComponent imgLvl) {
        this.lvl = lvl;
        this.imgLvl = imgLvl;
    }


    @Override
    public Level getLevel() {
        return this.lvl;
    }

   
    @Override
    public void updateGraphics(final GraphicsLevel graphicLvl) {
        imgLvl.update(this.lvl,graphicLvl);
    }
    
}
