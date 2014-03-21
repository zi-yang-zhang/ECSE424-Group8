package com.example.rehand;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DetailsPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_page);
		// Show the Up button in the action bar.
		setupActionBar();
		setUpProgress();
	}

	private void setUpProgress() {
		TextView view;
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		if(!prefs.getString("ArmRotation", "Not Done").equals("Not Done")) {
			view = (TextView) findViewById(R.id.ArmRotationProgress);
			view.setText("Done");
        }
        if(!prefs.getString("ElbowExtension", "Not Done").equals("Not Done")) {
        	view = (TextView) findViewById(R.id.ElbowExtensionProgress);
			view.setText("Done");
        }
        if(!prefs.getString("FingerGrip", "Not Done").equals("Not Done")) {
        	view = (TextView) findViewById(R.id.FingerGripProgress);
			view.setText("Done");
        }
        if(!prefs.getString("FingerLift", "Not Done").equals("Not Done")) {
        	view = (TextView) findViewById(R.id.FingerLiftProgress);
			view.setText("Done");
        }
        if(!prefs.getString("RadialDeviation", "Not Done").equals("Not Done")) {
        	view = (TextView) findViewById(R.id.RadialDeviationProgress);
			view.setText("Done");
        }
        if(!prefs.getString("ThumbStretch", "Not Done").equals("Not Done")) {
        	view = (TextView) findViewById(R.id.ThumbStretchProgress);
			view.setText("Done");
        }
        if(!prefs.getString("WristFlex", "Not Done").equals("Not Done")) {
        	view = (TextView) findViewById(R.id.WristFlexProgress);
			view.setText("Done");
        }
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
		getMenuInflater().inflate(R.menu.details_page, menu);
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
	public void goToArmRotationProgressDetailPage(View view){		
		Intent intent = new Intent(this, ArmRotationProgressDetailPage.class);
		startActivity(intent);
	}

	public void goToElbowExtentionProgressDetailPage(View view){		
		Intent intent = new Intent(this, ElbowExtensionProgressDetailPage.class);
		startActivity(intent);
	}

	public void goToFingerGripProgressDetailPage(View view){		
		Intent intent = new Intent(this, FingerGripProgressDetailPage.class);
		startActivity(intent);
	}

	public void goToFingerLiftProgressDetailPage(View view){		
		Intent intent = new Intent(this, FingerLiftProgressDetailPage.class);
		startActivity(intent);
	}

	public void goToRadialDeviationProgressDetailPage(View view){		
		Intent intent = new Intent(this, RadialDeviationProgressDetailPage.class);
		startActivity(intent);
	}

	public void goToThumbStretchProgressDetailPage(View view){		
		Intent intent = new Intent(this, ThumbStretchProgressDetailPage.class);
		startActivity(intent);
	}

	public void goToWristFlexProgressDetailPage(View view){		
		Intent intent = new Intent(this, WristFlexProgressDetailPage.class);
		startActivity(intent);
	}
	public void goToProgressPage(View view){		
		Intent intent = new Intent(this, ProgressPage.class);
		startActivity(intent);
	}
}
