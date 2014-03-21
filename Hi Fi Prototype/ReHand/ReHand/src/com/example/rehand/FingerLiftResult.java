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
	String attemptText;
	double attempt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		ScoreDatabaseHelper db = new ScoreDatabaseHelper(this);
		boolean gettingStarted = prefs.getBoolean("fingerLiftFirstTime", true);
		super.onCreate(savedInstanceState);
		DecimalFormat f = new DecimalFormat("##.##");
		setContentView(R.layout.activity_finger_lift_result);
		final TextView socreText = (TextView) findViewById(R.id.socreText);
		final TextView testTypeText = (TextView) findViewById(R.id.testTypeText);
		attemptText = this.getIntent().getStringExtra("score");
		attempt = Double.parseDouble(attemptText);
		//getting started
		if(gettingStarted){
			//create new entry and save in database
			testTypeText.setText("You Have Completed Bench Mark Test!");
			socreText.setText("Your Score: "+String.valueOf(f.format(attempt))+" seconds!");
		}else{
			testTypeText.setText("You Have Completed Finger Lift Exercise!");
			socreText.setText("Your Score: "+String.valueOf(f.format(attempt))+" seconds!");
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
	    			ExerciseResult fingerLiftResult = new ExerciseResult(4,"Finger Lift",attempt,(attempt+9));
	    			db.addResult(fingerLiftResult);
	    			prefs.edit().putBoolean("fingerLiftFirstTime", false).commit();
	    		}else{
	    			ExerciseResult fingerLiftResult = db.getResult(4);
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
