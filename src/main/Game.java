package main;
import java.awt.*;
import javax.swing.JFrame;

public class Game {
	
	static boolean isRunning;
	int x = 5;
	
	
	public static void main(String[] args){
		System.out.println("I am in Game");
		Game g = new Game();
		g.run();
		System.exit(0);
		
	
		
	}
	/** 
     * This method starts the game and runs it in a loop 
     */ 
    public void run() 
    { 
    	initialize();
    	
    	while(isRunning){
    		update();
    		render();
    	}
            
    } 
    
    /** 
     * This method will set up everything need for the game to run 
     */ 
    public void initialize() 
    { 
    	
		
		//play audio welcom
    } 
    
    /** 
     * This method will check for input, move things 
     * around and check for win conditions, etc 
     */ 
    public void update() 
    { 
        
    } 
    
    /** 
     * This method will draw everything 
     */ 
    public void render() 
    { 

            
    } 
}
