package com.example.rehand;

public class ExerciseResult {
	private final int NUMBEROFLEVEL = 3;
    private int id;
    private String name;
    private double benchmark;
    private double maximumMark;
    private double currentProgress;
    private double currentScore;
    private double previousScore;
    private double beforePreviousScore;
    private double personalBest;
    private int level;


    public ExerciseResult(){
    	this.level = 1;
    }
    /**
     * @param id id of the exercise result in database
     * @param name name of the exercise result in database
     * @param benchmark   benchmark of the exercise result in database
     * @param maximumMark  maximumMark of the exercise result in database
     */
    public ExerciseResult(int id, String name, double benchmark, double maximumMark) {
    	this.id = id;
    	this.name = name;
        this.benchmark = benchmark;
        this.maximumMark = maximumMark;
        this.currentScore = 100*(benchmark 
							- (benchmark - (maximumMark - benchmark)/NUMBEROFLEVEL))
							/(((maximumMark - benchmark)/NUMBEROFLEVEL)*2);
        this.currentProgress = 100*(benchmark 
        					- (benchmark - (maximumMark - benchmark)/NUMBEROFLEVEL))
        					/(((maximumMark - benchmark)/NUMBEROFLEVEL)*2);
        this.personalBest = benchmark;	
        this.level = 1;
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
    public int getLevel(){
    	return this.level;
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
    public void setLevel(int level){
    	this.level = level;
    }
    
    // setter for result page
    public void updateScores(double attempt) {
      	if(previousScore == 0){
      		previousScore = currentScore;
    	}else{
    		beforePreviousScore = previousScore;
    		previousScore = currentScore;
    	}
        this.currentScore = 100*(attempt 
				- (benchmark - (maximumMark - benchmark)/NUMBEROFLEVEL))
				/(((maximumMark - benchmark)/NUMBEROFLEVEL)*2);
    }
    public void updateCurrentProgress() {
    	if(previousScore == 0){
    		currentProgress = currentScore;
    	}else if(beforePreviousScore == 0){
    		currentProgress = (currentScore + previousScore)/2;
    	}else{
    		currentProgress = (currentScore + previousScore+ beforePreviousScore)/3;
    	}
    	if(currentProgress >= 100){
    		level++;
    		currentProgress = 0;
    		benchmark = benchmark + (maximumMark - benchmark)/NUMBEROFLEVEL;
    	}
    }
    public void updatePersonalBest(double attempt) {
    	double score = 100*(attempt 
				- (benchmark - (maximumMark - benchmark)/NUMBEROFLEVEL))
				/(((maximumMark - benchmark)/NUMBEROFLEVEL)*2);
    	if(score > currentScore){
    		personalBest = score;
    	}
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
        		+ ", level=" + level
        		+ "]";
    }
}
