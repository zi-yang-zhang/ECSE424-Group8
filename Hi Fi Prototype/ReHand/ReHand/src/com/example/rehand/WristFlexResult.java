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

public class WristFlexResult extends Activity {

	double attempt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		//prefs.edit().putString("wristFlex", "Done").commit();
		DecimalFormat f = new DecimalFormat("##.##");
		boolean gettingStarted = prefs.getBoolean("wristFlexFirstTime", true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wrist_flex_result);
		final TextView socreText = (TextView) findViewById(R.id.socreText);
		final TextView testTypeText = (TextView) findViewById(R.id.testTypeText);
		String attemptText = this.getIntent().getStringExtra("score");
		attempt = Double.parseDouble(attemptText);
		//getting started
		if(gettingStarted){
			//create new entry and save in database
			testTypeText.setText("You Have Completed the Baseline Test!");
		}else{
			//double score = wristFlexResult.getCurrentScore();
			testTypeText.setText("You Have Completed Wrist Flex Exercise!");
		}
		socreText.setText("Your Score: "+String.valueOf(f.format(attempt)) +"\u00b0!");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wrist_flex_result, menu);
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
	       		prefs.edit().putString("WristFlex", "Done").commit();
	       		ScoreDatabaseHelper db = new ScoreDatabaseHelper(getBaseContext());
	       		boolean gettingStarted = prefs.getBoolean("wristFlexFirstTime", true);
	       		if(gettingStarted){
	    			//create new entry and save in database
	    			ExerciseResult wristFlexResult = new ExerciseResult(4,"Wrist Flex",attempt,(attempt+300));
	    			db.addResult(wristFlexResult);
	    			prefs.edit().putBoolean("wristFlexFirstTime", false).commit();
	    		}else{
	    			ExerciseResult wristFlexResult = db.getResult(4);
	    			wristFlexResult.updatePersonalBest(attempt);
	    			wristFlexResult.updateScores(attempt);
	    			wristFlexResult.updateCurrentProgress();
	    			db.updateResult(wristFlexResult);
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
