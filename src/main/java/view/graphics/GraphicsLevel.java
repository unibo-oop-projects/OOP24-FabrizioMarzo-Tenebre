package view.graphics;

import java.awt.image.BufferedImage;
import java.util.List;

import model.level.Level;

public interface GraphicsLevel {
    void drawLevel(final Level lvl, final List<BufferedImage> listLevelSprite, final
    List<List<Integer>> listLevelData);
}
