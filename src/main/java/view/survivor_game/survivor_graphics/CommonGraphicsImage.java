package view.survivor_game.survivor_graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import model.entities.survivor.base.Survivor;


public class CommonGraphicsImage implements GraphicsSurvivor {
    private BufferedImage img;
    private List<BufferedImage> idleAni;
    private int aniIndex,aniTick,aniSpeed = 15;
    private int totalFrames;

    public CommonGraphicsImage(final String nameClass){
        System.err.println("Import the Image");
        this.img = UtilGraphics.importImg(nameClass);
        loadAnimations();
    }

    private void loadAnimations(){
        System.out.println("Carico idle");

        if (img == null) {
            System.err.println("Error: Image don't loaded correctly!");
            return;
        }

        if (img.getWidth() % 48 != 0 || img.getHeight() % 64 != 0) {
            System.err.println("Attencion: image width and height not compatible with frames of 48px");
        }

        this.totalFrames = img.getWidth() / 48;
        idleAni = new ArrayList<>(totalFrames);
        for(int i=0 ;i < totalFrames ;i++ ){
            idleAni.add(img.getSubimage(i*48,5*64, 48, 64));
        }
    }

    private void updateAnimations(){
        aniTick++;
        if(aniTick >= aniSpeed ){
            aniTick=0;
            aniIndex++;
            if (aniIndex >= idleAni.size()){
                aniIndex = 0;
            }
        }
    }

    @Override
    public void drawSurvivor(final Survivor sur,final Graphics2D g2d) {

        int x = sur.getCurrentPos().getLeft().intValue();
        int y = sur.getCurrentPos().getRight().intValue();
        updateAnimations();
        g2d.drawImage(idleAni.get(aniIndex),x,y,80*5,128*5,null);
        System.out.println("I am painting !!");
    }
    
}
