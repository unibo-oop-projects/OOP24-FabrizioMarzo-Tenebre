package view.graphics_component;

import java.awt.Graphics2D;

import model.entities.survivor.base.Survivor;

public interface GraphicsSurvivorComponent {
    void drawSurvivor(final Survivor sur,final Graphics2D g2d);
}
