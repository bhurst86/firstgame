package com.firstgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{
    private Handler handler;
    Random r = new Random();
    
    public EnemyBossBullet(float x, float y, ID id, Handler handler){
        super(x, y, id);
        
        this.handler = handler;
        
        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
    }
     public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    public void tick(){
        x += velX;
        y += velY;
        
        //if (y <= 0 || y >= FirstGame.HEIGHT - 36) velY *= -1;
        // if (x <= 0 || x >= FirstGame.WIDTH - 30) velX *= -1;
        
        if(y >= FirstGame.HEIGHT) handler.removeObject(this);
       
        handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 16, 16, 0.02f, handler));
    }
    
    public void render(Graphics g){
        
        g.setColor(Color.yellow);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}