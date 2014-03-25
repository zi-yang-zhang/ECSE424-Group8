package com.example.rehand;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

public class GettingStartedPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getting_started_page);
		setupActionBar();
	}

	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.getting_started_page, menu);
		return true;
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
	    		Intent intent = new Intent(getBaseContext(), HomePage.class);
	    		startActivity(intent);
	    		finish();
	    	}
	    }
}