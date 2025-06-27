package view.graphics_util;

import java.io.InputStream;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImportImageFactoryImpl implements ImportImageFactory{

    private ImportImage createLoader(final String extension) {
        return new ImportImage() {
            private final ImageCache cache = new ImageCache();

            @Override
            public BufferedImage imp(final String path) {
                if (cache.isImageCached(path)) {
                    return cache.getImage(path);
                }
                try (InputStream is = getClass().getResourceAsStream(path + extension)) {
                    if (is == null) {
                        System.err.println("Image not found: " + path + extension);
                        return null;
                    }
                    BufferedImage img = ImageIO.read(is);
                    cache.putImage(path, img);
                    return img;
                } catch (Exception e) {
                    System.err.println("Error loading image: " + path + extension);
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    @Override
    public ImportImage loaderPNG() {
        return this.createLoader(".png");
    }

    @Override
    public ImportImage loaderJPNG() {
       return this.createLoader(".jpng");
    }

    @Override
    public ImportImage loaderJPEG() {
       return this.createLoader(".jpeg");
    }
    
}
