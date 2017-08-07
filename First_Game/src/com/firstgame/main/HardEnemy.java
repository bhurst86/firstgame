package com.firstgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class HardEnemy extends GameObject{

    private Handler handler;
    private Random r = new Random();
    
    private BufferedImage hard_enemy;
    
    public HardEnemy(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = 5;
        velY = 5;
        
        SpriteSheet ss = new SpriteSheet(FirstGame.sprite_sheet);
        
        hard_enemy = ss.grabImage(1, 3, 16, 16);
    }
     public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    public void tick(){
        x += velX;
        y += velY;
        
        if (y <= 0 || y >= FirstGame.HEIGHT - 36) {if(velY < 0) velY = -(r.nextInt(7)+1)*-1; else velY = (r.nextInt(7)+1)*-1;}
        if (x <= 0 || x >= FirstGame.WIDTH - 30) {if(velX < 0) velX = -(r.nextInt(7)+1)*-1; else velX = (r.nextInt(7)+1)*-1;}
        
       //handler.addObject(new Trail(x, y, ID.Trail, Color.orange, 16, 16, 0.06f, handler));
    }
    
    public void render(Graphics g){
        
        //g.setColor(Color.orange);
        //g.fillRect((int)x, (int)y, 16, 16);
    	g.drawImage(hard_enemy, (int)x, (int)y, null);
    }
}