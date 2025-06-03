package game.game_model.game_level;

import model.level.LevelFactory;
import view.graphics_component.level.GrahicsTutlevelComponent;

public class FactoryLevelGame {
    
    private LevelFactory lvlFactory = new LevelFactory();

    public IGameLevel gameLevelTutorial(){
        return new GameLevel(lvlFactory.createTutorialLevel(),
                             new GrahicsTutlevelComponent());

    }
}
