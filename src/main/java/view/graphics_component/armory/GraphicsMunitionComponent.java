package view.graphics_component.armory;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;

public interface GraphicsMunitionComponent {
    void update(final Munition mun,final GraphicsMunition grsy);
}
