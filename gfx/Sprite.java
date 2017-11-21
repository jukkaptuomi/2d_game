
package pkg2d.gfx;

import java.awt.image.BufferedImage;


public class Sprite {
    public SpriteSheet sheet;
    public BufferedImage image;
    
    
    public Sprite(SpriteSheet sheet, int x, int y){
        image = sheet.getSprite(x, y);
    }
    public BufferedImage getBufferedImage(){
        return image;
    }
}

//09 471 86800 ma-pe 7.30- 15.30
//3.3 10. 7
