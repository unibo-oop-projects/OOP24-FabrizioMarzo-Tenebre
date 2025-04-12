package view.graphics_survivor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class CommonGraphicsImage implements GraphicsSurvivor {
    private BufferedImage img;
    private BufferedImage[] idleAni;
    private int aniIndex,aniTick,aniSpeed = 15;

    public CommonGraphicsImage(){
        importImg();
        loadAnimations();
    }

     private void importImg(){
        InputStream is = getClass().getResourceAsStream("/Idle.png");
        try {
            img = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadAnimations(){
        System.out.println("Carico idle");
        idleAni = new BufferedImage[8];
        for(int i=0 ;i < idleAni.length ;i++ ){
            idleAni[i] = img.getSubimage(i*48,5*64, 48, 64);
        }
    }

    private void updateAnimations(){
        aniTick++;
        if(aniTick >= aniSpeed ){
            aniTick=0;
            aniIndex++;
            if (aniIndex >= idleAni.length){
                aniIndex = 0;
            }
        }
    }

    @Override
    public void drawSurvivor(Graphics2D g2d) {

        updateAnimations();
        g2d.drawImage(idleAni[aniIndex],0,0,80*5,128*5,null);
        System.out.println("I am painting !!");
    }
    
}
