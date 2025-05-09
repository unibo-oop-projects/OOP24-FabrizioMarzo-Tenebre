package game.level_game;

import model.level.Level;
import view.graphics_component.level.GrahicsTutlevelComponent;

public class FactoryLevelGame {
    
    public IGameLevel gameLevelTutorial(final Level lvl){
        return new GameLevel(lvl,
                             new GrahicsTutlevelComponent());

    }
}
