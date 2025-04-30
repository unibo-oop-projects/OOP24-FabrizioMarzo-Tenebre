package view.graphics_util;

import java.awt.image.BufferedImage;

/**
 * Interface for importing images from a given file path.
 * <p>
 * This interface defines a method for loading an image from a specified file path.
 * Implementations of this interface are responsible for reading image files
 * and returning them as a {@link BufferedImage}.
 * 
 */
public interface ImportImage {

    /**
     * Imports an image from the specified file path.
     * <p>
     * This method reads the image file located at the provided path and returns it as a
     * {@link BufferedImage}. 
     * 
     * @param imgPath the file path of the image to import. It can be an absolute or relative path.
     * @return a {@link BufferedImage} object representing the image.
     */
    public BufferedImage imp(final String imgPath);
}
