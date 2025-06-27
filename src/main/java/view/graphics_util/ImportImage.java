package view.graphics_util;

import java.awt.image.BufferedImage;
import java.util.Optional;

public interface ImportImage {

    public Optional<BufferedImage> imp(final String imgPath);
}
