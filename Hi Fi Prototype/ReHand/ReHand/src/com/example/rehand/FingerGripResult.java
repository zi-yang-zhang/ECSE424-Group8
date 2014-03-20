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

public class FingerGripResult extends Activity {

	double attempt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		//prefs.edit().putString("ElbowExtension", "Done").commit();
		DecimalFormat f = new DecimalFormat("##.##");
		boolean gettingStarted = prefs.getBoolean("fingerGripFirstTime", true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finger_grip_result);
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
			testTypeText.setText("You Have Completed Finger Grip Exercise!");
		}
		socreText.setText("Your Score: "+String.valueOf(f.format(attempt)) +" degrees!");
		
		
		//ExerciseResult fingerGripResult = new ExerciseResult(3,"Finger Grip",200, Double.parseDouble(score));
		/*if(started ==1){
			
		}
		*/
		//db.deleteResult(fingerGripResult);
		//System.out.println(db.getResult(3).toString());
		//db.addResult(result);
		//db.deleteResult(result);
		//System.out.println(started);
		//System.out.println(result.toString());
		//System.out.println(db.getResult(2).toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finger_grip_result, menu);
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
	       		prefs.edit().putString("fingerGrip", "Done").commit();
	       		ScoreDatabaseHelper db = new ScoreDatabaseHelper(getBaseContext());
	       		boolean gettingStarted = prefs.getBoolean("fingerGripFirstTime", true);
	       		if(gettingStarted){
	    			//create new entry and save in database
	    			ExerciseResult fingerGripResult = new ExerciseResult(6,"Finger Grip",attempt,(attempt+300));
	    			db.addResult(fingerGripResult);
	    			prefs.edit().putBoolean("fingerGripFirstTime", false).commit();
	    		}else{
	    			ExerciseResult fingerGripResult = db.getResult(6);
	    			fingerGripResult.updatePersonalBest(attempt);
	    			fingerGripResult.updateScores(attempt);
	    			fingerGripResult.updateCurrentProgress();
	    			db.updateResult(fingerGripResult);
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
