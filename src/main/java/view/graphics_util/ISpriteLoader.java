package view.graphics_util;
import java.awt.image.BufferedImage;


import java.util.List;

public interface ISpriteLoader {
    
    List<List<BufferedImage>> loadSurvivorAnimations(final String nameSurvivor,final int width_frame , final int height_frame);

    List<List<BufferedImage>> loadZombieAnimations(final String nameZombie,final int width_frame , final int height_frame);

    BufferedImage loadEntitiesShadow(final String nameObject);

    BufferedImage loadMunition(final String nameMunition);

    List<BufferedImage> loadLevelSprite(final String nameLevelSprite,final int width_frame , final int height_frame);

    List<List<Integer>> getLevelData(final String nameLevelData);
}
