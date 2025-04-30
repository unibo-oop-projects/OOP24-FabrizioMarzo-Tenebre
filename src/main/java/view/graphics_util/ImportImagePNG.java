package view.graphics_util;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Implementation of the {@link ImportImage} interface for importing PNG images.
 * <p>
 * This class loads PNG images from a given path, using caching to improve performance. 
 * If the image has already been loaded and cached, it will return the cached version 
 * instead of reloading it. The images are loaded from the classpath using the 
 * {@link ImageIO} utility.
 *
 */
public class ImportImagePNG implements ImportImage{

    private static final String IMAGE_EXTENSION = ".png"; 
    private final ImageCache imgCache = new ImageCache();
    
    /**
     * Imports a PNG image from the specified path.
     * <p>
     * This method checks if the image is already cached. If so, it returns the cached 
     * image. Otherwise, it attempts to load the image from the resources folder by 
     * appending the ".png" extension to the provided path. If the image is found, it is 
     * read using {@link ImageIO}, cached, and returned. In case of any errors (e.g., image 
     * not found or reading failure), it returns {@code null}.
     * </p>
     *
     * @param imgPath the path of the image, without the ".png" extension.
     * @return a {@link BufferedImage} representing the loaded PNG image, or {@code null} if 
     *         the image could not be loaded.
     */
    @Override
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
