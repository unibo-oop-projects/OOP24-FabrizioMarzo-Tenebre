package game.game_state;

import game.game_level.FactoryLevelGame;
import game.game_level.IGameLevel;
import input.input_controller.InputController;
import input.input_controller.KeyboardInputController;
import view.scene.Scene;
import view.scene.SwingSceneTutorial;

public class PlayState implements GameState{

    private Scene view ;
    private IGameLevel level;
    private FactoryLevelGame lvlGameFct = new FactoryLevelGame();
    private InputController contrl;


    @Override
    public void setUp() {
        this.contrl = new KeyboardInputController();
        this.level = lvlGameFct.gameLevelTutorial();
        this.view = new SwingSceneTutorial(level,contrl,1200, 800);
    }

    @Override
    public void processInput() {
        level.getGameSurvivor().updateInput(contrl);
        System.out.println("New controller");
    }

    @Override
    public void updateGame(final int elapsed) {
        level.getLevel().updateLevelState(elapsed);
    }

    @Override
    public void render() {
        view.render();
    }
    
}
