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
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_page);
		// Show the Up button in the action bar.
		setupActionBar();
		calculatePercentage();
	}

	private void calculatePercentage() {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		int done = 0;
		if(!prefs.getString("ArmRotation", "Not Done").equals("Not Done")) {
			done++;
        }
        if(!prefs.getString("ClawStretch", "Not Done").equals("Not Done")) {
        	done++;
        }
        if(!prefs.getString("ElbowExtension", "Not Done").equals("Not Done")) {
        	done++;
        }
        if(!prefs.getString("FingerGrip", "Not Done").equals("Not Done")) {
        	done++;
        }
        if(!prefs.getString("FingerLift", "Not Done").equals("Not Done")) {
        	done++;
        }
        if(!prefs.getString("RadialDeviation", "Not Done").equals("Not Done")) {
        	done++;
        }
        if(!prefs.getString("ThumbStretch", "Not Done").equals("Not Done")) {
        	done++;
        }
        if(!prefs.getString("WristFlex", "Not Done").equals("Not Done")) {
        	done++;
        }

        TextView view = (TextView) findViewById(R.id.progressPercentage);
        DecimalFormat df = new DecimalFormat("#.##");
        view.setText(df.format(done/8.0*100) + "%");

        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
        bar.setProgress((int) (done/8.0*100.0));
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
		getMenuInflater().inflate(R.menu.progress_page, menu);
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

	public void goToDetailsPage(View view) {
		Intent intent = new Intent(this, DetailsPage.class);
		startActivity(intent);
	}
	public void goToHomePage(View view) {
		Intent intent = new Intent(this, HomePage.class);
		startActivity(intent);
	}
}
