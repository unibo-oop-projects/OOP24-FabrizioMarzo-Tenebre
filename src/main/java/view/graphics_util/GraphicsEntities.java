package view.graphics_util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

/**
 * A utility class for loading and processing sprite animations for game entities.
 * <p>
 * This class is responsible for loading animations for survivors, zombies, and 
 * other level objects. It divides sprite sheets into frames 
 * and stores them as {@link BufferedImage} objects. It also handles loading 
 * shadows for level objects.
 * 
 */
public class GraphicsEntities {

    private static final String SURVIVOR_PATH = "/sprite_sheet/survivor/";
    private static final String ZOMBIE_PATH = "/sprite_sheet/zombie/";
    private static final String LEVEL_OBJECT_PATH = "/level_object/";
    private final ImportImage impImg = new ImportImagePNG() ;

    /**
     * Loads the animation frames for a survivor character.
     * <p>
     * This method loads the sprite sheet for the specified survivor, extracts frames 
     * based on the provided width and height of each frame, and returns the 
     * frames organized into a list of rows of frames.
     * </p>
     * 
     * @param nameSurvivor the name of the survivor character to load animations for.
     * @param width_frame the width of a single frame in the sprite sheet.
     * @param height_frame the height of a single frame in the sprite sheet.
     * @return a list of rows of animation frames as {@link BufferedImage} objects.
     *         Returns an empty list if the sprite sheet could not be loaded.
     */
    public List<List<BufferedImage>> loadSurvivorAnimations(final String nameSurvivor,final int width_frame , final int height_frame){
        return loadAnimations(SURVIVOR_PATH, nameSurvivor, width_frame, height_frame);
    }

    /**
     * Loads the animation frames for a zombie character.
     * <p>
     * This method loads the sprite sheet for the specified zombie, extracts frames 
     * based on the provided width and height of each frame, and returns the 
     * frames organized into a list of rows of frames.
     * </p>
     * 
     * @param nameZombie the name of the zombie character to load animations for.
     * @param width_frame the width of a single frame in the sprite sheet.
     * @param height_frame the height of a single frame in the sprite sheet.
     * @return a list of rows of animation frames as {@link BufferedImage} objects.
     *         Returns an empty list if the sprite sheet could not be loaded.
     */
    public List<List<BufferedImage>> loadZombieAnimations(final String nameZombie,final int width_frame , final int height_frame){
        return loadAnimations(ZOMBIE_PATH, nameZombie, width_frame, height_frame);
    }

    /**
     * Loads the shadow image for a level object.
     * <p>
     * This method loads a shadow image from the level object sprite sheet based on
     * the specified object name.
     * </p>
     * 
     * @param nameObject the name of the level object (e.g., tree, rock) whose shadow 
     *                   image to load.
     * @return a {@link BufferedImage} representing the shadow image, or {@code null} 
     *         if the image could not be loaded.
     */
    public BufferedImage loadEntitiesShadow(final String nameObject) {
        return impImg.imp(LEVEL_OBJECT_PATH + nameObject);
    }

    private Pair<Integer, Integer> numColRow(final int width_frame, final int height_frame, final BufferedImage img) {
        int columns = img.getWidth() / width_frame;
        int rows = img.getHeight() / height_frame;

        // Verifica che l'immagine sia divisibile esattamente in frame
        if (img.getWidth() % width_frame != 0 || img.getHeight() % height_frame != 0) {
            System.err.println("Image dimensions are not divisible by the frame size.");
            return Pair.of(0, 0); // Restituisce una coppia vuota per evitare problemi successivi
        }

        return Pair.of(columns, rows);
    }

    private List<List<BufferedImage>> loadAnimations(final String pathPrefix, final String name, final int width_frame, final int height_frame) {
        var img = impImg.imp(pathPrefix + name);
        
        if (img == null) {
            System.err.println("Image not found: " + pathPrefix + name);
            return new ArrayList<>(); // Restituisce una lista vuota in caso di errore
        }
        
        var numCR = this.numColRow(width_frame, height_frame, img);
        return this.load(numCR, width_frame, height_frame, img);
    }




    private List<List<BufferedImage>> load(final Pair<Integer,Integer> numColRow,final int width_frame , final int height_frame,final BufferedImage img){
        List<List<BufferedImage>> allAnimations = new ArrayList<>();
        var numCol = numColRow.getLeft();
        var numRow = numColRow.getRight();

        for (int j = 0; j < numRow; j++) {
            List<BufferedImage> aniRow = new ArrayList<>();
    
            for (int i = 0; i < numCol; i++) {
                BufferedImage frame = img.getSubimage(
                    i * width_frame,     
                    j * height_frame,  
                    width_frame,
                    height_frame
                );
                aniRow.add(frame);
            }
    
            allAnimations.add(aniRow); 
        }
        return allAnimations;
    }
}
