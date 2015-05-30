package main;
import java.awt.*;
import javax.swing.JFrame;

public class Game  {
	
	static boolean isRunning;
	int x = 5;
	//LusidOSCJavaApp lusidOSC = new LusidOSCJavaApp(); 
	
	
	
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
    	new Shape();
		//new Task();
		new Player(); 
		
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
