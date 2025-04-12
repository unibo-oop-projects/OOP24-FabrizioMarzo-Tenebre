package engine;

import view.scene.SceneProva;

public class GameEngine {
    private long period = 20;
    private SceneProva view ;

    public void setup(){
        view = new SceneProva(1200, 800);
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
        System.out.println("Hello");
    }

    protected void updateGame(final int elapsed){
        System.out.println("Time Passed "+elapsed);
    }

    protected void render(){
        view.render();
    }

}
