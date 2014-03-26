package com.example.rehand;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class HomePage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = getBaseContext().getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		super.onCreate(savedInstanceState);
		if(prefs.getBoolean("GettingStartedFirstTime", true)==true){
			setContentView(R.layout.activity_getting_started_page);
		}else{
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
	   public void gettingStartedToExercise(View view) {
	    	final CheckBox notShow = (CheckBox)findViewById(R.id.notShow);
	    	if(notShow.isChecked()){
	    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    		builder.setMessage(R.string.warning_message_getting_started_to_exercise)
	    			   .setTitle("Warning");
	    		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	               // User clicked OK button
	    	       		SharedPreferences prefs = getBaseContext().getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
	    	       		prefs.edit().putBoolean("GettingStartedFirstTime", false).commit();
	    	       		Intent intent = new Intent(getBaseContext(), ExerciseListPage.class);
	    	    		startActivity(intent);
	    	    		finish();
	    	           }
	    	       });
	    	builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	        	   dialog.cancel();
	    	        	   }
	    	       });
	    		builder.create().show();
	    	}else{
	    		Intent intent = new Intent(getBaseContext(), ExerciseListPage.class);
	    		startActivity(intent);
	    		finish();
	    	}
			
	    }

	    public void gettingStartedToHome(View view) {
	    	final CheckBox notShow = (CheckBox)findViewById(R.id.notShow);
	    	if(notShow.isChecked()){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			//prefs.edit().putInt("FirstTime", 1).commit();
			builder.setMessage(R.string.warning_message_getting_started_to_home)
				   .setTitle("Warning");
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User clicked OK button
		       		SharedPreferences prefs = getBaseContext().getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		       		prefs.edit().putBoolean("GettingStartedFirstTime", false).commit();
		       		Intent intent = new Intent(getBaseContext(), HomePage.class);
		    		startActivity(intent);
		    		finish();
		           }
		       });
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   dialog.cancel();
		        	   }
		       });
			builder.create().show();
	    	}else{
	    		this.setContentView(R.layout.activity_home_page);
	    	}
	    }
}
