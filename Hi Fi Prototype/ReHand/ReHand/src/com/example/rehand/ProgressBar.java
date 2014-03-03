package com.example.rehand;

public class ProgressBar {
	private double currentProgress;
	private int arrayControl;
	private double [] scores = new double[3];
	private boolean levelUp;
	//Initialize a new progress bar
	public ProgressBar(){
		this.currentProgress = 0;
		this.arrayControl = 0;
		for(int i=0;i<scores.length;i++){
			scores[i] = 0;
		}
		this.levelUp = false;
	}
	//Method for setting current progress bar
	public void setCurrentProgress(double score){
		scores[arrayControl] = score;
		if(scores[arrayControl] ==0){
			if(arrayControl == 0){
				this.currentProgress = scores[arrayControl];
			}else if(arrayControl == 1){
				this.currentProgress = (scores[arrayControl-1]+scores[arrayControl])/(arrayControl+1);
			}
		}
		this.currentProgress = (scores[arrayControl-2]+scores[arrayControl-1]+scores[arrayControl])/(arrayControl+1);
		if(arrayControl == 2){
			arrayControl =0;
		}else{
			arrayControl++;
		}
		if(currentProgress>=100){
			this.levelUp();
		}
	}
	
	public double getCurrentProgress(){
		return currentProgress;
	}
	
	//method for reset current progress while level up
	public void levelUp(){
		this.currentProgress = 0;
		this.arrayControl = 0;
		for(int i=0;i<scores.length;i++){
			scores[i] = 0;
		}
		this.levelUp = true;
	}
	
	public boolean getlevelUp(){
		return this.levelUp;
	}
	//reset the boolean indicator for level up
	public void resetLevelUp(){
		this.levelUp = false;
	}
}
