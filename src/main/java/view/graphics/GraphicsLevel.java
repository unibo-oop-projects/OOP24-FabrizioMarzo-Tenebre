package view.graphics;

import java.awt.image.BufferedImage;
import java.util.List;

import model.level.types.Level;

public interface GraphicsLevel {
    void drawLevel(final Level lvl, final List<List<BufferedImage>> allImage);
}
