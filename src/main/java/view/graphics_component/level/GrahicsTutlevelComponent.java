package view.graphics_component.level;

import java.awt.image.BufferedImage;


import model.level.Level;
import view.graphics.GraphicsLevel;
import view.graphics_util.ImportImage;
import view.graphics_util.ImportImagePNG;

public class GrahicsTutlevelComponent implements GraphicsLevelComponent {

    private static final int WIDTH_FRAME = 32;
    private static final int HEIGHT_FRAME = 33;
    private static final String CITY_PATH = "/sprite_sheet/level/city";

    private BufferedImage img;
    private ImportImage importImg = new ImportImagePNG();

    public GrahicsTutlevelComponent(){
        this.img = importImg.imp(CITY_PATH);
        //System.out.println(img);
    }


    @Override
    public void update(final Level lvl, final GraphicsLevel gryLvl) {
        gryLvl.drawLevel(lvl,this.img);
    }

    
}
