package main;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

import sun.audio.*;

public class Shape {
	/*
	 * shape attributes
	 *  	name
	 *  	description
	 *  	
	 */
	// a list that contain all shapes with its name and description
	private static ArrayList<Shape> shapeList = new ArrayList<Shape> ();
	private String uId;
	private String sname;
	private String desc;
	private String audio;
	
	public Shape(){
		System.out.println("we are in shape");
		//for test using simulator
		/*shapeList.add(new Shape("0x2F3", "Cube", "This is a cube" , "aud/cube.au"));
		shapeList.add(new Shape("0xAF8", "Square Pyramid", "This is a Square Pyramid", "aud/sphere.au"));*/
		
		
		shapeList.add(new Shape("0x111111", "Cube", "This is a cube" , "aud/cube.au"));
		shapeList.add(new Shape("0x222222", "Traiangle", "This is a Square Pyramid", "aud/sphere.au"));
		shapeList.add(new Shape("0x333333", "PRISM", "This is a HEXAGONAL PRISM", "aud/sphere.au"));
		//rshapeList.add(new Shape("0x9581B8918AFE", "Octahedron", "This is an Octahedron", "aud/sphere.au"));
		
	}
	
	public Shape(String id, String name, String descreption, String aud){
		System.out.println("we are in shape constructor");
		this.uId = id;
		this.sname = name;
		this.desc = descreption;
		this.audio = aud;
	}
	
	public static boolean isShape(String id){
		for (Shape s: shapeList){
			if (s.uId.startsWith(id))
				return true;
		}
		return false;
		
	}

	public String getName(String id) {
		// TODO Auto-generated method stub
		for (Shape s: shapeList){
			if (id.startsWith(s.uId)){
				playAudio(s.audio);
				return s.sname;
			}
				
		}
		return null;
	
		
	}
	
	public String getName(){
		return this.sname;
	}
	
	public String getUID(){
		return this.uId;
	}

	public String getDesc(String id) {
		// TODO Auto-generated method stub
		for (Shape s: shapeList){
			if (id.startsWith(s.uId))
				return s.desc;
		}
		return null;
	}
	
	public void playAudio(String audFile){
		InputStream in = null;
		 AudioStream audioStream = null;
					//open the sound file as a Java input Stream
					
					try {
						in = new FileInputStream(audFile);
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
				
				
			}

	public Shape getRandomShape() {
		//getting random shape object from shapeList Array
		return shapeList.get(new Random().nextInt(shapeList.size()));
	}
				
		

}
