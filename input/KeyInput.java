/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.input;

import pkg2d.entity.Entity;
import pkg2d.Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pkg2d.Id;

public class KeyInput implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (Entity en : Game.handler.entity) {
            if(en.id != Id.player) break;
//Entity en = Game.handler.entity.get(1);
        switch(key){
            case KeyEvent.VK_W:
                if(!en.jumping){
                    en.jumping = true;
                    en.gravity = 10.0;
                }
                break;
            case KeyEvent.VK_A:
                if(!en.jumping){
                    en.setVelX(-5);
                }else{
                    en.setVelX(-3);
                }
                break;
            case KeyEvent.VK_D:
                if(!en.jumping){
                    en.setVelX(5);
                }else{
                    en.setVelX(3);
                }
                en.facing = 1;
                break;    
        }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (Entity en : Game.handler.entity) {
            switch (key) {
                case KeyEvent.VK_W:
                    en.setVelY(0);
                    break;
                case KeyEvent.VK_A:
                    en.setVelX(0);
                    break;
                case KeyEvent.VK_D:
                    en.setVelX(0);
                    break;
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
