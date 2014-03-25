package com.example.rehand;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class FingerLiftResult extends Activity {
	private String attemptText;
	private double attempt;
	private String indexText;
	private double index;
	private String middleText;
	private double middle;
	private String ringText;
	private double ring;
	private String littleText;
	private double little;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		boolean gettingStarted = prefs.getBoolean("fingerLiftFirstTime", true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finger_lift_result);
		DecimalFormat f = new DecimalFormat("##.##");
		final TextView socreText = (TextView) findViewById(R.id.socreText);
		final TextView indexScore = (TextView) findViewById(R.id.indexScore);
		final TextView middleScore = (TextView) findViewById(R.id.middleScore);
		final TextView ringScore = (TextView) findViewById(R.id.ringScore);
		final TextView littleScore = (TextView) findViewById(R.id.littleScore);
		final TextView testTypeText = (TextView) findViewById(R.id.testTypeText);
		attemptText = this.getIntent().getStringExtra("score");
		indexText = this.getIntent().getStringExtra("scoreIndexFinger");
		middleText = this.getIntent().getStringExtra("scoreMiddleFinger");
		ringText = this.getIntent().getStringExtra("scoreRingFinger");
		littleText = this.getIntent().getStringExtra("scoreLittleFinger");
		//getting started
		if(gettingStarted){
			testTypeText.setText("You Have Completed Bench Mark Test!");
		}else{
			testTypeText.setText("You Have Completed Finger Lift Exercise!");
		}
		if(attemptText.equalsIgnoreCase("Pass")){
			attempt = 15;
			socreText.setText("Pass");
		}else{
			attempt = Double.parseDouble(attemptText);
			socreText.setText(String.valueOf(f.format(attempt))+" seconds");
		}
		
		if(indexText.equalsIgnoreCase("Pass")){
			indexScore.setText("Pass");
		}else{
			index = Double.parseDouble(indexText);
			indexScore.setText(String.valueOf(f.format(index))+" seconds");
		}
		
		if(middleText.equalsIgnoreCase("Pass")){
			middleScore.setText("Pass");
		}else{
			middle = Double.parseDouble(middleText);
			middleScore.setText(String.valueOf(f.format(middle))+" seconds");
		}
		
		if(ringText.equalsIgnoreCase("Pass")){
			ringScore.setText("Pass");
		}else{
			ring = Double.parseDouble(ringText);
			ringScore.setText(String.valueOf(f.format(ring))+" seconds");
		}
		
		if(littleText.equalsIgnoreCase("Pass")){
			littleScore.setText("Pass");
		}else{
			little = Double.parseDouble(littleText);
			littleScore.setText(String.valueOf(f.format(little))+" seconds");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finger_lift_result, menu);
		return true;
	}

	public void goToExercisesPage(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.result_page_warning)
			   .setTitle("Saving Result");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User clicked OK button
	        	SharedPreferences prefs = getBaseContext().getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
	       		prefs.edit().putString("FingerLift", "Done").commit();
	       		ScoreDatabaseHelper db = new ScoreDatabaseHelper(getBaseContext());
	       		boolean gettingStarted = prefs.getBoolean("fingerLiftFirstTime", true);
	       		if(gettingStarted){
	    			//create new entry and save in database
	       			
	    			ExerciseResult fingerLiftResult = new ExerciseResult(2,"Finger Lift",attempt,15);
	    			if(attempt==15){
	    				fingerLiftResult.setLevel(3);
	    				fingerLiftResult.setCurrentProgress(100);
	    				fingerLiftResult.setCurrentScore(100);
	       			}
	    			db.addResult(fingerLiftResult);
	    			prefs.edit().putBoolean("fingerLiftFirstTime", false).commit();
	    		}else{
	    			ExerciseResult fingerLiftResult = db.getResult(2);
	    			fingerLiftResult.updatePersonalBest(attempt);
	    			fingerLiftResult.updateScores(attempt);
	    			fingerLiftResult.updateCurrentProgress();
	    			db.updateResult(fingerLiftResult);
	    		}
	       		Intent intent = new Intent(getBaseContext(), ExerciseListPage.class);
	       		startActivity(intent);
	        	   
	           }
	       });
	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   dialog.cancel();

	       		Intent intent = new Intent(getBaseContext(), ExerciseListPage.class);
	       		startActivity(intent);
	        	   }
	       });
		builder.create().show();
	}
}
