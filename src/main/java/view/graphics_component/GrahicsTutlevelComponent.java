package view.graphics_component;

import model.level.Level;
import view.graphics_level.GraphicsLevel;

public class GrahicsTutlevelComponent implements GraphicsLevelComponent {

    @Override
    public void update(final Level lvl, final GraphicsLevel gryLvl) {
        gryLvl.drawLevel(lvl);
    }

    
}
