import game.game_engine.GameEngine;
import game.game_state.TutorialState;

/**
 * Main class that launches the game.
 * 
 * <p>{@code ArmataTenebre} is the entry point of the game. It creates and
 * initializes a new {@link GameEngine} instance, sets it up, and starts the
 * main game loop.</p>
 */
public class ArmataTenebre {
    
    /**
     * Main method that starts the game engine.
     * 
     * <p>This method creates an instance of {@link GameEngine}, sets it up,
     * and then starts the main game loop. It is called when the program is executed.</p>
     *
     * @param args command-line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.setup(new TutorialState());
        engine.mainLoop();
    }
}
