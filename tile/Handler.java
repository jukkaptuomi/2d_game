/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d;

import pkg2d.entity.Entity;
import pkg2d.entity.Player;
import pkg2d.tile.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import static pkg2d.Game.handler;
import pkg2d.entity.NPC;
import pkg2d.tile.Wall;

/**
 *
 * @author jukkatuomi
 */
public class Handler {
    public LinkedList<Entity> entity = new LinkedList<Entity>();
    public LinkedList<Tile> tile = new LinkedList<Tile>();

    
     public void render(Graphics g){
         for(Entity en:entity){
            en.render(g);
        }
        for(Tile ti:tile){
            ti.render(g);
        }
        
    }
    public void tick(){
        for(Entity en:entity){
            en.tick();
        }
        for(Tile ti:tile){
            ti.tick();
        }
        
    }
    public void addEntity(Entity en){
        entity.add(en);
    }
    public void removeEntity(Entity en){
        entity.remove(en);
    }
    public void addTile(Tile ti){
        tile.add(ti);
    }
    public void removeTile(Tile ti){
        tile.remove(ti);
    }
    public void createLevel(BufferedImage level){
       int width = level.getWidth();
       int height = level.getHeight();
       
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = level.getRGB(x, y);
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = (pixel) & 0xFF;
                
                if(red == 255&&green==0&&blue==0)addEntity(new NPC(x*64,y*64,64,64,true,Id.npc,this));
                if(red == 0&&green==0&&blue==0)addTile(new Wall(x*64,y*64,64,64,true,Id.wall,this));
                if(red == 0&&green==0&&blue==255)addEntity(new Player(x*64,y*64,64,64,true,Id.player,this));
                
                
            }
        }
    }
}
