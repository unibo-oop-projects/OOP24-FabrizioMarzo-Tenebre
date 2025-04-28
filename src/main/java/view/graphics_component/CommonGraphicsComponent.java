package view.graphics_component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.base.Survivor;

/**
 * Graphical component for rendering a common type of {@link Survivor}.
 * <p>
 * This class loads and manages sprite sheet animations, and handles the logic to draw
 * the correct frame of the animation based on the survivor's current state.
 */
public class CommonGraphicsComponent implements GraphicsSurvivorComponent {

    private static final int WIDTH_IMAGE = 80;
    private static final int HEIGHT_IMAGE = 128;
    private static final int WIDTH_FRAME = 48;
    private static final int HEIGHT_FRAME = 64;



    private BufferedImage img,shadow;
    private Pair<Integer, Integer> grid;
    private int numGridCol, numGridRow;
    private List<List<BufferedImage>> annimations;
    private int aniIndex,aniTick,aniSpeed = 5;

    /**
     * Constructs a {@code CommonGraphicsComponent} using the given class name.
     * <p>
     * It imports the corresponding image, calculates the animation grid,
     * and loads all animation frames for rendering.
     *
     * @param nameClass the simple name of the survivor class used to load the sprite sheet
     */
    public CommonGraphicsComponent(final String nameClass){
        System.err.println("Import the Image, and set all Animations");
        this.img = UtilGraphicsImg.importImg("/sprite_sheet/survivor/" + nameClass);
        this.grid = UtilGraphicsImg.numColRow(WIDTH_FRAME, HEIGHT_FRAME, img);
        this.numGridCol = grid.getLeft();
        this.numGridRow = grid.getRight();
        this.annimations = UtilGraphicsImg.loadAllAnimations(numGridCol, numGridRow, WIDTH_FRAME, HEIGHT_FRAME, img);
        this.shadow = UtilGraphicsImg.importImg("/sprite_sheet/Shadow");
    }

    /**
     * Updates the animation frame index over time based on {@code aniSpeed}.
     * Loops the animation when the last frame is reached.
     */
    private void updateAnimations(){
        aniTick++;
        if(aniTick >= aniSpeed ){
            aniTick=0;
            aniIndex++;
            if (aniIndex >= grid.getLeft()){
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
        updateAnimations();
        g2d.drawImage(shadow, surPosX, surPosY,WIDTH_IMAGE,HEIGHT_IMAGE,null);
        g2d.drawImage(annimations.get(surState).get(aniIndex),surPosX,surPosY,WIDTH_IMAGE,HEIGHT_IMAGE,null);
        System.out.println("I am painting !!");
    }
    
}
