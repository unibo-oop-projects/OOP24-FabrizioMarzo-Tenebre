package engine;

import input.input_controller.KeyboardInputController;
import model.level.Level;
import model.level.TutorialLevel;
import view.scene.SceneTutorial;

public class GameEngine{
    private long period = 25;
    private SceneTutorial view ;
    private Level tutLevel;
    private KeyboardInputController contrl = new KeyboardInputController();

    public void setup(){
        tutLevel = new TutorialLevel();
        view = new SceneTutorial(tutLevel,1200, 800, contrl);
    }

    public void mainLoop(){
        long lastTime = System.currentTimeMillis();
        while(true){
                long current = System.currentTimeMillis();
                int elapsed = (int)(current - lastTime);
                processInput();
                updateGame(elapsed);
                render();
                waitForNextFrame(current);
                lastTime = current;
        }
    }

    protected void waitForNextFrame(long current){
        long dt = System.currentTimeMillis() - current;
        if (dt < period){
                try {
                    Thread.sleep(period-dt);
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
        }
    }

    protected void processInput(){
        tutLevel.getSurvivorOnLevel().updateInput(contrl);
    }

    protected void updateGame(final int elapsed){
        tutLevel.updateState(elapsed);
    }

    protected void render(){
        view.render();
    }

}
