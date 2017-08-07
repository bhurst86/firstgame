package com.firstgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SmartEnemy extends GameObject{
    private Handler handler;
    private GameObject player;
    
    private BufferedImage enemy_image;
    
    public SmartEnemy(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        
        for (int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }
        
        SpriteSheet ss = new SpriteSheet(FirstGame.sprite_sheet);
        enemy_image = ss.grabImage(1, 3, 16, 16);
               
    }
     public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    public void tick(){
        x += velX;
        y += velY;
        
        float diffX = x - player.getX() - 16;
        float diffY = y - player.getY() - 16;
        float distance = (float)Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
        
        velX = (float)((-1.0/distance) * diffX);
        velY = (float)((-1.0/distance)* diffY);
        
        if (y <= 0 || y >= FirstGame.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= FirstGame.WIDTH - 16) velX *= -1;
        
        //handler.addObject(new Trail(x, y, ID.Trail, Color.ORANGE, 16, 16, 0.06f, handler));
    }
    
    public void render(Graphics g){
        
        //g.setColor(Color.ORANGE);
        //g.fillRect((int)x, (int)y, 16, 16);
    	g.drawImage(enemy_image, (int)x, (int)y, null);
    }
}
