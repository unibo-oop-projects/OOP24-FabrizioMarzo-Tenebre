package view.graphics_component.level;

import model.level.Level;
import view.graphics.GraphicsLevel;

public class GrahicsTutlevelComponent implements GraphicsLevelComponent {

    @Override
    public void update(final Level lvl, final GraphicsLevel gryLvl) {
        gryLvl.drawLevel(lvl);
    }

    
}
