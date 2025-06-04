package view.graphics_component.armory;
import java.awt.image.BufferedImage;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;
import view.graphics_util.LoadAnimations;

public class GraphicsParabellumComponent implements GraphicsMunitionComponent{

    private LoadAnimations graphEnti = new LoadAnimations();
    private BufferedImage munition;

    public GraphicsParabellumComponent(final String nameClass){
        this.munition = graphEnti.loadMunition(nameClass);

    }

    @Override
    public void update(final Munition mun,final GraphicsMunition grsy) {
        grsy.drawMunition(mun, munition);
    }
    
}
