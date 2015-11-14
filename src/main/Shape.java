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
	//private int prevNum = -1; 
	
	public Shape(){
		System.out.println("we are in shape");
		//for test using simulator
		/*shapeList.add(new Shape("0x2F3", "Cube", "This is a cube" , "aud/cube.au"));
		shapeList.add(new Shape("0xAF8", "Square Pyramid", "This is a Square Pyramid", "aud/sphere.au"));*/
		
		
		shapeList.add(new Shape("0x111111", "Square", "This is a cube" , "aud/cube.au"));
		shapeList.add(new Shape("0x222222", "Traiangle", "This is a Square Pyramid", "aud/sphere.au"));
		shapeList.add(new Shape("0x333333", "Rhombus", "This is a HEXAGONAL PRISM", "aud/sphere.au"));
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
				//playAudio(s.audio);
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
	


	public Shape[] getRandomShapes() {
		System.out.println("random shapes");
		//getting random shape object from shapeList Array
		Shape[] twoShapes = new Shape[2];
		int listSize = shapeList.size();
		int num1 = new Random().nextInt(listSize);
		int num2;
		do{
			num2 = new Random().nextInt(listSize);
		} while (num1 == num2);
		
		twoShapes[0] = shapeList.get(num1);
		twoShapes[1] = shapeList.get(num2);
		
		return twoShapes;
	}
	
				
		

}
