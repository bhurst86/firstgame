package com.firstgame.main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.firstgame.main.FirstGame.STATE;


public class Menu extends MouseAdapter{
    
    private FirstGame game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;
    
    public Menu(FirstGame game, Handler handler, HUD hud){
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }
    
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        //Main Menu
        if(FirstGame.gameState == FirstGame.STATE.Menu){
            //Play
            if(mouseOver(mx, my, 215, 115, 200, 64)){
                FirstGame.gameState = STATE.Select;
                
                AudioPlayer.getSound("menu_sound").play();
                return;
            }

            if(mouseOver(mx, my, 215, 190, 200, 64)){            //Help
                FirstGame.gameState = FirstGame.STATE.Help; 
                AudioPlayer.getSound("menu_sound").play();
            }


            if(mouseOver(mx, my, 215, 265, 200, 64)){            //Exit
                System.exit(0);            
            }            
        }
        
        											//This is the Second Menu to select Difficulty
        if(FirstGame.gameState == FirstGame.STATE.Select){
            													//Normal Mode
            if(mouseOver(mx, my, 215, 115, 200, 64)){
            	FirstGame.gameState = FirstGame.STATE.Game;
                handler.addObject(new Player(FirstGame.WIDTH/2-32, FirstGame.HEIGHT/2-32, ID.Player, handler));  
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.BasicEnemy, handler));
                
                game.diff = 0;
                
                AudioPlayer.getSound("menu_sound").play();
            }
            													//Hard Mode
            if(mouseOver(mx, my, 215, 190, 200, 64)){
            	 FirstGame.gameState = FirstGame.STATE.Game;
                 handler.addObject(new Player(FirstGame.WIDTH/2-32, FirstGame.HEIGHT/2-32, ID.Player, handler));  
                 handler.clearEnemys();
                 handler.addObject(new HardEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.BasicEnemy, handler));
                 
                 game.diff = 1;
                 
                 AudioPlayer.getSound("menu_sound").play();
            }

          
            if(mouseOver(mx, my, 215, 265, 200, 64)){
            	 FirstGame.gameState = FirstGame.STATE.Menu;
                 AudioPlayer.getSound("menu_sound").play();  	//Back
                 return;           
            }            
        }
        											//Back button for help screen
        if(FirstGame.gameState == FirstGame.STATE.Help){
            if(mouseOver(mx, my, 215, 360, 200, 64)){
            	FirstGame.gameState = FirstGame.STATE.Menu;
                AudioPlayer.getSound("menu_sound").play();
                return;
            }
        }
        
        											//This is the Game Over Screen
        if(FirstGame.gameState == FirstGame.STATE.End){
            if(mouseOver(mx, my, 215, 360, 200, 64)){
            	FirstGame.gameState = FirstGame.STATE.Menu;
                hud.setLevel(1);
                hud.score(0);
                handler.clearEnemys();
               
                AudioPlayer.getSound("menu_sound").play();
            }
        }
    }
    
    public void mouseReleased(MouseEvent e){
        
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
               return true;
            } else return false;
        } else return false;
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g){
        if(FirstGame.gameState == FirstGame.STATE.Menu){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Menu", 248, 55);			//Menu Text

            //play
            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawRect(215, 115, 200, 64);
            g.drawString("Play", 282, 158);			//Play

            //help
            g.setColor(Color.WHITE);
            g.drawRect(215, 190, 200, 64);
            g.drawString("Help", 280, 230);			//Help

            //exit
            g.setColor(Color.WHITE);
            g.drawRect(215, 265, 200, 64);
            g.drawString("Exit", 285, 305);         //Exit
        }else if(FirstGame.gameState == FirstGame.STATE.Help){
            Font fnt = new Font("arial", 1, 50);            
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            										
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Help", 248, 55);			//Outer box
            
            													
            g.setFont(fnt3);						//Some Instructions
            g.drawString("Use WASD to move player", 185, 300);

            
            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawRect(215, 360, 200, 64);			//Back Text and Position
                                //W  //H
            g.drawString("Back", 280, 402);
        }else if(FirstGame.gameState == FirstGame.STATE.End){
            Font fnt = new Font("arial", 1, 50);            
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Game Over!!", 185, 55);	//Outer box
            
            
            g.setFont(fnt3);
            g.drawString("You Lost! With a score of: " + hud.getScore(), 175, 190);			//Some Instructions

            
            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawRect(215, 360, 200, 64);			//Try Again Text and Position                                      //W  //H
            g.drawString("Back", 275, 402);
            
        }else if(FirstGame.gameState == FirstGame.STATE.Select){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Select Difficulty", 135, 55);		//Select Difficulty Text

            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawRect(215, 115, 200, 64);
            g.drawString("Normal", 270, 158);			//Normal Text box

            g.setColor(Color.WHITE);
            g.drawRect(215, 190, 200, 64);
            g.drawString("Hard", 280, 230);				//Hard Text box
          
            g.setColor(Color.WHITE);
            g.drawRect(215, 265, 200, 64);
            g.drawString("Back", 280, 305);            //Back Text box
        }
    }
}
