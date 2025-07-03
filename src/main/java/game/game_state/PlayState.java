package game.game_state;

import game.game_model.game_level.IGameLevel;
import input.input_controller.InputController;
import input.input_controller.KeyboardInputController;
import view.scene.Scene;
import view.scene.SwingSceneTutorial;

/**
 * Represents the main gameplay state where the player interacts with the game
 * level.
 * <p>
 * This state handles setting up the current level, processing user input,
 * updating the game state including level progression, and rendering the view.
 * </p>
 */
public class PlayState implements GameState {

    private Scene view;
    private IGameLevel gameLevel;
    private InputController contrl = new KeyboardInputController();
    private PlayStateManager managerPlayGame = new PlayStateManager();

    /**
     * Initializes the gameplay state by loading the current level.
     */
    @Override
    public void setUp() {
        loadLevel(managerPlayGame.loadCurrentLevel());
    }

    /**
     * Processes input by forwarding input updates to the survivor entity.
     */
    @Override
    public void processInput() {
        gameLevel.getGameSurvivor().updateInput(contrl);
    }

    /**
     * Updates the game level and checks for level completion.
     * If the level is completed, attempts to load the next level.
     * 
     * @param elapsed time elapsed since last update, in milliseconds
     */
    @Override
    public void updateGame(final int elapsed) {
        gameLevel.getLevel().updateLevelState(elapsed);
        gameLevel.updateStateGameLevel();

        if (gameLevel.getLevel().isLevelCompleted()) {
            managerPlayGame.loadNextLevel().ifPresentOrElse(
                    this::loadLevel,
                    () -> {
                        System.out.println("All levels are completed");
                    });
        }
    }

    /**
     * Renders the current scene view.
     */
    @Override
    public void render() {
        view.render();
    }

    /**
     * Loads the specified game level and initializes the corresponding view.
     * 
     * @param level the game level to load
     */
    private void loadLevel(final IGameLevel level) {
        this.gameLevel = level;
        this.view = new SwingSceneTutorial(gameLevel, contrl, 1200, 800);
    }

}
