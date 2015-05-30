package main;

import java.util.ArrayList;

public class Task {

	private ArrayList<Task> taskList = new ArrayList<Task>();
	private String taskQ;
	private String taskAudio;
	
	
	public Task(){
		
		taskList.add(new Task("Where is the", "audiofile" ));
		taskList.add(new Task("Place two shape of", "audiofile" ));
		
	}
	
	public Task(String t, String audio){
		taskQ = t;
		taskAudio = audio;
}
	
	public void getTask(int level, Shape s){
		switch(level){
		case 1:
			System.out.println(taskList.get(0).taskQ + " " + s.getName());
			break;
		case 2:
			System.out.println(taskList.get(1).taskQ + " " + s.getName());
			break;
		}
	}
	
	
}
