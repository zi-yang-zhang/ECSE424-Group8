package com.example.rehand;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class FingerLiftResult extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		//prefs.edit().putString("FingerLift", "Done").commit();
		DecimalFormat f = new DecimalFormat("##.##");
		ScoreDatabaseHelper db = new ScoreDatabaseHelper(this);
		boolean gettingStarted = prefs.getBoolean("fingerLiftFirstTime", true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finger_lift_result);
		final TextView socreText = (TextView) findViewById(R.id.socreText);
		final TextView testTypeText = (TextView) findViewById(R.id.testTypeText);
		String attemptText = this.getIntent().getStringExtra("score");
		double attempt = Double.parseDouble(attemptText);
		//getting started
		if(gettingStarted){
			//create new entry and save in database
			//double score = 100*attempt/5;
			ExerciseResult fingerLiftResult = new ExerciseResult(4,"Finger Lift",attempt,(attempt+9));
			testTypeText.setText("You Have Completed Bench Mark Test!");
			socreText.setText("Your Score: "+String.valueOf(f.format(attempt)));
			db.addResult(fingerLiftResult);
			prefs.edit().putBoolean("fingerLiftFirstTime", false).commit();
		}else{
			//double score = 100*attempt/5;
			ExerciseResult fingerLiftResult = db.getResult(4);
			fingerLiftResult.updatePersonalBest(attempt);
			fingerLiftResult.updateScores(attempt);
			fingerLiftResult.updateCurrentProgress();
			testTypeText.setText("You Have Completed Finger Lift Exercise!");
			socreText.setText("Your Score: "+String.valueOf(f.format(attempt)));
			db.updateResult(fingerLiftResult);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finger_lift_result, menu);
		return true;
	}

	public void goToExercisesPage(View view){
		Intent intent = new Intent(this, ExerciseListPage.class);
		startActivity(intent);
	}
}
