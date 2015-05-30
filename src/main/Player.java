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
	
	public Player(){
		this.level = 1;
		this.score = 1;
		this.correctAnswer = 1;
		this.wrongAnswer = 1;
		this.totalAnswer = 0;
		
		
	}
	
	public int getlevel(){
		return this.level;
	}
	
	public void answerCorrect(){
		this.correctAnswer++;
		this.totalAnswer++;
		this.calculateScore();
		}
	
	public void answerWrong(){
		this.wrongAnswer++;
		this.totalAnswer++;
		this.calculateScore();
	}
	
	public void calculateScore(){
		this.score = this.correctAnswer * this.score / this.totalAnswer;
		System.out.println("score "+ this.score);
		System.out.println("Total q :"+ this.totalAnswer);
		if(this.totalAnswer > 5)
			this.incLevel();
	}
	
	public void incLevel(){
		this.level += 1;
	}

}
