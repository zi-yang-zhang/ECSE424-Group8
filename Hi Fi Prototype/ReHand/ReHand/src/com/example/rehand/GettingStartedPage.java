package com.example.rehand;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class GettingStartedPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getting_started_page);
		setupActionBar();
	}

	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.getting_started_page, menu);
		return true;
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
}