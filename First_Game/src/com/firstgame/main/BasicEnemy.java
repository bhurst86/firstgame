package com.firstgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject{

private BufferedImage enemy_image;	
	
    private Handler handler;
    public BasicEnemy(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = 5;
        velY = 5;
        
        SpriteSheet ss = new SpriteSheet(FirstGame.sprite_sheet);
        
        enemy_image = ss.grabImage(1, 2, 16, 16);
        
    }
     public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    public void tick(){
        x += velX;
        y += velY;
        
        if (y <= 0 || y >= FirstGame.HEIGHT - 36) velY *= -1;
        if (x <= 0 || x >= FirstGame.WIDTH - 30) velX *= -1;
        
        //handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.06f, handler));
    }
    
    public void render(Graphics g){
        
        //g.setColor(Color.red);
        //g.fillRect((int)x, (int)y, 16, 16);
    	g.drawImage(enemy_image, (int)x, (int)y, null);
    }
}
