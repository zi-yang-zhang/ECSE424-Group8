package com.example.rehand;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class RadialDeviationResult extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radial_deviation_result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.radial_deviation_result, menu);
		return true;
	}

	public void goToExercisesPage(View view){
		Intent intent = new Intent(this, ExerciseListPage.class);
		startActivity(intent);
	}
}
