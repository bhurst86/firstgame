package com.firstgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject{

    Random r = new Random();
    Handler handler;
    
    private BufferedImage player_image;
    
    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        
        SpriteSheet ss = new SpriteSheet(FirstGame.sprite_sheet);
        
        player_image = ss.grabImage(1, 1, 32, 32);
      
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }
    public void tick() {
        x += velX;
        y += velY;
        
        x = FirstGame.clamp(x, 0, FirstGame.WIDTH - 38);
        y = FirstGame.clamp(y, 0, FirstGame.HEIGHT - 60);
        
        //handler.addObject(new Trail(x, y, ID.Trail, Color.CYAN, 32, 32, 0.06f, handler));
       
        collision();
    }

    private void collision(){
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 2;
                }
            }
            if(tempObject.getId() == ID.EnemyBoss){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 100;
                }
            }
        }
    }
    public void render(Graphics g) {
       //g.setColor(Color.BLUE);       
       //g.fillRect((int)x, (int)y, 32, 32);
    	g.drawImage(player_image, (int)x, (int)y, null);
    }
    
}
