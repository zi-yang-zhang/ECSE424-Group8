package com.example.rehand;

public class ExerciseResult {

    private int id;
    private double benchmark;
    private double currentProgress;
    private double currentScore;
    private double previousScore;
    private double beforePreviousScore;


    public ExerciseResult(){}
    
    public ExerciseResult(double benchmark) {
        super();
        this.benchmark = benchmark;
        this.currentProgress=0;
        this.currentScore=0;
        this.previousScore=0;
        this.beforePreviousScore=0;
    }
    public int getId(){
    	return this.id;
    }
    public double getBenchmark() {
        return this.benchmark;
    }
    public double getCurrentProgress() {
        return this.currentProgress;
    }
    public double getCurrentScore() {
        return this.currentScore;
    }
    public double getPreviousScore() {
        return this.previousScore;
    }
    public double getBeforePreviousScore() {
        return this.beforePreviousScore;
    }
    public void setId(int id){
    	this.id = id;
    }
    public void setBenchmark(double benchmark) {
        this.benchmark = benchmark;
    }
    public void setCurrentProgress(double currentProgress) {
        this.currentProgress = currentProgress;
    }
    public void setCurrentScore(double currentScore) {
        this.currentScore = currentScore;
    }
    public void setPreviousScore(double previousScore) {
        this.previousScore = previousScore;
    }
    public void setBeforePreviousScore(double beforePreviousScore) {
        this.beforePreviousScore = beforePreviousScore;
    }
    
    
 
}
