package view.graphics_survivor;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class UtilGraphics {
    private static final Map<String, BufferedImage> cache = new HashMap<>();

    public static BufferedImage importImg(final String nameClass) {

        if (cache.containsKey(nameClass)) {
            return cache.get(nameClass);
        }

        try (InputStream is = UtilGraphics.class.getResourceAsStream("/" + nameClass + ".png")) {
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

}
