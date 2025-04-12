package view.graphics_survivor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class CommonGraphicsImage implements GraphicsSurvivor {
    private BufferedImage img;

    public CommonGraphicsImage(){
        importImg();
    }

     private void importImg(){
        InputStream is = getClass().getResourceAsStream("/Idle.png");
        try {
            img = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawSurvivor(Graphics2D g2d) {
        g2d.drawImage(img, 0,0, null);
        System.out.println("I am painting !!");
    }
    
}
