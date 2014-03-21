package com.example.rehand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ElbowExtensionPage extends Activity {
	boolean tutorial = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elbow_extension_page);
		// Show the Up button in the action bar.
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
		getMenuInflater().inflate(R.menu.elbow_extension_page, menu);
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

	public void goToExercisePage(View view) {
        Intent exerciseWithOutTutorial = new Intent(this, ElbowExtensionExercise.class);
        startActivity(exerciseWithOutTutorial);
	}

	public void viewTutorial(View view) {
		Intent exerciseWithTutorial = new Intent(this, ElbowExtensionTutorialPage.class);
        startActivity(exerciseWithTutorial);
	}
	
	public void goBack(View view) {
		Intent exercises = new Intent(this, ExerciseListPage.class);
        startActivity(exercises);
	}
}
