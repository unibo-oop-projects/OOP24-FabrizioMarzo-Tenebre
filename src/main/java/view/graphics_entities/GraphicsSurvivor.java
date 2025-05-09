package view.graphics_entities;

import java.awt.image.BufferedImage;

import model.entities.survivor.Survivor;

public interface GraphicsSurvivor {
    void drawSurvivor(final Survivor sur, final BufferedImage image);
}
