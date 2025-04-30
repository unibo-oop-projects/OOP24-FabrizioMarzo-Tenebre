package view.graphics_util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class GraphicsEntities {

    private static final String SURVIVOR_PATH = "/sprite_sheet/survivor/";
    private static final String ZOMBIE_PATH = "/sprite_sheet/zombie/";
    private static final String LEVEL_OBJECT_PATH = "/level_object/";
    private final ImportImage impImg = new ImportImagePNG() ;

    public List<List<BufferedImage>> loadSurvivorAnimations(final String nameSurvivor,final int width_frame , final int height_frame){
        return loadAnimations(SURVIVOR_PATH, nameSurvivor, width_frame, height_frame);
    }

    public List<List<BufferedImage>> loadZombieAnimations(final String nameZombie,final int width_frame , final int height_frame){
        return loadAnimations(ZOMBIE_PATH, nameZombie, width_frame, height_frame);
    }

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
