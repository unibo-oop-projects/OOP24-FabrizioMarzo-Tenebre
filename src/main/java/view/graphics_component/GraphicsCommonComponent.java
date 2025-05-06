package view.graphics_component;

import java.util.List;

import model.entities.survivor.Survivor;

import java.awt.image.BufferedImage;

import view.graphics_util.GraphicsEntities;

public class GraphicsCommonComponent implements GraphicsSurvivorComponent{

    private static final int WIDTH_FRAME = 48;
    private static final int HEIGHT_FRAME = 64;

    private GraphicsEntities graphEnti = new GraphicsEntities();
    //private BufferedImage shadow;
    private List<List<BufferedImage>> annimations;
    private int aniIndex,aniTick,aniSpeed = 5;

    
    public GraphicsCommonComponent(final String nameClass) {
        System.err.println("Import the Image, and set all Animations");
        this.annimations = graphEnti.loadSurvivorAnimations(nameClass, WIDTH_FRAME, HEIGHT_FRAME);
        //this.shadow = graphEnti.loadEntitiesShadow("Shadow");
    }

    private void updateAnimations(final int surState){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            int currentStateSize = annimations.get(surState).size();
            if (aniIndex >= currentStateSize){
                aniIndex = 0;
            }
        }
    }

    @Override
    public void update(Survivor sur, GraphicsSurvivor grySur) {
        int surState = sur.getState().getIndex();
        updateAnimations(surState);
        grySur.drawSurvivor(sur, annimations.get(surState).get(aniIndex));
    }
    
}
