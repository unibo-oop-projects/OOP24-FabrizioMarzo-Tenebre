package view.graphics_util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.image.Raster;


import org.apache.commons.lang3.tuple.Pair;

public class SpriteSheetLoader implements ISpriteLoader{

    private static final String SURVIVOR_PATH = "/sprite_sheet/survivor/";
    private static final String ZOMBIE_PATH = "/sprite_sheet/zombie/";
    private static final String MUNITION_PATH = "/sprite_sheet/armory/";
    private static final String LEVEL_PATH = "/sprite_sheet/level/";

    private static final String LEVEL_OBJECT_PATH = "/level_object/";
    private final ImportImageFactory factory = new ImportImageFactoryImpl();
    private final ImportImage loaderPNG = factory.loaderPNG();

    @Override
    public List<List<BufferedImage>> loadSurvivorAnimations(final String nameSurvivor,final int width_frame , final int height_frame){
        return loadAnimations(SURVIVOR_PATH, nameSurvivor, width_frame, height_frame);
    }

    @Override
    public List<List<BufferedImage>> loadZombieAnimations(final String nameZombie,final int width_frame , final int height_frame){
        return loadAnimations(ZOMBIE_PATH, nameZombie, width_frame, height_frame);
    }

    @Override
    public List<BufferedImage> loadLevelSprite(final String nameLevelSprite,final int width_frame , final int height_frame){
        return loadSprite(LEVEL_PATH, nameLevelSprite, width_frame, height_frame);
    }

    @Override
    public BufferedImage loadEntitiesShadow(final String nameObject) {
        Optional<BufferedImage> subImage = getBBoxImage(loaderPNG.imp(LEVEL_OBJECT_PATH + nameObject).get());
        return subImage.get();
    }

    @Override
    public BufferedImage loadMunition(final String nameMunition) {
        Optional<BufferedImage> subImage = getBBoxImage(loaderPNG.imp(MUNITION_PATH + nameMunition).get());
        return subImage.get();
    }

    private Pair<Integer, Integer> numColRow(final int width_frame, final int height_frame, final BufferedImage img) {

        int columns = img.getWidth() / width_frame;
        int rows = img.getHeight() / height_frame;
    
        return Pair.of(columns, rows);
    }

    private List<List<BufferedImage>> loadAnimations(final String pathPrefix, final String name, final int width_frame, final int height_frame) {
        var img = loaderPNG.imp(pathPrefix + name);
        

        // Check if the sprite image is load corretly 
        if (!img.isPresent()) { 
            System.err.println("Image not found: " + pathPrefix + name);
            return new ArrayList<>(); // Return the list empty of animations
        }
        
        // If the image is load corretly found his col and row 
        var numCR = this.numColRow(width_frame, height_frame, img.get());
        return this.loadSpriteSets(numCR, width_frame, height_frame, img.get());
    }


    private List<BufferedImage> loadSprite(final String pathPrefix, final String name, final int width_frame, final int height_frame){
        var imgcity = loaderPNG.imp(pathPrefix + name);

        if (!imgcity.isPresent()) { 
            System.err.println("Image not found: " + pathPrefix + name);
            return new ArrayList<>(); // Return the list empty of animations
        }
        
        var numCR = this.numColRow(width_frame, height_frame, imgcity.get());
        return this.loadSprites(numCR, width_frame, height_frame, imgcity.get());
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

    @Override
    public List<List<Integer>> getLevelData(final String nameLevelData) {
        var img = loaderPNG.imp(LEVEL_PATH + nameLevelData);
        List<List<Integer>> levelData = new ArrayList<>();
    
        Raster raster = img.get().getRaster();  // ottieni il raster dell'immagine
        int numBands = raster.getNumBands(); // numero di canali (RGB, o RGBA)
    
        for (int j = 0; j < img.get().getHeight(); j++) {
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < img.get().getWidth(); i++) {
                int[] pixel = new int[numBands];
                raster.getPixel(i, j, pixel);  // legge tutti i canali del pixel
    
                int valueRed = pixel[0];  // il canale rosso è sempre al primo indice
                row.add(valueRed);
            }
            levelData.add(row);
        }
    
        return levelData;
    }
    
    
}
