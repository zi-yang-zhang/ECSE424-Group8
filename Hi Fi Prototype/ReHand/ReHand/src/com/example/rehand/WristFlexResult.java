package com.example.rehand;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class WristFlexResult extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claw_stretch_result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claw_stretch_result, menu);
		return true;
	}

}
