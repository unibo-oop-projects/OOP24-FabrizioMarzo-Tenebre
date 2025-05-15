package game.game_state;

import input.input_controller.InputController;
import input.input_controller.KeyboardInputController;
import model.level.Level;
import model.level.LevelFactory;
import view.scene.Scene;
import view.scene.SwingSceneTutorial;

public class TutorialState implements GameState{

    private Scene view ;
    private Level tutLevel;
    private LevelFactory lvlFact= new LevelFactory();
    private InputController contrl;


    @Override
    public void setUp() {
        this.contrl = new KeyboardInputController();
        tutLevel = lvlFact.createTutorialLevel();
        view = new SwingSceneTutorial(tutLevel,1200, 800);
    }

    @Override
    public void processInput() {
        view.setInputController(contrl);
        System.out.println("New controller");
    }

    @Override
    public void updateGame(int elapsed) {
        tutLevel.updateLevelState(elapsed);
    }

    @Override
    public void render() {
        view.render();
    }
    
}
