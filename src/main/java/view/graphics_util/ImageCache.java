package view.graphics_util;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple image cache to store and retrieve {@link BufferedImage} objects.
 * <p>
 * This class provides a basic caching mechanism for images. It stores images in a 
 * {@link Map} with the image path as the key. This allows images to be loaded once 
 * and reused, improving performance by avoiding multiple loading operations for 
 * the same image.
 * </p>
 * 
 * <p>
 * The cache is stored as a static map to ensure that it is shared across all 
 * instances of the class.
 * </p>
 */
public class ImageCache {
    private static final Map<String, BufferedImage> imageCache = new HashMap<>();

    /**
     * Checks if the image is already cached.
     * <p>
     * This method checks if an image with the specified path has been previously
     * loaded and stored in the cache.
     * </p>
     *
     * @param imagePath the path of the image to check.
     * @return {@code true} if the image is cached, {@code false} otherwise.
     */
    public boolean isImageCached(final String imagePath) {
        return imageCache.containsKey(imagePath);
    }

    /**
     * Retrieves a cached image.
     * <p>
     * If the image is cached, this method returns the cached {@link BufferedImage}.
     * Otherwise, it returns {@code null}.
     * </p>
     *
     * @param imagePath the path of the image to retrieve.
     * @return the cached {@link BufferedImage}, or {@code null} if not found.
     */
    public BufferedImage getImage(final String imagePath) {
        return imageCache.get(imagePath);
    }

    /**
     * Puts an image in the cache.
     * <p>
     * This method stores the image in the cache with the specified image path 
     * as the key. If an image already exists for the given path, it will be 
     * replaced by the new one.
     * </p>
     *
     * @param imagePath the path of the image to cache.
     * @param image the {@link BufferedImage} to store in the cache.
     */
    public void putImage(final String imagePath, final BufferedImage image) {
        imageCache.put(imagePath, image);
    }
}
