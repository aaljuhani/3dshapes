package main;

import lusidOSC.LusidClient;
import java.util.ArrayList;
import lusidOSC.LusidObject;




public class LusidOSCJavaApp {

	LusidClient lusidClient;
	
	ArrayList<LusidObject> lusidObj = new ArrayList<LusidObject>();
		
	public LusidOSCJavaApp(){
		// create the client, on port 3333.
		lusidClient = new LusidClient(this, 3333);
		
	}

	
	// -------------------------------------------------------------------
	// these methods are called whenever a LusidOSC event occurs.
	// -------------------------------------------------------------------
	// called when an object is added to the scene
	public void addLusidObject(LusidObject lObj) {
		
		//when object is added we add an instance of the object to lusidObj arraylist
		//lusidObj.add(lObj);
	
		
	  System.out.println("add object: "+lObj.getUniqueID());
	  System.out.println("  location = ("+lObj.getX()+","+lObj.getY()+","+lObj.getZ()+")");
	  System.out.println("  rotation = ("+lObj.getRotX()+","+lObj.getRotY()+","+lObj.getRotZ()+")");
	  System.out.println("      data = ("+lObj.getEncoding()+","+lObj.getData()+")");
	  
	/*  if(Shape.isShape(lObj.getUniqueID())){ //check id
		  System.out.println(Shape.getName(lObj.getUniqueID()));
		  System.out.println(Shape.getDesc(lObj.getUniqueID()));
	  }*/
	}
	// called when an object is removed from the scene
	public void removeLusidObject(LusidObject lObj) {
		lusidObj.remove(lObj);
		System.out.println("remove object: "+lObj.getUniqueID());
		
	}
	// called when an object is moved
	public void updateLusidObject (LusidObject lObj) {
		System.out.println("update object: "+lObj.getUniqueID());
	}
	
	public void clearLusidObj(){
		lusidObj.clear();
	}

	
	
}
