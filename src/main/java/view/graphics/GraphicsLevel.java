package view.graphics;

import java.awt.image.BufferedImage;

import model.level.Level;

public interface GraphicsLevel {
    void drawLevel(final Level lvl, final BufferedImage img);
}
