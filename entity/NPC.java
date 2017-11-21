
package pkg2d.entity;

import pkg2d.Handler;
import pkg2d.Id;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import pkg2d.Game;
import pkg2d.tile.Tile;


public class NPC extends Entity{

    public NPC(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
        velX = -2;
    }

    public void render(Graphics g) {
        g.drawImage(Game.mob.getBufferedImage(), x, y,width,height, null);
    }

    public void tick() {
    
        if(velX != 0) isMoving = true;
        else isMoving = false;
        x += velX;
        y += velY;
        if(x <= 0) x = 0;
        if(y <= 0) y = 0;
////        if(x + width >= 1080) x = 1080 - width;
////        if(y + height >= 771) y = 771 - height;
////        for(Tile t: handler.tile){
////            if(!t.isSolid())break;
////            if(t.getId() == Id.wall){
////                if(getBoundsTop().intersects(t.getBounds())){
////                    setVelY(0);
////                    if(jumping){
////                        jumping = false;
////                        falling = true;
////                        gravity = 0.8;
////                    }
////                    
////                }
////                if(getBoundsBottom().intersects(t.getBounds())){
////                    setVelY(0);
////                    if(falling) falling = false;
////                }else{
////                    if(!falling && !jumping){
////                        falling = true;
////                        gravity = 0.8;
////                    }
////                }
////                if(getBoundsLeft().intersects(t.getBounds())){
////                    setVelX(0);
////                    x = t.getX() + width;
////                    
////                }
////                if(getBoundsRight().intersects(t.getBounds())){
////                    setVelX(0);
////                    x = t.getX() - width;
////                    
////                }
////            }
////        }
////        
////        if(falling){
////            gravity += 0.1;
////            setVelY((int)gravity);
////        }
////        
       }
  
    }
    

    
