package view.graphics_component;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Utility class for loading and handling sprite images for graphical components.
 * <p>
 * This class provides methods for importing images from resources, calculating sprite sheet
 * dimensions, and extracting animation frames. It includes an internal cache to avoid
 * reloading images unnecessarily, improving performance and memory efficiency.
 */
public class UtilGraphicsImg {
    private static final Map<String, BufferedImage> cache = new HashMap<>();

    /**
     * Imports a PNG image from the resources folder using the provided class name.
     * <p>
     * If the image was already loaded before, it is retrieved from the internal cache.
     *
     * @param nameClass the simple name of the class of survivor for get his PNG image.
     * @return the loaded {@link BufferedImage}
     */
    public static BufferedImage importImg(final String nameClass) {

        if (cache.containsKey(nameClass)) {
            System.out.println("Take on chace, don't lost memory");
            return cache.get(nameClass);
        }

        try (InputStream is = UtilGraphicsImg.class.getResourceAsStream(nameClass + ".png")) {
            if (is == null) {
                System.err.println("Imagine don't find: " + nameClass + ".png");
                return null;
            }

            BufferedImage img = ImageIO.read(is);
            cache.put(nameClass, img);
            return img;

        } catch (Exception e) {
            System.err.println("Error on loading: " + nameClass);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Calculates the number of columns and rows in a sprite sheet image based on frame size.
     *
     * @param width_frame  the width of a single frame
     * @param height_frame the height of a single frame
     * @param img          the sprite sheet image
     * @return a {@link Pair} representing the number of columns (left) and rows (right)
     */
    public static Pair<Integer,Integer> numColRow(final int width_frame, final int height_frame, final BufferedImage img){
        var columns = img.getWidth()/width_frame;
        var rows = img.getHeight()/ height_frame;
        return Pair.of(columns, rows);
    }

    /**
     * Extracts all animation frames from a sprite sheet organized in a grid layout.
     * <p>
     * Each row represents a different animation (e.g., idle, walking, attacking),
     * and each column in that row is a frame of that animation.
     *
     * @param columns       number of columns in the sprite sheet
     * @param rows          number of rows in the sprite sheet
     * @param width_frame   width of each frame
     * @param height_frame  height of each frame
     * @param img           the sprite sheet image
     * @return a nested {@link List} of {@link BufferedImage} objects,
     *         where each sublist represents an animation sequence
     */
    public static List<List<BufferedImage>> loadAllAnimations(final int columns, final int rows,final int width_frame , final int height_frame, final BufferedImage img) {
        List<List<BufferedImage>> allAnimations = new ArrayList<>();
    
        for (int j = 0; j < rows; j++) {
            List<BufferedImage> aniRow = new ArrayList<>();
    
            for (int i = 0; i < columns; i++) {
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

