package game.game_state;

import game.game_model.game_level.IGameLevel;
import input.input_controller.InputController;
import input.input_controller.KeyboardInputController;
import view.scene.Scene;
import view.scene.SwingSceneTutorial;

public class PlayState implements GameState{

    private Scene view ;
    private IGameLevel gameLevel;
    private InputController contrl = new KeyboardInputController();
    private PlayStateManager managerPlayGame = new PlayStateManager();

    @Override
    public void setUp() {
        loadLevel(managerPlayGame.loadCurrentLevel());
    }

    @Override
    public void processInput() {
        gameLevel.getGameSurvivor().updateInput(contrl);
    }

    @Override
    public void updateGame(final int elapsed) {
        gameLevel.getLevel().updateLevelState(elapsed);
        gameLevel.updateStateGameLevel();

        if (gameLevel.getLevel().isLevelCompleted()) {
            managerPlayGame.loadNextLevel().ifPresentOrElse(
                this::loadLevel,
                () -> {
                    System.out.println("All levels are completed");
                }
            );
        }
    }

    @Override
    public void render() {
        view.render();
    }

    private void loadLevel(final IGameLevel level) {
        this.gameLevel = level;
        this.view = new SwingSceneTutorial(gameLevel, contrl, 1200, 800);
    }
    
}
