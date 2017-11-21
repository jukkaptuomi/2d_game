/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.entity;

import pkg2d.Handler;
import pkg2d.Id;
import java.awt.Color;
import java.awt.Graphics;
import pkg2d.Game;
import pkg2d.tile.Tile;


public class Player extends Entity {

    public int frame = 0;
    public int frameDelay = 0;
    
    public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(Game.player[frame].getBufferedImage(), x, y, width, height,null);
        
        
    }
    @Override
    public void tick() {
        if(velX != 0) isMoving = true;
        else isMoving = false;
        x += velX;
        y += velY;
        if(x <= 0) x = 0;
        if(y <= 0) y = 0;
//        if(x + width >= 1080) x = 1080 - width;
//        if(y + height >= 771) y = 771 - height;
        for(Tile t: handler.tile){
            if(!t.isSolid())break;
            
                if(getBoundsTop().intersects(t.getBounds())){
                    setVelY(0);
                    if(jumping){
                        jumping = false;
                        falling = true;
                        gravity = 0.8;
                    }
                    
                }
                if(getBoundsBottom().intersects(t.getBounds())){
                    setVelY(0);
                    if(falling) falling = false;
                }else{
                    if(!falling && !jumping){
                        falling = true;
                        gravity = 0.8;
                    }
                }
                if(getBoundsLeft().intersects(t.getBounds())){
                    setVelX(0);
                    x = t.getX() + width;
                    
                }
                if(getBoundsRight().intersects(t.getBounds())){
                    setVelX(0);
                    x = t.getX() - width;
                    
                
            }
                
        }
        for(Entity t: handler.entity){
            if(!t.isSolid() || t.id == Id.player )continue;
            
                if(getBoundsTop().intersects(t.getBounds())){
                    setVelY(0);
                    if(jumping){
                        jumping = false;
                        falling = true;
                        gravity = 0.8;
                        die();
                    }
                    
                }
                if(getBoundsBottom().intersects(t.getBounds())){
                    setVelY(0);
                    if(falling) falling = false;
                }else{
                    if(!falling && !jumping){
                        falling = true;
                        gravity = 0.8;
                        die();
                    }
                }
                if(getBoundsLeft().intersects(t.getBounds())){
                    setVelX(0);
                    x = t.getX() + width;
                    die();
                    
                }
                if(getBoundsRight().intersects(t.getBounds())){
                    setVelX(0);
                    x = t.getX() - width;
                    die();
                    
                
            }
                
        }
        if(jumping){
            gravity -= 0.3;
            setVelY((int)-gravity);
            if(gravity <= 0.0){
                jumping = false;
                falling = true;
            }
        }
        if(falling){
            gravity += 0.1;
            setVelY((int)gravity);
        }
        if(isMoving){
        frameDelay++;
        
        if(frameDelay >= 5){
            frame++;
            if(frame >= 3){
                frame = 0;
            }
            frameDelay = 0;
        }
        }
    }
    
    
}
