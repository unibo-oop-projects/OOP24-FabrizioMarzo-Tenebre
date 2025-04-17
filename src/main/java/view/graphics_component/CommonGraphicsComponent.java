package view.graphics_component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.survivor.base.Survivor;


public class CommonGraphicsComponent implements GraphicsSurvivorComponent {

    private static final int WIDTH_IMAGE = 80*5;
    private static final int HEIGHT_IMAGE = 128*5;
    private static final int WIDTH_FRAME = 48;
    private static final int HEIGHT_FRAME = 64;



    private BufferedImage img;
    private Pair<Integer, Integer> grid;
    private int numGridCol, numGridRow;
    private List<List<BufferedImage>> annimations;
    private int aniIndex,aniTick,aniSpeed = 15;


    public CommonGraphicsComponent(final String nameClass){
        System.err.println("Import the Image, and set all Animations");
        this.img = UtilGraphicsImg.importImg(nameClass);
        this.grid = UtilGraphicsImg.numColRow(WIDTH_FRAME, HEIGHT_FRAME, img);
        this.numGridCol = grid.getLeft();
        this.numGridRow = grid.getRight();
        this.annimations = UtilGraphicsImg.loadAllAnimations(numGridCol, numGridRow, WIDTH_FRAME, HEIGHT_FRAME, img);
    }

    private void updateAnimations(){
        aniTick++;
        if(aniTick >= aniSpeed ){
            aniTick=0;
            aniIndex++;
            if (aniIndex >= grid.getLeft()){
                aniIndex = 0;
            }
        }
    }

    @Override
    public void drawSurvivor(final Survivor sur,final Graphics2D g2d) {

        int surPosX = sur.getCurrentPos().getLeft().intValue();
        int surPosY = sur.getCurrentPos().getRight().intValue();
        updateAnimations();
        g2d.drawImage(annimations.get(3).get(aniIndex),surPosX,surPosY,WIDTH_IMAGE,HEIGHT_IMAGE,null);
        System.out.println("I am painting !!");
    }
    
}
