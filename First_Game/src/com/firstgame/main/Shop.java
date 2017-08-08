package com.firstgame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter{

	private Handler handler;
	HUD hud;
	
	private int B1 = 100;
	private int B2 = 100;
	private int B3 = 100;
	
	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("Shop", FirstGame.WIDTH/2-100, 50);
		
		//Box 1
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Upgrade Health", 108, 130);
		g.drawString("Cost: " + B1, 119, 160);
		g.drawRect(100,100,100,100);
		
		//Box 2		
		g.drawString("Upgrade Speed", 257, 130);
		g.drawString("Cost: " + B2, 269, 160);
		g.drawRect(250,100,100,100);
		
		//Box 3
		g.drawString("Refill Health", 417, 130);
		g.drawString("Cost: " + B3, 419, 160);
		g.drawRect(400,100,100,100);
		
		//Displays Score
		g.setFont(new Font("arail", 0 , 32));
		g.drawString("Score: "+ hud.getScore(), 175, 285);
		
		//Space to go back
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Press Space to return", FirstGame.WIDTH/2-100, FirstGame.HEIGHT/2+150);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//box1 Upgrade Health
		if(mx >= 100 && mx <= 200) {
			if(my >= 100 && my <= 200) {
				//selected box1
				if(hud.getScore() >= B1) {
					hud.score(hud.getScore() - B1);
					B1 += 100;
					
					hud.bounds += 20;
					hud.HEALTH = (100 + hud.bounds/2);
				}
			}
		}
		//box2 Upgrade Speed
		if(mx >= 250 && mx <= 350) {
			if(my >= 100 && my <= 200) {
				//selected box1
				if(hud.getScore() >= B2) {
					hud.score(hud.getScore() - B2);
					B2 += 500;
					
					handler.speed++;
				}
			}
		}
		//Refill Health
		if(mx >= 400 && mx <= 500) {
			if(my >= 100 && my <= 200) {
				//selected box1
				if(hud.getScore() >= B3) {
					hud.score(hud.getScore() - B3);					
					hud.HEALTH = (100 + hud.bounds/2);
				}
			}
		}
			
	}
}
