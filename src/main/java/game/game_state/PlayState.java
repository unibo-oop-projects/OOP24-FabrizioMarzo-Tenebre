package game.game_state;

import game.game_model.game_level.FactoryLevelGame;
import game.game_model.game_level.IGameLevel;
import input.input_controller.InputController;
import input.input_controller.KeyboardInputController;
import model.level.types.LevelType;
import view.scene.Scene;
import view.scene.SwingSceneTutorial;

public class PlayState implements GameState{

    private Scene view ;
    private IGameLevel gameLevel;
    private FactoryLevelGame lvlGameFct = new FactoryLevelGame();
    private InputController contrl;

    @Override
    public void setUp() {
        this.contrl = new KeyboardInputController();
        this.gameLevel = lvlGameFct.createLevelGame(LevelType.LEVEL_TUTORIAL);
        this.view = new SwingSceneTutorial(gameLevel,contrl,1200, 800);
    }

    @Override
    public void processInput() {
        gameLevel.getGameSurvivor().updateInput(contrl);
    }

    @Override
    public void updateGame(final int elapsed) {
        gameLevel.getLevel().updateLevelState(elapsed);
        gameLevel.updateStateGameLevel();
    }

    @Override
    public void render() {
        view.render();
    }
    
}
