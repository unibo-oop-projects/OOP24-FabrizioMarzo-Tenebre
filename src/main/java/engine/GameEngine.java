package engine;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import input.Command;
import input.Controller;
import model.level.Level;
import model.level.TutorialLevel;
import view.scene.SceneTutorial;

public class GameEngine  implements Controller{
    private long period = 25;
    private SceneTutorial view ;
    private Level tutLevel; 
    private BlockingQueue<Command> cmdQueue;

    public void setup(){
        cmdQueue = new ArrayBlockingQueue<>(100);
        tutLevel = new TutorialLevel();
        System.out.println("Creo la scena e dico che io sono il suo Controller");
        view = new SceneTutorial(tutLevel,1200, 800,this);
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
        System.out.println("Controllo un comando..");
        Command cmd = cmdQueue.poll();
        if (cmd != null){
            cmd.execute(tutLevel);
        }
    }

    protected void updateGame(final int elapsed){
        tutLevel.updateState(elapsed);
    }

    protected void render(){
        view.render();
    }

    @Override
    public void notifyCommand(Command cmd) {
        cmdQueue.add(cmd);    
    }

}
