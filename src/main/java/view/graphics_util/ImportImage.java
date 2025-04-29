package view.graphics_util;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImportImage {

    private static final String IMAGE_EXTENSION = ".png"; 
    private final ImageCache imgCache = new ImageCache();
    
    public BufferedImage imp(final String imgPath){
        if(imgCache.isImageCached(imgPath)){
            return imgCache.getImage(imgPath);
        } else {
            try (InputStream is = getClass().getResourceAsStream(imgPath + IMAGE_EXTENSION)) {
                if (is == null) {
                    System.err.println("Image not found: " + imgPath + IMAGE_EXTENSION);
                    return null;
                }
                BufferedImage img = ImageIO.read(is);
                imgCache.putImage(imgPath, img);
                return img;
            } catch (Exception e) {
                System.err.println("Error on loading image: " + imgPath + IMAGE_EXTENSION);
                e.printStackTrace();
                return null;
            }
        }

    }
    
}
