package game.game_state;

public interface GameState {
    void setUp();
    void processInput();
    void updateGame(final int elapsed);
    void render();
}
