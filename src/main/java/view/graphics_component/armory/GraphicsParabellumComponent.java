package view.graphics_component.armory;
import java.awt.image.BufferedImage;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;
import view.graphics_util.SpriteSheetLoader;

public class GraphicsParabellumComponent implements GraphicsMunitionComponent{

    private SpriteSheetLoader graphEnti = new SpriteSheetLoader();
    private BufferedImage munition;

    public GraphicsParabellumComponent(final String nameClass){
        this.munition = graphEnti.loadMunition(nameClass);

    }

    @Override
    public void update(final Munition mun,final GraphicsMunition grsy) {
        grsy.drawMunition(mun, munition);
    }
    
}
