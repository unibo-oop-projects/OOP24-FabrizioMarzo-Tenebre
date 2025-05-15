package game.game_engine;

import input.input_controller.InputController;
import input.input_controller.KeyboardInputController;
import model.level.Level;
import model.level.LevelFactory;
import view.scene.Scene;
import view.scene.SwingSceneTutorial;

/**
 * Core class responsible for initializing and running the game loop.
 *
 * <p>The {@code GameEngine} handles setup, input processing, game updates,
 * rendering, and timing. It runs a fixed-timestep loop using a specified
 * period (frame delay).</p>
 */
public class GameEngine{
    private long period = 25;
    private Scene view ;
    private Level tutLevel;
    private LevelFactory lvlFact= new LevelFactory();
    private InputController contrl;

     /**
     * Initializes the game level and view.
     *
     * <p>This method sets up the tutorial level and initializes the
     * rendering view with specified screen dimensions and input controller.</p>
     */
    public void setup(){
        this.contrl = new KeyboardInputController();
        tutLevel = lvlFact.createTutorialLevel();
        view = new SwingSceneTutorial(tutLevel,1200, 800);
    }

    /**
     * Starts and runs the main game loop.
     *
     * <p>This loop continuously processes input, updates the game state,
     * renders the view, and manages frame timing to ensure consistent updates.</p>
     */
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

    /**
     * Ensures a consistent frame rate by sleeping for the remaining time
     * in the frame, if necessary.
     *
     * @param current the timestamp at the beginning of the current frame
     */
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

    /**
     * Processes player input for the current frame.
     *
     * <p>Delegates input handling to the Survivor object in the current level.</p>
     */
    protected void processInput(){
        view.setInputController(contrl);
        System.out.println("New controller");
    }

    /**
     * Updates the game state based on the time elapsed since the last frame.
     *
     * @param elapsed the time elapsed since the previous update (in milliseconds)
     */
    protected void updateGame(final int elapsed){
        tutLevel.updateLevelState(elapsed);
    }

    /**
     * Renders the current game state to the screen.
     */
    protected void render(){
        view.render();
    }

}
