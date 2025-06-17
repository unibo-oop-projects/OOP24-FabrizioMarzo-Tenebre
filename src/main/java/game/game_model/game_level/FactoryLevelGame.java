package game.game_model.game_level;

import model.level.types.ILevelFactory;
import model.level.types.LevelFactory;
import model.level.types.LevelType;
import view.graphics_component.level.GrahicsTutlevelComponent;

public class FactoryLevelGame {
    
    private final ILevelFactory lvlFactory = new LevelFactory();

    public IGameLevel createLevelGame(final LevelType lvlType){
        return new GameLevel(lvlFactory.createLevel(lvlType).get(),
                             new GrahicsTutlevelComponent());

    }
}
