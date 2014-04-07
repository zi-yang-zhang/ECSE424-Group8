package com.example.rehand;

import java.text.DecimalFormat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ThumbStretchResult extends Activity {

	private String attemptText;
	double attempt;
	private String leftText;
	double left;
	private String rightText;
	double right;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thumb_stretch_result);
		boolean gettingStarted = prefs.getBoolean("thumbStretchFirstTime", true);
		/*
		Resources resources = this.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    System.out.println("xpid"+metrics.xdpi);
	    System.out.println("ypid"+metrics.ydpi);
	    System.out.println("heightPixels"+metrics.heightPixels);
	    System.out.println("widthPixels"+metrics.widthPixels);
	    System.out.println("width"+(metrics.widthPixels/metrics.xdpi));
	    System.out.println("height"+(metrics.heightPixels/metrics.ydpi));
	    */
		DecimalFormat f = new DecimalFormat("##.##");
		final TextView socreText = (TextView) findViewById(R.id.socreText);
		final TextView scoreLeft = (TextView) findViewById(R.id.scoreLeft);
		final TextView scoreRight = (TextView) findViewById(R.id.scoreRight);
		final TextView testTypeText = (TextView) findViewById(R.id.testTypeText);
		attemptText = this.getIntent().getStringExtra("score");
		attempt = Double.parseDouble(attemptText);
		leftText = this.getIntent().getStringExtra("scoreLeft");
		left = Double.parseDouble(leftText);
		rightText = this.getIntent().getStringExtra("scoreRight");
		right = Double.parseDouble(rightText);
		MediaPlayer done = MediaPlayer.create(getBaseContext(), R.drawable.done);
		done.start();
		//getting started
		if(gettingStarted){
			//create new entry and save in database
			testTypeText.setText("You Have Completed Baseline Test!");
			socreText.setText(String.valueOf(f.format(attempt))+"inches");
			scoreLeft.setText(String.valueOf(f.format(left))+"inches");
			scoreRight.setText(String.valueOf(f.format(right))+"inches");
		}else{
			testTypeText.setText("You Have Completed Finger Lift Exercise!");
			socreText.setText(String.valueOf(f.format(attempt))+"inches");
			scoreLeft.setText(String.valueOf(f.format(left))+"inches");
			scoreRight.setText(String.valueOf(f.format(right))+"inches");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thumb_stretch_result, menu);
		return true;
	}

	public void goToExercisesPage(View view){
		Resources resources = this.getResources();
	    final DisplayMetrics metrics = resources.getDisplayMetrics();
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
	    			ExerciseResult thumbStretchResult = new ExerciseResult(3,"Thumb Stretch",attempt,((metrics.heightPixels/metrics.ydpi)/2));
	    			db.addResult(thumbStretchResult);
	    			prefs.edit().putBoolean("thumbStretchFirstTime", false).commit();
	    		}else{
	    			ExerciseResult thumbStretchResult = db.getResult(3);
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
