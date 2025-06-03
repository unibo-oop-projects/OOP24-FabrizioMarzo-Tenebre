package view.graphics;

import java.awt.image.BufferedImage;

import model.armory.munition.Munition;

public interface GraphicsMunition {
        void drawMunition(final Munition mun , final BufferedImage image);
}
