package com.example.rehand;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ExerciseListPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise_list_page);
		// Show the Up button in the action bar.
		setupActionBar();
		setupExercises();
	}

	private void setupExercises() {
		Button b;
		int done = 0;
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		if(!prefs.getString("ArmRotation", "Not Done").equals("Not Done")) {
			b = (Button) findViewById(R.id.button1);
			b.setEnabled(false);
			done++;
        }
        if(!prefs.getString("FingerLift", "Not Done").equals("Not Done")) {
        	b = (Button) findViewById(R.id.button2);
			b.setEnabled(false);
			done++;
        }
        if(!prefs.getString("ThumbStretch", "Not Done").equals("Not Done")) {
        	b = (Button) findViewById(R.id.button3);
			b.setEnabled(false);
			done++;
        }
        if(!prefs.getString("WristFlex", "Not Done").equals("Not Done")) {
        	b = (Button) findViewById(R.id.button4);
			b.setEnabled(false);
			done++;
        }

        TextView view = (TextView) findViewById(R.id.progressPercentage);
        DecimalFormat df = new DecimalFormat("#.##");
        view.setText(df.format(done/4.0*100) + "%");

        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
        bar.setProgress((int) (done/4.0*100.0));
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exercise_list_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void goToArmRotationPage(View view){		
		Intent intent = new Intent(this, ArmRotationPage.class);
		startActivity(intent);
	}


	public void goToFingerLiftPage(View view){		
		Intent intent = new Intent(this, FingerLiftPage.class);
		startActivity(intent);
	}

	public void goToThumbStretchPage(View view){		
		Intent intent = new Intent(this, ThumbStretchPage.class);
		startActivity(intent);
	}

	public void goToWristFlexPage(View view){		
		Intent intent = new Intent(this, WristFlexPage.class);
		startActivity(intent);
	}
	public void goToHomePage(View view){		
		Intent intent = new Intent(this, HomePage.class);
		startActivity(intent);
	}
}
