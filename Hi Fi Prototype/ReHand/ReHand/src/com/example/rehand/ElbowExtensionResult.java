package com.example.rehand;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ElbowExtensionResult extends Activity {

	double attempt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		//prefs.edit().putString("ElbowExtension", "Done").commit();
		DecimalFormat f = new DecimalFormat("##.##");
		boolean gettingStarted = prefs.getBoolean("elbowExtensionFirstTime", true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elbow_extension_result);
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
			testTypeText.setText("You Have Completed Elbow Extension Exercise!");
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
		getMenuInflater().inflate(R.menu.elbow_extension_result, menu);
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
	       		prefs.edit().putString("elbowExtension", "Done").commit();
	       		ScoreDatabaseHelper db = new ScoreDatabaseHelper(getBaseContext());
	       		boolean gettingStarted = prefs.getBoolean("elbowExtensionFirstTime", true);
	       		if(gettingStarted){
	    			//create new entry and save in database
	    			ExerciseResult elbowExtensionResult = new ExerciseResult(2,"Elbow Extension",attempt,(attempt+300));
	    			db.addResult(elbowExtensionResult);
	    			prefs.edit().putBoolean("elbowExtensionFirstTime", false).commit();
	    		}else{
	    			ExerciseResult elbowExtensionResult = db.getResult(2);
	    			elbowExtensionResult.updatePersonalBest(attempt);
	    			elbowExtensionResult.updateScores(attempt);
	    			elbowExtensionResult.updateCurrentProgress();
	    			db.updateResult(elbowExtensionResult);
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
