package view.graphics_util;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {
    private final Map<String, BufferedImage> imageCache = new HashMap<>();

    public boolean isImageCached(final String imagePath) {
        return imageCache.containsKey(imagePath);
    }

    public BufferedImage getImage(final String imagePath) {
        return imageCache.get(imagePath);
    }

    public void putImage(final String imagePath, final BufferedImage image) {
        imageCache.put(imagePath, image);
    }
}
