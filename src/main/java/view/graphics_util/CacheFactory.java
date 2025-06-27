package view.graphics_util;

import java.awt.image.BufferedImage;

public interface CacheFactory {

    Cache<String,BufferedImage> stringImageCache();
    
}
