package com.firstgame.main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
//https://www.youtube.com/watch?v=5ufOPX8N1Rg   Video 4 Completed
//https://www.youtube.com/watch?v=3paMFMwVfWU   Video 5 Completed
//https://www.youtube.com/watch?v=KpzvlvxZkcE   Video 6 Completed
//https://www.youtube.com/watch?v=JrSjwQbTldg   Video 7 Completed
//https://www.youtube.com/watch?v=Urg8AEIVyWA   Video 8 Completed
//https://www.youtube.com/watch?v=urye2D3_mss   Video 9 Completed
//https://www.youtube.com/watch?v=QgQUt3nuBx4   Video 10 Completed
//https://www.youtube.com/watch?v=HRaJXVuZjRM#t=25.257667  Video 11 Completed
//https://www.youtube.com/watch?v=K_CfBxvpd9A   Video 12 Completed
//https://www.youtube.com/watch?v=RrahDyZXAv0   Video 13
//https://www.youtube.com/watch?v=RrahDyZXAv0 Video 14 Complete

//First Commit 
//sdfg

/*
HedustMA – Forms 02
Released under a Creative Commons license for noncommercial usage.
*/

public class FirstGame extends Canvas implements Runnable{

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    
    private Thread thread;
    private boolean running = false;
    
    public static boolean paused = false;
    public int diff = 0;
    
    //0 = normal
    //1 = hard
    
    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    
    public enum STATE {
      Menu,
      Help,
      End,
      Select,
      Game
    };
    
    public static STATE gameState = STATE.Menu;
    
    public static BufferedImage sprite_sheet = null;
    
    public FirstGame(){
    	
    	BufferedImageLoader loader = new BufferedImageLoader();
        
    	try {
			sprite_sheet = loader.loadImage("/sprite_images.png");
			System.out.println("Loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        
        AudioPlayer.load();
        
        AudioPlayer.getMusic("music").loop();
        
        
        new Window(WIDTH, HEIGHT, "The Game", this);
                
        spawner = new Spawn(handler, hud, this);
        r = new Random();

        if(gameState == STATE.Game){
            handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));  
            handler.addObject(new BasicEnemy(r.nextInt(FirstGame.WIDTH - 50), r.nextInt(FirstGame.HEIGHT - 50), ID.BasicEnemy, handler));  //first enemy 
        }else {
            for (int i = 0; i < 15; i++) {
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                
            }
        }
        
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
    This run() block is the game loop
    */
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime)/ ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }         
        }
        stop();
    }
    
    private void tick(){
        
        
        if(gameState == STATE.Game)
        {
        	
            if(!paused) 
            {
            	hud.tick();
            	spawner.tick();
            	handler.tick();
	            if(HUD.HEALTH <= 0)
	            {
	                HUD.HEALTH = 100;              
	                gameState = gameState.End;
	                handler.clearEnemys();
	                for (int i = 0; i < 15; i++) 
	                {
	                    handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
	                
	                }
	            }
            }
        	
        }else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Help){
            menu.tick();
            handler.tick();
            
        }
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, WIDTH);
        
        handler.render(g);
        
        if(paused)
        {
        	g.setColor(Color.white);
        	g.drawString("PAUSED", 100, 100);
        }
        
        if(gameState == STATE.Game){
            hud.render(g);          
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
            menu.render(g);
        }
        
        g.dispose();
        bs.show();
    }
    
    //Clamp Method
    public static float clamp(float var, float min, float max){
        if (var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }
    
    public static void main(String[] args) {
      new FirstGame();
        
        
    }   
}
