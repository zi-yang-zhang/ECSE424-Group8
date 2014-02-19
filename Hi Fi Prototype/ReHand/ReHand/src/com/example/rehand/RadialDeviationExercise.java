package com.example.rehand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class RadialDeviationExercise extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radial_deviation_exercise);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.radial_deviation_exercise, menu);
		return true;
	}
	public void goToRadialDeviationResult(View view){
		Intent intent = new Intent(this, RadialDeviationResult.class);
		startActivity(intent);
	}

}
