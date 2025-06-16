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
    private List<BufferedImage> listLevelSprite;
    private List<List<Integer>> listLevelData;
    
    public GrahicsTutlevelComponent(){
        this.listLevelSprite = this.loader.loadLevelSprite("levelSprite", WIDTH_FRAME, HEIGHT_FRAME);
        this.listLevelData = this.loader.getLevelData("levelData");
        for (int i = 0 ; i <14 ; i++ ){

            System.out.println(listLevelData.get(i));
        }
    }

    @Override
    public void update(final Level lvl, final GraphicsLevel gryLvl) {
        gryLvl.drawLevel(lvl, this.listLevelSprite, this.listLevelData);
    }

    
}
