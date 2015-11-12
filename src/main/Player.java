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
	private double score;
	private int correctAnswer;
	private int totalAnswer;
	private int wrongAnswer;
	private int chances;
	
	public Player(){
		this.level = 1;
		this.score = 0;
		this.correctAnswer = 1;
		this.wrongAnswer = 1;
		this.chances = 1;
		this.totalAnswer = 0;
		
		
	}
	
	public int getlevel(){
		return this.level;
	}
	
	public void answerCorrect(){
		this.correctAnswer++;
		this.totalAnswer++;
		this.score = this.score + 1.0/this.chances;
		this.chances = 1; //reset chances
		this.calculateScore();
		}
	
	public void answerWrong(){
		this.wrongAnswer++;
		this.totalAnswer++; 
		this.chances++;
		this.calculateScore();
	}
	
	public void calculateScore(){
		//this.score = this.correctAnswer * this.score / this.totalAnswer;  // GPA way didn't work prorperly
		
		System.out.println("score "+ this.score);
		View.setScore(""+Math.floor(this.score));
		System.out.println("Total q :"+ this.totalAnswer);
		
		this.setLevel(((int)this.score/10 == 0)? 1:(int)this.score/10 );
		
	}
	
	public void setLevel(int l){
		this.level = l;
		View.setLevel(""+l);
	}
	

	
}
