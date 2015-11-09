package main;

import lusidOSC.LusidClient;

import java.util.ArrayList;
import java.util.Arrays;

import lusidOSC.LusidObject;




public class LusidOSCJavaApp {

	LusidClient lusidClient;
	//initialize all objects
	Shape shape = new Shape();
	Task task = new Task();
	Player player =	new Player(); 
	
	View view = new View();
	
	
	//setup env
	int width = 8;
	int height = 6;
	
	// a flag for running the game
	boolean isRunning = true;
	
	// a flag to distinguish between exploring and tasks level
	boolean isTask = false;
	
	// advance level has isSubTask
	boolean isSubTask = false;
	
	// sub task id
	int subTaskID;
	
	// a flag to indecate if answer is provided after each task
	private volatile boolean isAnswered = false;
	
	//an object to hold the answer for the current Task
	Shape[] taskShape;
	
	//current state for added object
	ArrayList<LusidObject> lusidArr = new ArrayList<LusidObject>();
	
	double oldDist ;

	
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
			while ((System.currentTimeMillis()-startTime)< 1*60*1000){
				 
			}
			
			//start Task level
			isTask = true;
			
			while(isTask){
				//check level
				System.out.println("is task");
			
				if(!isSubTask){
					//ask a question based on player level
					taskShape = shape.getRandomShapes();
					
					System.out.println(taskShape[0]);
					task.getTask(player.getlevel(),taskShape );
					// reset answer flag
					isAnswered = false;
				} else {
					System.out.println("is sub task");
					subTaskID = task.getSubTask(taskShape);
					isAnswered = false;
					
				}
					
					
				
				
				while(!isAnswered || (!isAnswered && !isSubTask) ){	
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
			if(!isSubTask){
				checkAnswer();
			}
			
			
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
		
		ArrayList<LusidObject> currentLusidList = new ArrayList<LusidObject>(Arrays.asList(lusidClient.getLusidObjects()));
		
		
		//System.out.println("current size "+currentLusidList.size());
		
		//System.out.println("update object: "+lObj.getUniqueID());
		if(isSubTask && currentLusidList.size() > 1 ){
			switch(subTaskID){
			case 1:
				//System.out.println(calculateDistance(currentLusidList) +" case 1 "+ oldDist);
				if(oldDist - calculateDistance(currentLusidList) > 10  ){
					System.out.println("horray");
					isSubTask = false;
					correctAnswer();
				} else {
					//wrongAnswer();
				}
				break;
			case 2:
				if(calculateDistance(currentLusidList) - oldDist > 10  ){
					System.out.println("horray");
					isSubTask = false;
					correctAnswer();
				} 
				break;
			}
			
		}
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
			if(getAddedObj().getUniqueID().startsWith(taskShape[0].getUID())){
				correctAnswer();
			}
			else{
				wrongAnswer();
			}
			break;
		case 2:
			// check if the first object is correct
			if(lusidArr.size() == 1){
				if(getAddedObj().getUniqueID().startsWith(taskShape[0].getUID())){
				// if the first object correct, ask for the other one
					//add the other one
					System.out.println("Add the other one");
				} else {
					wrongAnswer();
				}
			} else {
				LusidObject lastObject = getAddedObj();
				if(lastObject.getUniqueID().startsWith(taskShape[0].getUID())){
					correctAnswer();
				}
				else{
					wrongAnswer();
				}
			}
			break;
		case 3:
			// check if the first object is correct
						if(lusidArr.size() == 1){
							if(getAddedObj().getUniqueID().startsWith(taskShape[0].getUID())){
							// if the first object correct, ask for the other one
								//add the other one
								System.out.println("Add the other shape");
							} else {
								wrongAnswer();
							}
						} else {
							LusidObject lastObject = getAddedObj();
							if(lastObject.getUniqueID().startsWith(taskShape[1].getUID())){
								//set isSubTask
								isSubTask = true;
								//save current state
								oldDist = calculateDistance(lusidArr);
								System.out.println("DISTENCE " +calculateDistance(lusidArr));
								correctAnswer();
								
							}
							else{
								wrongAnswer();
							}
						}
						break;
			
		}
		
	}
	
	public double calculateDistance(ArrayList<LusidObject> oldState){
		
		    LusidObject lObj1 = oldState.get(0);
		    // shift the X and Y so they are centered on the screen.
		    int x1 = width/2 + lObj1.getX();
		    int y1 = height/2 - lObj1.getY();
		    float rotation1 = lObj1.getRotZ();
		    
		        LusidObject lObj2 = oldState.get(1);
		        // shift the X and Y so they are centered on the screen.
		        int x2 = width/2 + lObj2.getX();
		        int y2 = height/2 - lObj2.getY();
		        float rotation2 = lObj2.getRotZ();

		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	public void correctAnswer(){
		System.out.println("thats correct");
		//give positive feedback
		
		//increment score
		player.answerCorrect();
		
		//if(!isSubTask){
			//set the flag to true
			isAnswered = true;
		//}
		
	}
	
	public void wrongAnswer(){
		System.out.println("Try again");
		//Try again audio
		
		player.answerWrong();
	}

	
	
}
