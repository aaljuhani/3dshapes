package main;

import java.util.ArrayList;

public class Task {

	private ArrayList<Task> taskList = new ArrayList<Task>();
	private String taskQ;
	private String taskAudio;
	
	private String taskQ2;
	private String taskAudio2;
	
	
	public Task(){
		
		taskList.add(new Task("Where is the", "audiofile" ));
		taskList.add(new Task("Place two shape of", "audiofile" ));
		taskList.add(new Task("Place a shape of", "audiofile" , "and a shape of", "audiofile"));

		
	}
	
	public Task(String t, String audio){
		taskQ = t;
		taskAudio = audio;
}
	public Task(String t1 , String audio1, String t2, String audio2){
		taskQ = t1;
		taskAudio = audio1;
		taskQ2 = t2;
		taskAudio2 = audio2;
	}
	
	public void getTask(int level, Shape[] taskShape){
		System.out.println(level+"level one task"+ taskShape[0].getName());
		switch(level){
		case 1:
			System.out.println(taskList.get(0).taskQ + " " + taskShape[0].getName());
			break;
		case 2:
			System.out.println(taskList.get(1).taskQ + " " + taskShape[0].getName());
			break;
		case 3:
			System.out.println(taskList.get(2).taskQ + " " + taskShape[0].getName());
			System.out.println(taskList.get(2).taskQ2 + " " + taskShape[1].getName());
			break;
		}
	}
	
	public void getTask(int level, Shape s1 , Shape s2){
		System.out.println(taskList.get(2).taskQ + " " + s1.getName());
		System.out.println(taskList.get(2).taskQ2 + " " + s2.getName());
	}
	
	
}
