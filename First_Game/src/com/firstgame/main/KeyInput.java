package com.firstgame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.firstgame.main.FirstGame.STATE;


public class KeyInput extends KeyAdapter{
    
    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    private FirstGame game;
    
    public KeyInput(Handler handler, FirstGame game){
        this.handler = handler;
        this.game = game;
        
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
        
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if (tempObject.getId() == ID.Player){
                //key events for player 1
                
                if(key == KeyEvent.VK_W) {tempObject.setVelY(-handler.speed); keyDown[0] = true;}
                if(key == KeyEvent.VK_S) {tempObject.setVelY(+handler.speed); keyDown[1] = true;}
                if(key == KeyEvent.VK_A) {tempObject.setVelX(-handler.speed); keyDown[2] = true;}
                if(key == KeyEvent.VK_D) {tempObject.setVelX(+handler.speed); keyDown[3] = true;}            
            }            
        }
        if(key == KeyEvent.VK_P)
        {
        	
        	if(FirstGame.gameState == STATE.Game) {
        		if(FirstGame.paused) FirstGame.paused = false;     	
        		else FirstGame.paused = true;
        	}
        }
        if(key == KeyEvent.VK_ESCAPE)System.exit(0);
        if(key == KeyEvent.VK_SPACE) {
        	if(FirstGame.gameState == STATE.Game) FirstGame.gameState = STATE.Shop;
        	else if (FirstGame.gameState == STATE.Shop) FirstGame.gameState = STATE.Game;
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if (tempObject.getId() == ID.Player){
                //key events for player 1
                
                if(key == KeyEvent.VK_W) keyDown[0] = false; 
                if(key == KeyEvent.VK_S) keyDown[1] = false;              
                if(key == KeyEvent.VK_A) keyDown[2] = false;
                if(key == KeyEvent.VK_D) keyDown[3] = false;
                
                //Vertical Movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //Horizontal Movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
                
                
            }
            
        }
    }
}