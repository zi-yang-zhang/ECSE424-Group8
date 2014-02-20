package com.example.rehand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

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

		getActionBar().setDisplayHomeAsUpEnabled(true);

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
	public void onCheckboxClicked(View view) {
	    // Is the view now checked?
	    boolean checked = ((CheckBox) view).isChecked();
	    
	    // Check which checkbox was clicked
	    switch(view.getId()) {
	        case R.id.elbow_extension_tutorial:
	            if (checked){
	            	this.tutorial = true;
	            }

	    }
	}
	public void goToExercisePage(View view){
		CheckBox check = (CheckBox) findViewById(R.id.elbow_extension_tutorial);
		boolean checked = check.isChecked();
        if (checked){
            Intent exerciseWithTutorial = new Intent(this, ElbowExtensionTutorialPage.class);
            startActivity(exerciseWithTutorial);
        }else{
            Intent exerciseWithOutTutorial = new Intent(this, ElbowExtensionExercise.class);
            startActivity(exerciseWithOutTutorial);
        }
	}

	public void goToSchedule(View view) {
		Intent intent = new Intent(this, SchedulePage.class);
		intent.putExtra("Exercise", 3);
		startActivity(intent);
	}
}
