/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d;

import pkg2d.entity.Player;
import pkg2d.input.KeyInput;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame; 
import pkg2d.entity.Entity;
import pkg2d.gfx.Sprite;
import pkg2d.gfx.SpriteSheet;
import pkg2d.tile.Wall;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 270;
    public static final int HEIGHT = WIDTH / 14 * 10;
    public static final int SCALE = 4;
    public static final String TITLE = "Peli";
    private Thread thread;
    private boolean running = false;
    private BufferedImage image;
    
    public static Handler handler;
    public static SpriteSheet sheet;
    public static Camera cam;
    
    public static Sprite player[]= new Sprite[3];
    public static Sprite grass;
    public static Sprite mob;
    
    public Game() {
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }
    private void init(){
        handler = new Handler();
        sheet = new SpriteSheet("/nimetön.png");
        cam = new Camera();
        
        addKeyListener(new KeyInput());
        
        mob = new Sprite(sheet,1,15);
        grass = new Sprite(sheet,1,1);
        for (int i = 0; i < player.length; i++) {
            player[i] = new Sprite(sheet,i+1,16);
        }
        try {
            image = ImageIO.read(getClass().getResource("/level.png"));
            // handler.addEntity(new Player(400,200,64,64,true,Id.player,handler));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        handler.createLevel(image);
        
    }
    private synchronized void start() {
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    private synchronized void stop() {
        if(!running) return;
        running = false;
        try { 
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    } 
    @Override
    public void run() {
        init();
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        int frames = 0;
        int ticks = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1.0) {
            tick();
            ticks++;
            delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(frames + " FPS & " + ticks + " Updates PS");
                frames = 0;
                ticks = 0;
            }
        }
        stop();
    }
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(150,120,124));
        g.fillRect(0, 0, getWidth(), getHeight()); 
        g.translate(cam.getX(), cam.getY());
        handler.render(g);
        
        g.dispose();
        bs.show();
        
        
    }
    public void tick() {
        handler.tick();
        for(Entity e : handler.entity){
            if(e.getId() == Id.player){
                cam.tick(e);
            }
        }
    } 
    public static int getFrameWidth(){
        return WIDTH*SCALE;
    }
    public static int getFrameHeight(){
        return HEIGHT*SCALE;
    }
    
    
    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame(TITLE);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.start();
    }
}