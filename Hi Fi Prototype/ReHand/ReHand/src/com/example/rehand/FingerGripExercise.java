package com.example.rehand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class FingerGripExercise extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finger_grip_exercise);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finger_grip_exercise, menu);
		return true;
	}
	public void goToFingerGripResult(View view){
		Intent intent = new Intent(this, FingerGripResult.class);
		startActivity(intent);
	}

}
