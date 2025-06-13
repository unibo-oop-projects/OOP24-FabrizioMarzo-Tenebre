package view.graphics_component.level;

import java.awt.image.BufferedImage;
import java.util.List;

import model.level.Level;
import view.graphics.GraphicsLevel;
import view.graphics_util.ISpriteLoader;
import view.graphics_util.SpriteSheetLoader;

public class GrahicsTutlevelComponent implements GraphicsLevelComponent {

    private static final int WIDTH_FRAME = 32;
    private static final int HEIGHT_FRAME = 33;


    private ISpriteLoader loader = new SpriteSheetLoader();
    private List<BufferedImage> listImg;
    
    public GrahicsTutlevelComponent(){
        this.listImg = this.loader.loadCitySprite("city", WIDTH_FRAME, HEIGHT_FRAME);
    }

    @Override
    public void update(final Level lvl, final GraphicsLevel gryLvl) {
        gryLvl.drawLevel(lvl,listImg.get(0));
    }

    
}
