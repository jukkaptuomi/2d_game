/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.tile;

import java.awt.Color;
import java.awt.Graphics;
import pkg2d.Game;
import pkg2d.Handler;
import pkg2d.Id;

/**
 *
 * @author jukkatuomi
 */
public class Wall extends Tile{

    public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        g.drawImage(Game.grass.getBufferedImage(), x, y,width,height, null);
    }

    public void tick() {
    }
    
}
