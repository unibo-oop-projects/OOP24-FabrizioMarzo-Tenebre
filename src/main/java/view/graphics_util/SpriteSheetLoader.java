package view.graphics_util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class SpriteSheetLoader implements ISpriteLoader{

    private static final String SURVIVOR_PATH = "/sprite_sheet/survivor/";
    private static final String ZOMBIE_PATH = "/sprite_sheet/zombie/";
    private static final String MUNITION_PATH = "/sprite_sheet/armory/";
    private static final String CITY_PATH = "/sprite_sheet/level/";


    private static final String LEVEL_OBJECT_PATH = "/level_object/";
    private final ImportImage impImgPNG = new ImportImagePNG() ;

    public List<List<BufferedImage>> loadSurvivorAnimations(final String nameSurvivor,final int width_frame , final int height_frame){
        return loadAnimations(SURVIVOR_PATH, nameSurvivor, width_frame, height_frame);
    }

    public List<List<BufferedImage>> loadZombieAnimations(final String nameZombie,final int width_frame , final int height_frame){
        return loadAnimations(ZOMBIE_PATH, nameZombie, width_frame, height_frame);
    }

    public List<BufferedImage> loadLevelSprite(final String cityname,final int width_frame , final int height_frame){
        return loadSprite(CITY_PATH, cityname, width_frame, height_frame);
    }

    public BufferedImage loadEntitiesShadow(final String nameObject) {
        Optional<BufferedImage> subImage = getBBoxImage(impImgPNG.imp(LEVEL_OBJECT_PATH + nameObject));
        return subImage.get();
    }

    public BufferedImage loadMunition(final String nameMunition) {
        Optional<BufferedImage> subImage = getBBoxImage(impImgPNG.imp(MUNITION_PATH + nameMunition));
        return subImage.get();
    }

    private Pair<Integer, Integer> numColRow(final int width_frame, final int height_frame, final BufferedImage img) {

        int columns = img.getWidth() / width_frame;
        int rows = img.getHeight() / height_frame;

        // // Check if the image is visible secondly the frame 
        // if (img.getWidth() % width_frame != 0 || img.getHeight() % height_frame != 0) {
        //     System.err.println("Image dimensions are not divisible by the frame size.");
        //     return Pair.of(0, 0); // Return empty Pair 
        // }
    
        System.out.println("\n\n" + columns + " " + rows );
        return Pair.of(columns, rows);
    }

    private List<List<BufferedImage>> loadAnimations(final String pathPrefix, final String name, final int width_frame, final int height_frame) {
        var img = impImgPNG.imp(pathPrefix + name);
        
        // Check if the sprite image is load corretly 
        if (img == null) { 
            System.err.println("Image not found: " + pathPrefix + name);
            return new ArrayList<>(); // Return the list empty of animations
        }
        
        // If the image is load corretly found his col and row 
        var numCR = this.numColRow(width_frame, height_frame, img);
        return this.loadSpriteSets(numCR, width_frame, height_frame, img);
    }


    private List<BufferedImage> loadSprite(final String pathPrefix, final String name, final int width_frame, final int height_frame){
        var imgcity = impImgPNG.imp(pathPrefix + name);

        if (imgcity == null) { 
            System.err.println("Image not found: " + pathPrefix + name);
            return new ArrayList<>(); // Return the list empty of animations
        }
        
        var numCR = this.numColRow(width_frame, height_frame, imgcity);
        return this.loadSprites(numCR, width_frame, height_frame, imgcity);
    }


    private List<BufferedImage> loadSprites(final Pair<Integer,Integer> numColRow,final int width_frame , final int height_frame,final BufferedImage img ){

        List<BufferedImage> allSprites = new ArrayList<>();
        var numCol = numColRow.getLeft();
        var numRow = numColRow.getRight();

        for (int j = 0; j < numRow; j++){
            for (int i = 0; i < numCol; i++){
                BufferedImage frame = img.getSubimage(
                    i * width_frame,     
                    j * height_frame,  
                    width_frame,
                    height_frame
                );

                Optional<BufferedImage> subImage = getBBoxImage(frame);
                if (subImage.isEmpty())
                    break;

                allSprites.add(subImage.get());
            }
        }

        return allSprites;
    }

    private List<List<BufferedImage>> loadSpriteSets(final Pair<Integer,Integer> numColRow,final int width_frame , final int height_frame,final BufferedImage img){
        
        List<List<BufferedImage>> allAnimations = new ArrayList<>();
        var numCol = numColRow.getLeft();
        var numRow = numColRow.getRight();

        // Begin of the first row 
        for (int j = 0; j < numRow; j++) {
            List<BufferedImage> aniRow = new ArrayList<>(); // Create the first list of image 
    
            for (int i = 0; i < numCol; i++) {
                BufferedImage frame = img.getSubimage(
                    i * width_frame,     
                    j * height_frame,  
                    width_frame,
                    height_frame
                );

                Optional<BufferedImage> subImage = getBBoxImage(frame);
                if (subImage.isEmpty())
                    break;

                aniRow.add(subImage.get());
            }
            allAnimations.add(aniRow); 
        }
        return allAnimations;
    }


    private Optional<BufferedImage> getBBoxImage(final BufferedImage img){

        int width = img.getWidth();
        int height = img.getHeight();
        int top = height, bottom = 0, left = width, right = 0;

        for (int y = 0; y < height ; y++ ){
            for ( int x = 0 ; x < width ; x++){
                int rgb = img.getRGB(x, y);

                if (rgb != 0) { 
                    if (x < left) left = x;
                    if (x > right) right = x;
                    if (y < top) top = y;
                    if (y > bottom) bottom = y;
                }
            }
        }

        if (top == height && bottom == 0 && left == width && right == 0 )
            return Optional.empty();

        BufferedImage frame = img.getSubimage(left, top, right - left + 1, bottom - top + 1);
        return Optional.of(frame);
    }

}
