package view.graphics_component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import model.entities.entities_base.survivor_base.Survivor;
import view.graphics_util.GraphicsEntities;

/**
 * Graphical component for rendering a common type of {@link Survivor}.
 * <p>
 * This class loads and manages sprite sheet animations, and handles the logic to draw
 * the correct frame of the animation based on the survivor's current state.
 */
public class GraphicsCommonComponent implements GraphicsSurvivorComponent {

    private static final int WIDTH_IMAGE = 80;
    private static final int HEIGHT_IMAGE = 128;
    private static final int WIDTH_FRAME = 48;
    private static final int HEIGHT_FRAME = 64;

    private GraphicsEntities graphEnti = new GraphicsEntities();
    private BufferedImage shadow;
    private List<List<BufferedImage>> annimations;
    private int aniIndex,aniTick,aniSpeed = 5;

    /**
     * Constructs a {@code GraphicsCommonComponent} using the given class name.
     * <p>
     * It loads the corresponding annimation frames for rendering, and load the shadow
     * and loads all animation f
     *
     * @param nameClass the simple name of the survivor class used to load the sprite sheet
     */
    public GraphicsCommonComponent(final String nameClass){
        System.err.println("Import the Image, and set all Animations");
        this.annimations = graphEnti.loadSurvivorAnimations(nameClass, WIDTH_FRAME, HEIGHT_FRAME);
        this.shadow = graphEnti.loadEntitiesShadow("Shadow");
    }

    /**
     * Updates the animation frame index over time based on {@code aniSpeed}.
     * Loops the animation when the last frame is reached.
     * <p>
     * 
     * @param surState the state of survivor for update the specific animation
     */
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
    

    /**
     * Draws the current frame of the survivor's animation at the survivor's position.
     * The animation frame depends on the survivor's state.
     *
     * @param sur the {@link Survivor} for get the Survivor position, and get survivor's state
     * @param g2d the {@link Graphics2D} context used to draw the survivor
     */
    @Override
    public void drawSurvivor(final Survivor sur,final Graphics2D g2d) {

        int surPosX = sur.getCurrentPos().getLeft().intValue();
        int surPosY = sur.getCurrentPos().getRight().intValue();
        int surState = sur.getState().getIndex();
        updateAnimations(surState);
        g2d.drawImage(shadow, surPosX, surPosY,WIDTH_IMAGE,HEIGHT_IMAGE,null);
        g2d.drawImage(annimations.get(surState).get(aniIndex),surPosX,surPosY,WIDTH_IMAGE,HEIGHT_IMAGE,null);
        System.out.println("I am painting new mode!!");
    }
    
}
