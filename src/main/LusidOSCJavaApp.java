package main;

import lusidOSC.LusidClient;

import java.util.ArrayList;

import lusidOSC.LusidObject;




public class LusidOSCJavaApp {

	LusidClient lusidClient;
	static boolean isRunning;
	
	ArrayList<LusidObject> lusidArr = new ArrayList<LusidObject>();
	
	public static void main(String[] args) {
		// start things up outside the static main() method.
		new LusidOSCJavaApp();
		
		
	}
		
	public LusidOSCJavaApp(){
		// create the client, on port 3333.
		lusidClient = new LusidClient(this, 3333);
		
		//initialize all objects
				new Shape();
				new Task();
				new Player(); 
				
				run();
				isRunning = true;
		
	}
	
	/** 
     * This method starts the game and runs it in a loop 
     */ 
    public void run() 
    { 
    	
    	while(isRunning){
    		update();
    		render();
    	}
            
    } 
    
    
    /** 
     * This method will check for input, move things 
     * around and check for win conditions, etc 
     */ 
    public void update() 
    { 
    	 System.out.println("******************************************update*************************");
    	LusidObject lObj = getAddedObj();
    	if(lObj != null)
    	{
    		if(Shape.isShape(lObj.getUniqueID())){ //check id
    			  System.out.println(Shape.getName(lObj.getUniqueID()));
    			  System.out.println(Shape.getDesc(lObj.getUniqueID()));
    		  }
    		
    	}
    
    } 
    
    /** 
     * This method will draw everything 
     */ 
    public void render() 
    { 

            
    } 

	
	// -------------------------------------------------------------------
	// these methods are called whenever a LusidOSC event occurs.
	// -------------------------------------------------------------------
	// called when an object is added to the scene
	public void addLusidObject(LusidObject lObj) {
		
		//when object is added we add an instance of the object to lusidObj arraylist
		lusidArr.add(lObj);
	
		
	  System.out.println("add object: "+lObj.getUniqueID());
	  System.out.println("  location = ("+lObj.getX()+","+lObj.getY()+","+lObj.getZ()+")");
	  System.out.println("  rotation = ("+lObj.getRotX()+","+lObj.getRotY()+","+lObj.getRotZ()+")");
	  System.out.println("      data = ("+lObj.getEncoding()+","+lObj.getData()+")");
	  
	}
	// called when an object is removed from the scene
	public void removeLusidObject(LusidObject lObj) {
		lusidArr.remove(lObj);
		System.out.println("remove object: "+lObj.getUniqueID());
		
	}
	// called when an object is moved
	public void updateLusidObject (LusidObject lObj) {
		System.out.println("update object: "+lObj.getUniqueID());
	}
	
	public void clearLusidObj(){
		lusidArr.clear();
	}
	
	public LusidObject getAddedObj(){
		return lusidArr.get(lusidArr.size()-1);
	}

	
	
}
