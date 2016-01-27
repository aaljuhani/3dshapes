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
		
		shapeList.add(new Shape("0x111111", "Cube", "This is a cube" , "cube"));
		shapeList.add(new Shape("0x222222", "Square Pyramid", "This is a Square Pyramid", "Square Pyramid"));
		shapeList.add(new Shape("0x333333", "Cylinder", "This is a Cylinder", "cylinder"));
	shapeList.add(new Shape("0x444444", "Triangular prism", "This is a Triangular prism", "Triangular prism"));
		shapeList.add(new Shape("0x555555", "Triangle", "This is a Triangle", "triangle"));
		shapeList.add(new Shape("0x666666", "Square", "This is a Square", "square"));
		shapeList.add(new Shape("0x777777", "Rhombus", "This is a Rhombus", "rhombus"));
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
	
	public String getAud(String id) {
		// TODO Auto-generated method stub
		for (Shape s: shapeList){
			if (id.startsWith(s.uId)){
				return s.audio;
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
	


	public Shape[] getRandomShapes(int level) {
		System.out.println("random shapes");
		//getting random shape object from shapeList Array
		Shape[] twoShapes = new Shape[2];
		int listSize = shapeList.size();
		int num1;
		int num2;
		if (level != 2 ){
		 num1 = new Random().nextInt(listSize);
		do{
			num2 = new Random().nextInt(listSize);
		} while (num1 == num2);
		} else {
			 num1 = new Random().nextInt(2) + 4;
			
			do{
				num2 = new Random().nextInt(listSize);
			} while (num1 == num2 );
		}
		System.out.println(num1);
		System.out.println(num2);
		System.out.println(level);
		
		
		twoShapes[0] = shapeList.get(num1);
		twoShapes[1] = shapeList.get(num2);
		
		return twoShapes;
	}
	
				
		

}
