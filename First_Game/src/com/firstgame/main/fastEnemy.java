package com.firstgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class fastEnemy extends GameObject{
    private Handler handler;
    
    private BufferedImage fast_enemy;
    
    public fastEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = 2;
        velY = 9;
        
        SpriteSheet ss = new SpriteSheet(FirstGame.sprite_sheet);
        
        fast_enemy = ss.grabImage(1, 4, 16, 16);
    }
     public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    public void tick(){
        x += velX;
        y += velY;
        
        if (y <= 0 || y >= FirstGame.HEIGHT - 36) velY *= -1;
        if (x <= 0 || x >= FirstGame.WIDTH - 30) velX *= -1;
        
        //handler.addObject(new Trail(x, y, ID.Trail, Color.MAGENTA, 16, 16, 0.06f, handler));
    }
    
    public void render(Graphics g){
        
        //g.setColor(Color.MAGENTA);
        //g.fillRect((int)x, (int)y, 16, 16);
    	g.drawImage(fast_enemy, (int)x, (int)y, null);
    	
    }
}