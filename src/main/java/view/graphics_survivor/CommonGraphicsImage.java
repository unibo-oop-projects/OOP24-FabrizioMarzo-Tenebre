package view.graphics_survivor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class CommonGraphicsImage implements GraphicsSurvivor {
    private BufferedImage img;
    private List<BufferedImage> idleAni;
    private int aniIndex,aniTick,aniSpeed = 5;
    private int totalFrames;

    public CommonGraphicsImage(){
        importImg();
        loadAnimations();
    }

     private void importImg(){
        InputStream is = getClass().getResourceAsStream("/Common.png");
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
    public void drawSurvivor(Graphics2D g2d) {

        updateAnimations();
        g2d.drawImage(idleAni.get(aniIndex),0,0,80*5,128*5,null);
        System.out.println("I am painting !!");
    }
    
}
