package game.game_engine;

import game.game_state.GameState;

public class GameEngine{
    private long period = 25;
    private GameState gameState;

    public void setup(final GameState gameState){
        this.gameState = gameState;
        this.gameState.setUp();
    }

    public void mainLoop(){
        long lastTime = System.currentTimeMillis();
        while(true){
                long current = System.currentTimeMillis();
                int elapsed = (int)(current - lastTime);
                gameState.processInput();
                gameState.updateGame(elapsed);
                gameState.render();
                waitForNextFrame(current);
                lastTime = current;
        }
    }

    protected void waitForNextFrame(final long current){
        long dt = System.currentTimeMillis() - current;
        if (dt < period){
                try {
                    Thread.sleep(period-dt);
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
        }
    }

}
