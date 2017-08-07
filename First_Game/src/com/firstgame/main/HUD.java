package com.firstgame.main;


import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Ben
 */
public class HUD {
    public static float HEALTH = 100;
    private float greenValue = 255;
    
    private int score = 0;
    private int level = 1;

    //This shows the health change
    public void tick(){
        HEALTH = FirstGame.clamp(HEALTH, 0, 100);
        greenValue = FirstGame.clamp(greenValue, 0, 255);
        
        greenValue = HEALTH*2;
        
        score++;
    }
    
    
    //This renders the Health bar
    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(85, (int)greenValue, 0));
        g.fillRect(15, 15, (int)HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
        
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
        g.drawString("Space for Shop", 15, 94);
    }
    public void score(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
}