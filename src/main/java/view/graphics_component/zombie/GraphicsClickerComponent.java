package view.graphics_component.zombie;

import java.awt.image.BufferedImage;
import java.util.List;

import model.entities.zombie.Zombie;
import view.graphics.GraphicsZombie;
import view.graphics_util.LoadAnimations;

public class GraphicsClickerComponent implements GraphicsZombieComponent{

    private static final int WIDTH_FRAME = 64;
    private static final int HEIGHT_FRAME = 32;

    private LoadAnimations graphEnti = new LoadAnimations();
    private List<List<BufferedImage>> animations;
    private int aniIndex,aniTick,aniSpeed = 8;

    public GraphicsClickerComponent(final String nameClass){
        this.animations = graphEnti.loadZombieAnimations(nameClass, WIDTH_FRAME, HEIGHT_FRAME);
    }

    private int updateAnimations(final int zobState){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            final int currentStateSize = animations.get(zobState).size();
            if (aniIndex >= currentStateSize){
                aniIndex = 0;
            }
        }
        return aniIndex;
    }

    @Override
    public void update(final Zombie zob, final GraphicsZombie graphZob ) {
        final int zobState = zob.getState().getIndex();
        graphZob.drawZombie(zob, animations.get(zobState).get(updateAnimations(zobState)));
    }
    
}
