package com.example.rehand;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class SignUpPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);

		if(prefs.getInt("GettingStartedFirstTime", 0) != 1) {
			prefs.edit().putInt("GettingStartedFirstTime", 0);
		}

		if(prefs.getInt("FirstTime", 0) == 0) {
			//First time opening app
			setContentView(R.layout.activity_startup_page);
		} else {
			setContentView(R.layout.activity_sign_up_page);
		}

		setupActionBar();
	}

	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up_page, menu);
		return true;
	}
	/** Called when the user clicks the login button */
	public void goToHomePage(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, HomePage.class);
		startActivity(intent);
	}

	public void close(View view) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		prefs.edit().putInt("FirstTime", 1).commit();

		Intent intent = new Intent(this, SignUpPage.class);
		startActivity(intent);
	}
}
