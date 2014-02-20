package com.example.rehand;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;

public class WristFlexResult extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		prefs.edit().putString("WristFlex", "Done").commit();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wrist_flex_result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wrist_flex_result, menu);
		return true;
	}

	public void goToExercisesPage(View view){
		Intent intent = new Intent(this, ExerciseListPage.class);
		startActivity(intent);
	}
}
