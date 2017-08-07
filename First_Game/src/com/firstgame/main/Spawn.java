package com.firstgame.main;

import java.util.Random;

public class Spawn {
  
    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    private FirstGame game;
    private Random r = new Random();
    
    public Spawn(Handler handler, HUD hud, FirstGame game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }
    
    //Level Cycle
    public void tick(){
        
        scoreKeep++;
        
        if(scoreKeep >= 100){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            
            if(game.diff == 0) 
            {
	            if(hud.getLevel() == 2){
	                handler.addObject(new BasicEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.BasicEnemy, handler));               
	            }
	            else if(hud.getLevel()==3){
	                handler.addObject(new fastEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.FastEnemy, handler));                
	            }
	            else if(hud.getLevel()== 4){
	                handler.addObject(new fastEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.FastEnemy, handler));
	            }
	            else if(hud.getLevel()== 5){
	                handler.addObject(new SmartEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.SmartEnemy, handler));
	            }
	            else if(hud.getLevel()== 6){
	                handler.addObject(new SmartEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.SmartEnemy, handler));
	            }
	            else if(hud.getLevel()== 7){
	                handler.addObject(new BasicEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.BasicEnemy, handler));
	                handler.addObject(new BasicEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.BasicEnemy, handler));
	            }
	            else if(hud.getLevel()== 10){
	                handler.clearEnemys();
	                handler.addObject(new EnemyBoss((FirstGame.WIDTH /2 - 32), -120, ID.EnemyBoss, handler));
	            }
            }else if(game.diff == 1) 
	            {
		            if(hud.getLevel() == 2){
		                handler.addObject(new HardEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.BasicEnemy, handler));
		                handler.addObject(new HardEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.BasicEnemy, handler));   
		            }
		            else if(hud.getLevel()==3){
		                handler.addObject(new fastEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.FastEnemy, handler));
		                handler.addObject(new fastEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.FastEnemy, handler));
		            }
		            else if(hud.getLevel()== 4){
		                handler.addObject(new fastEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.FastEnemy, handler));
		            }
		            else if(hud.getLevel()== 5){
		                handler.addObject(new SmartEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.SmartEnemy, handler));
		            }
		            else if(hud.getLevel()== 6){
		                handler.addObject(new SmartEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.SmartEnemy, handler));
		            }
		            else if(hud.getLevel()== 7){
		                handler.addObject(new HardEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.BasicEnemy, handler));
		                handler.addObject(new HardEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.BasicEnemy, handler));
		            }
		            else if(hud.getLevel()== 10){
		                handler.clearEnemys();
		                handler.addObject(new EnemyBoss((FirstGame.WIDTH /2 - 32), -120, ID.EnemyBoss, handler));
		            }
	            }
        }
    }
}
