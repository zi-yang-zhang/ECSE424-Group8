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

public class HomePage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
/*		if(prefs.getInt("GettingStartedFirstTime", 0) != 0) {
			Intent intent = new Intent(this, GettingStartedPage.class);
	        startActivity(intent);
		}*/
		super.onCreate(savedInstanceState);
		if(prefs.getInt("FirstTime", 0) == 0) {
			//First time opening app
			setContentView(R.layout.activity_startup_page);
		} else if(prefs.getInt("GettingStartedFirstTime", 0) == 0) {
			//First time opening app
			setContentView(R.layout.activity_getting_started_page);
		} else {
			setContentView(R.layout.activity_home_page);
		}
		setupActionBar();
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
		getMenuInflater().inflate(R.menu.home_page, menu);
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

	public void goToExerciseListPage(View view){
		// Do something in response to button
		Intent intent = new Intent(this, ExerciseListPage.class);
		startActivity(intent);
	}

	public void goToProgressPage(View view){
		// Do something in response to button
		Intent intent = new Intent(this, ProgressPage.class);
		startActivity(intent);
	}

    public void gettingStartedPositive(View view) {
        SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
        prefs.edit().putInt("GettingStartedFirstTime", 1).commit();

        Intent intent = new Intent(this, ExerciseListPage.class);
        startActivity(intent);
    }

    public void gettingStartedNegative(View view) {
        SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
        prefs.edit().putInt("GettingStartedFirstTime", 2).commit();

        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

	public void close(View view) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		prefs.edit().putInt("FirstTime", 1).commit();

		Intent intent = new Intent(this, HomePage.class);
		startActivity(intent);
	}
}
