package game.game_engine;

import game.game_state.GameState;

/**
 * Core game engine responsible for running the main game loop.
 * 
 * <p>
 * This engine controls the timing of the game updates and rendering,
 * delegating game-specific logic to the provided {@link GameState} instance.
 * </p>
 */
public class GameEngine {

    /**
     * The target period between frames in milliseconds.
     * For example, a period of 25 ms corresponds to roughly 40 frames per second.
     */
    private long period = 25;

    /**
     * The current game state, containing game logic and rendering.
     */
    private GameState gameState;

    /**
     * Initializes the engine with a given game state.
     * 
     * @param gameState the game state instance to manage and update
     */
    public void setup(final GameState gameState) {
        this.gameState = gameState;
        this.gameState.setUp();
    }

    /**
     * Runs the main game loop.
     * 
     * <p>
     * This method repeatedly:
     * </p>
     * <ul>
     * <li>Processes input</li>
     * <li>Updates the game state based on elapsed time</li>
     * <li>Renders the game</li>
     * <li>Waits to maintain a consistent frame rate</li>
     * </ul>
     * 
     * <p>
     * This loop runs indefinitely until the application is terminated.
     * </p>
     */

    public void mainLoop() {
        long lastTime = System.currentTimeMillis();
        while (true) {
            long current = System.currentTimeMillis();
            int elapsed = (int) (current - lastTime);
            gameState.processInput();
            gameState.updateGame(elapsed);
            gameState.render();
            waitForNextFrame(current);
            lastTime = current;
        }
    }

    /**
     * Pauses the current thread to maintain the target frame period.
     * 
     * <p>
     * This method calculates how much time has passed since the
     * given timestamp and sleeps the thread for the remaining time
     * needed to complete the target period.
     * </p>
     * 
     * @param current the timestamp (in milliseconds) when the frame processing
     *                started
     */

    protected void waitForNextFrame(final long current) {
        long dt = System.currentTimeMillis() - current;
        if (dt < period) {
            try {
                Thread.sleep(period - dt);
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }

}
