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

public class ThumbStretchResult extends Activity {

	String attemptText;
	double attempt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thumb_stretch_result);
		boolean gettingStarted = prefs.getBoolean("thumbStretchFirstTime", true);
		DecimalFormat f = new DecimalFormat("##.##");
		final TextView socreText = (TextView) findViewById(R.id.socreText);
		final TextView testTypeText = (TextView) findViewById(R.id.testTypeText);
		attemptText = this.getIntent().getStringExtra("score");
		attempt = Double.parseDouble(attemptText);
		//getting started
		if(gettingStarted){
			//create new entry and save in database
			testTypeText.setText("You Have Completed Bench Mark Test!");
			socreText.setText("Your Score: "+String.valueOf(f.format(attempt)));
		}else{
			testTypeText.setText("You Have Completed Finger Lift Exercise!");
			socreText.setText("Your Score: "+String.valueOf(f.format(attempt)));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thumb_stretch_result, menu);
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
	       		prefs.edit().putString("ThumbStretch", "Done").commit();
	       		ScoreDatabaseHelper db = new ScoreDatabaseHelper(getBaseContext());
	       		boolean gettingStarted = prefs.getBoolean("thumbStretchFirstTime", true);
	       		if(gettingStarted){
	    			//create new entry and save in database
	    			ExerciseResult thumbStretchResult = new ExerciseResult(6,"Thumb Stretch",attempt,(attempt+300));
	    			db.addResult(thumbStretchResult);
	    			prefs.edit().putBoolean("thumbStretchFirstTime", false).commit();
	    		}else{
	    			ExerciseResult thumbStretchResult = db.getResult(6);
	    			thumbStretchResult.updatePersonalBest(attempt);
	    			thumbStretchResult.updateScores(attempt);
	    			thumbStretchResult.updateCurrentProgress();
	    			db.updateResult(thumbStretchResult);
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
