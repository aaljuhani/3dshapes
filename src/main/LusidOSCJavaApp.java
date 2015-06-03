package main;

import lusidOSC.LusidClient;

import java.util.ArrayList;

import lusidOSC.LusidObject;




public class LusidOSCJavaApp {

	LusidClient lusidClient;
	//initialize all objects
	Shape shape = new Shape();
	Task task = new Task();
	Player player =	new Player(); 
	
	// a flag for running the game
	boolean isRunning = true;
	
	// a flag to distinguish between exploring and tasks level
	boolean isTask = false;
	
	// a flag to indecate if answer is provided after each task
	private volatile boolean isAnswered = false;
	
	//an object to hold the answer for the current Task
	Shape taskShape;
	
	
	ArrayList<LusidObject> lusidArr = new ArrayList<LusidObject>();
	
	public static void main(String[] args) {
		// start things up outside the static main() method.
		new LusidOSCJavaApp();
				
	}
		
		
	public LusidOSCJavaApp(){
		// create the client, on port 3333.
		lusidClient = new LusidClient(this, 3333);
		
		// after X min the exploring level will end
		long startTime = System.currentTimeMillis();
		
		while(isRunning){
			//the first X min is for exploring 
			/*while ((System.currentTimeMillis()-startTime)< 1*60*1000){
				 
			}*/ 
			
			//start Task level
			isTask = true;
			
			while(isTask){
				//ask a question based on player level
				taskShape = shape.getRandomShape();
				task.getTask(player.getlevel(),taskShape );
				// reset answer flag
				isAnswered = false;
				
				while(!isAnswered){	
					try {
						Thread.sleep(5000);
						//ask again
						//task.getTask(1,taskShape );
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
		}
		
	}
	

	
	// -------------------------------------------------------------------
	// these methods are called whenever a LusidOSC event occurs.
	// -------------------------------------------------------------------
	// called when an object is added to the scene
	public void addLusidObject(LusidObject lObj) {
		
		//when object is added we add an instance of the object to lusidObj arraylist
		lusidArr.add(lObj);
		
		// if we are in exploring level
		if(!isTask){
			try{
				 System.out.println(shape.getName(lObj.getUniqueID()));
				 System.out.println(shape.getDesc(lObj.getUniqueID()));
			}
			catch(Error e)
			{
				System.out.println("no shape");
			}
		} else {
			checkAnswer();
			
		}
			
	  System.out.println("add object: "+lObj.getUniqueID());
	  System.out.println("  location = ("+lObj.getX()+","+lObj.getY()+","+lObj.getZ()+")");
	  System.out.println("  rotation = ("+lObj.getRotX()+","+lObj.getRotY()+","+lObj.getRotZ()+")");
	  System.out.println("      data = ("+lObj.getEncoding()+","+lObj.getData()+")");
	  System.out.println("#######################################");
	  System.out.println(lusidArr.size());
	  
	}
	// called when an object is removed from the scene
	public void removeLusidObject(LusidObject lObj) {
		lusidArr.remove(lObj);
		System.out.println("remove object: "+lObj.getUniqueID());
		
	}
	// called when an object is moved
	public void updateLusidObject (LusidObject lObj) {
		//System.out.println("update object: "+lObj.getUniqueID());
	}
	
	public void clearLusidObj(){
		lusidArr.clear();
	}
	
	public LusidObject getAddedObj(){
		return lusidArr.isEmpty() ? null : lusidArr.get(lusidArr.size()-1);
	}
	
	public void checkAnswer(){
		switch(player.getlevel()){
		case 1:
			if(getAddedObj().getUniqueID().startsWith(taskShape.getUID())){
				correctAnswer();
			}
			else{
				wrongAnswer();
			}
			break;
		case 2:
			// check if the first object is correct
			if(lusidArr.size() == 1){
				if(getAddedObj().getUniqueID().startsWith(taskShape.getUID())){
				// if the first object correct, ask for the other one
					//add the other one
					System.out.println("Add the other one");
				} else {
					wrongAnswer();
				}
			} else {
				LusidObject lastObject = getAddedObj();
				if(lastObject.getUniqueID().startsWith(taskShape.getUID())){
					correctAnswer();
				}
				else{
					wrongAnswer();
				}
			}
			break;
		}
		
	}
	
	public void correctAnswer(){
		System.out.println("thats correct");
		//give positive feedback
		
		//increment score
		player.answerCorrect();
		
		// set the flag to true
		isAnswered = true;
	}
	
	public void wrongAnswer(){
		System.out.println("Try again");
		//Try again audio
		
		player.answerWrong();
	}

	
	
}
