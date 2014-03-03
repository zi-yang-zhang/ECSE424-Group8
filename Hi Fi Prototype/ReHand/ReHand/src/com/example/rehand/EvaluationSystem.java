package com.example.rehand;

public class EvaluationSystem {
	private final int numberOfLevel = 3;
	private double benchmark;
	private double maximumMark;
	private int currentLevel;
	private double progressToNextLevel;
	
	public EvaluationSystem(double benchmark, double maximumMark){
		this.benchmark = benchmark;
		this.maximumMark = maximumMark;
		this.currentLevel = 1;
		this.progressToNextLevel = (maximumMark - benchmark)/numberOfLevel ;
	}
	
	public double score(double currentAttempt){
		double score;
		score = (currentAttempt-(this.benchmark - this.progressToNextLevel))/(2*(this.progressToNextLevel))*100;
		return score;
		
	}
	public void setLevel(boolean levelUp){
		if(levelUp){
			currentLevel++;
			this.benchmark = this.benchmark + this.progressToNextLevel;
		}
	}
	
	public double getMaximumMark(){
		return this.maximumMark;
	}
	public int getLevel(){
		return currentLevel;
	}
	
}
