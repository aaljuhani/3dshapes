package main;

public class Player {
	/*
	 * player attributes
	 * 		score
	 * 		correct answer
	 * 		total answer
	 * 		level
	 */
	private int level;
	public Player(){
		level = 0;
	}
	
	public void incLevel(){
		level += 1;
	}

}
