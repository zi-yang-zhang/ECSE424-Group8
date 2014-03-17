package com.example.rehand;

public class ExerciseResult {

    private int id;
    private String name;
    private double benchmark;
    private double maximumMark;
    private double currentProgress;
    private double currentScore;
    private double previousScore;
    private double beforePreviousScore;
    private double personalBest;


    public ExerciseResult(){}
    
    public ExerciseResult(int id, String name, double benchmark, double maximumMark) {
    	this.id = id;
    	this.name = name;
        this.benchmark = benchmark;
        this.maximumMark = maximumMark;
    }
    // getter
    public int getId(){
    	return this.id;
    }
    public String getName(){
    	return this.name;
    }
    public double getBenchmark() {
        return this.benchmark;
    }
    public double getMaximumMark(){
    	return this.maximumMark;
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
    public double getPersonalBest(){
    	return this.personalBest;
    }
    // setter
    public void setId(int id){
    	this.id = id;
    }
    public void setName(String name){
    	this.name = name;
    }
    public void setBenchmark(double benchmark) {
        this.benchmark = benchmark;
    }
    public void setMaximumMark(double maximumMark){
    	this.maximumMark = maximumMark;
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
    public void setPersonalBest(double personalBest){
    	this.personalBest = personalBest;
    }
    
    
    @Override
    public String toString() {
        return "Result [id=" + id 
        		+ ", name=" + name 
        		+ ", benchmark=" + benchmark 
        		+ ", maximum mark=" + maximumMark 
        		+ ", current progress=" + currentProgress 
        		+ ", current score=" + currentScore 
        		+ ", previous score=" + previousScore 
        		+ ", before previous score=" + beforePreviousScore
        		+ ", person best=" + personalBest
        		+ "]";
    }
}
