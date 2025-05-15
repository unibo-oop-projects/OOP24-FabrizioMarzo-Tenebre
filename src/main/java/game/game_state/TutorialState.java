package game.game_state;

import game.game_level.FactoryLevelGame;
import game.game_level.IGameLevel;
import input.input_controller.InputController;
import input.input_controller.KeyboardInputController;
import view.scene.Scene;
import view.scene.SwingSceneTutorial;

public class TutorialState implements GameState{

    private Scene view ;
    private IGameLevel tutLevel;
    private FactoryLevelGame lvlGameFct = new FactoryLevelGame();
    private InputController contrl;


    @Override
    public void setUp() {
        this.contrl = new KeyboardInputController();
        this.tutLevel = lvlGameFct.gameLevelTutorial();
        this.view = new SwingSceneTutorial(tutLevel,contrl,1200, 800);
    }

    @Override
    public void processInput() {
        tutLevel.getGameSurvivor().updateInput(contrl);
        System.out.println("New controller");
    }

    @Override
    public void updateGame(int elapsed) {
        tutLevel.getLevel().updateLevelState(elapsed);
    }

    @Override
    public void render() {
        view.render();
    }
    
}
