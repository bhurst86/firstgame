package com.firstgame.main;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


public class AudioPlayer {
    
    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();    
    public static Map<String, Music> musicMap = new HashMap<String, Music>();
    
    public static void load(){
        try {
            soundMap.put("menu_sound", new Sound("res/button-3.ogg"));
            
            musicMap.put("music", new Music("res/background_music.ogg"));
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
        
    }
    public static Music getMusic(String key){
        return musicMap.get(key);
    }
    public static Sound getSound(String key){
        return soundMap.get(key);
    }
}