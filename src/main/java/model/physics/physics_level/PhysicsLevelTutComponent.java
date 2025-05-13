package model.physics.physics_level;

import org.apache.commons.lang3.tuple.Pair;

import model.level.Level;

public class PhysicsLevelTutComponent implements PhysicsLevelComponent {

    @Override
    public void updateLevel(Level lv, int dt) {
        Pair<Double,Double> checkUL = Pair.of(lv.getSurvivorOnLevel().getBBox().getULcorner().getLeft() -   lv.getSurvivorOnLevel().getWidth()
                                             ,lv.getSurvivorOnLevel().getBBox().getULcorner().getRight() - lv.getSurvivorOnLevel().getHeight());

         Pair<Double,Double> checkBR = Pair.of(lv.getSurvivorOnLevel().getBBox().getBRcorner().getLeft() -   lv.getSurvivorOnLevel().getWidth()
                                             ,lv.getSurvivorOnLevel().getBBox().getBRcorner().getRight() - lv.getSurvivorOnLevel().getHeight());        

        if(!lv.getLevelBBox().isColliding(checkUL,checkBR)){
            System.out.println("Final level");

            double posX = lv.getSurvivorOnLevel().getCurrentPos().getLeft();
            double posY = lv.getSurvivorOnLevel().getCurrentPos().getRight();

            double levelWidth = lv.getLevelWidth();
            double levelHeight = lv.getLevelHeight();

            int survivorWidth = lv.getSurvivorOnLevel().getWidth();
            int survivorHeight = lv.getSurvivorOnLevel().getHeight();

            posX = Math.max(0, Math.min(posX, levelWidth - survivorWidth));
            posY = Math.max(0, Math.min(posY, levelHeight - survivorHeight));
        
            lv.getSurvivorOnLevel().setPosition(Pair.of(posX, posY));
            lv.getSurvivorOnLevel().updateBBox(Pair.of(posX, posY));

        }
    }
    
}
