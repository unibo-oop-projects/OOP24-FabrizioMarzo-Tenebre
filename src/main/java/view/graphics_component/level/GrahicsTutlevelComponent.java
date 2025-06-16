package view.graphics_component.level;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import model.level.Level;
import view.graphics.GraphicsLevel;
import view.graphics_util.ISpriteLoader;
import view.graphics_util.SpriteSheetLoader;

public class GrahicsTutlevelComponent implements GraphicsLevelComponent {

    private static final int WIDTH_FRAME = 32;
    private static final int HEIGHT_FRAME = 33;


    private ISpriteLoader loader = new SpriteSheetLoader();
    private List<List<BufferedImage>> allImageLevel;
    
    public GrahicsTutlevelComponent(){
        allImageLevel = this.mapLevelDataToImages(this.loader.loadLevelSprite("levelSprite", WIDTH_FRAME, HEIGHT_FRAME)
                                          ,this.loader.getLevelData("levelData"));
    }

    private List<List<BufferedImage>> mapLevelDataToImages(List<BufferedImage> levelSprites,List<List<Integer>> levelData) {
        List<List<BufferedImage>> result = new ArrayList<>();

        for (List<Integer> row : levelData) {
            List<BufferedImage> imageRow = new ArrayList<>();
            for (Integer tileIndex : row) {
                if (tileIndex >= 0 && tileIndex < levelSprites.size()) {
                    imageRow.add(levelSprites.get(tileIndex));
                } else {
                    imageRow.add(null); 
                }
            }
            result.add(imageRow);
        }
        return result;
    }

    @Override
    public void update(final Level lvl, final GraphicsLevel gryLvl) {
        gryLvl.drawLevel(lvl, allImageLevel);
    }

    
}
