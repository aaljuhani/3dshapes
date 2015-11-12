package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import lusidOSC.LusidObject;

public class Task {

	private ArrayList<Task> taskList = new ArrayList<Task>();
	private ArrayList<Task> subTaskList = new ArrayList<Task>();
	private String taskQ;
	private String taskAudio;
	
	private String taskQ2;
	private String taskAudio2;
	
	//game state before subTask
	ArrayList<LusidObject> currlusidArr = new ArrayList<LusidObject>();
	
	//Audio variables
	public static InputStream in ;
	public static AudioStream audioStream ;
	public boolean playCompleted;
	
	
	
	public Task(){
		
		taskList.add(new Task("Where is the", "audiofile" ));
		taskList.add(new Task("Place two shape of", "audiofile" ));
		taskList.add(new Task("Place a shape of", "audiofile" , "then a shape of", "audiofile"));

		subTaskList.add(new Task("move the two object towards each other", "audio file"));
		subTaskList.add(new Task("move the two object away from each other", "audio file"));
			
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
			View.setTask("TASK: " + taskList.get(0).taskQ + " " + taskShape[0].getName());
			playAudio("task1");
			playAudio(taskShape[0].getName());
			break;
		case 2:
			System.out.println(taskList.get(1).taskQ + " " + taskShape[0].getName());
			View.setTask("TASK: " + taskList.get(1).taskQ + " " + taskShape[0].getName());
			playAudio("task2");
			playAudio(taskShape[0].getName());
			break;
		case 3:
			System.out.println(taskList.get(2).taskQ + " " + taskShape[0].getName());
			System.out.println(taskList.get(2).taskQ2 + " " + taskShape[1].getName());
			View.setTask("TASK: " + taskList.get(2).taskQ + " " + taskShape[0].getName() + " " + taskList.get(2).taskQ2 + " " + taskShape[1].getName());
			playAudio("task3-1");
			playAudio(taskShape[0].getName());
			playAudio("task3-2");
			playAudio(taskShape[1].getName());
			break;
		}
	}
	
	
	public int getSubTask(Shape[] currentShape){
		int randomSubTask = new Random().nextInt(subTaskList.size())+ 1;
		System.out.println(randomSubTask);
		switch(1){
		case 1:
			System.out.println("move "+ currentShape[0].getName() + " and "+ currentShape[1].getName() +" towards each other");
			View.setTask("TASK: " + "move "+ currentShape[0].getName() + " and "+ currentShape[1].getName() +" towards each other");
			break;
		case 2:
			System.out.println("move "+ currentShape[0].getName() + " and "+ currentShape[1].getName() +" away each other");
			View.setTask("TASK: " + "move "+ currentShape[0].getName() + " and "+ currentShape[1].getName() +" away each other");
			break;
		}
		
		
		
		return randomSubTask;
	}
	
	public static void playAudio(String audFile){
		System.out.println(AudioPlayer.player.isAlive());

		//open the sound file as a Java input Stream

		try {
		in = new FileInputStream("aud/"+audFile.toLowerCase()+".au");
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		// create an audiostream from the inputstream
		try {
		audioStream = new AudioStream(in);

		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		// play the audio clip with the audioplayer class
		AudioPlayer.player.start(audioStream);
		try {
			Thread.sleep(audioStream.getLength()/100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		}
	
	
}
