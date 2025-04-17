package view.graphics_component;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.tuple.Pair;

public class UtilGraphicsImg {
    private static final Map<String, BufferedImage> cache = new HashMap<>();

    public static BufferedImage importImg(final String nameClass) {

        if (cache.containsKey(nameClass)) {
            System.out.println("Take on chace, don't lost memory");
            return cache.get(nameClass);
        }

        try (InputStream is = UtilGraphicsImg.class.getResourceAsStream("/" + nameClass + ".png")) {
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

    public static Pair<Integer,Integer> numColRow(final int width_frame, final int height_frame, final BufferedImage img){
        var columns = img.getWidth()/width_frame;
        var rows = img.getHeight()/ height_frame;
        return Pair.of(columns, rows);
    }

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

