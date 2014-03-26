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

public class ArmRotationResult extends Activity {

	double attempt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		DecimalFormat f = new DecimalFormat("##.##");
		boolean gettingStarted = prefs.getBoolean("armRotationFirstTime", true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arm_rotation_result);
		final TextView socreText = (TextView) findViewById(R.id.socreText);
		final TextView testTypeText = (TextView) findViewById(R.id.testTypeText);
		String attemptText = this.getIntent().getStringExtra("score");
		attempt = Double.parseDouble(attemptText);
		//getting started
		if(gettingStarted){
			//create new entry and save in database
			testTypeText.setText("You Have Completed Bench Mark Test!");
		}else{
			//double score = wristFlexResult.getCurrentScore();
			testTypeText.setText("You Have Completed Arm Rotation Exercise!");
		}
		socreText.setText("Your Score: "+String.valueOf(f.format(attempt)) +" degrees!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.arm_rotation_result, menu);
		return true;
	}

	public void goToExercisesPage(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.result_page_warning)
			   .setTitle("Saving Result");
		//save the result
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	SharedPreferences prefs = getBaseContext().getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
	       		prefs.edit().putString("ArmRotation", "Done").commit();
	       		ScoreDatabaseHelper db = new ScoreDatabaseHelper(getBaseContext());
	       		boolean gettingStarted = prefs.getBoolean("armRotationFirstTime", true);
	       		if(gettingStarted){
	    			//create new entry and save in database
	    			ExerciseResult armRotationResult = new ExerciseResult(1,"Arm Rotation",attempt,(attempt+300));
	    			db.addResult(armRotationResult);
	    			prefs.edit().putBoolean("armRotationFirstTime", false).commit();
	    		}else{
	    			//update the database
	    			ExerciseResult armRotationResult = db.getResult(1);
	    			armRotationResult.updatePersonalBest(attempt);
	    			armRotationResult.updateScores(attempt);
	    			armRotationResult.updateCurrentProgress();
	    			db.updateResult(armRotationResult);
	    		}
	       		Intent intent = new Intent(getBaseContext(), ExerciseListPage.class);
	       		startActivity(intent);
	        	   
	           }
	       });
		//Discard the result
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
