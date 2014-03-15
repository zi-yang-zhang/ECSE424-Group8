package com.example.rehand;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ElbowExtensionExercise extends Activity implements SensorEventListener {
	/*
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private final float NOISE = (float) 0.3;
	private boolean mInitialized;
	private float mLastX, mLastY, mLastZ;
	*/

	
	private SensorManager mSensorManager;
	private Sensor mOrientation;
	private final float NOISE = (float) 5;
	private float mLastX, mLastY, mLastZ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elbow_extension_exercise);
/*
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	*/
	    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	    mSensorManager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.elbow_extension_exercise, menu);
		return true;
	}
	@Override
	public void onSensorChanged(SensorEvent event){
		final TextView xPosition = (TextView) findViewById(R.id.xPosition);
		final TextView yPosition = (TextView) findViewById(R.id.yPosition);
		final TextView zPosition = (TextView) findViewById(R.id.zPosition);
	    float z = event.values[0];
	    float x = event.values[1];
	    float y = event.values[2];

				float deltaX = Math.abs(mLastX - x);
				float deltaY = Math.abs(mLastY - y);
				float deltaZ = Math.abs(mLastZ - z);
				if (deltaX < NOISE) deltaX = mLastX;else deltaX = x;
				if (deltaY < NOISE) deltaY = mLastY;else deltaY = y;
				if (deltaZ < NOISE) deltaZ = mLastZ;else deltaZ = z;
				boolean enterX = deltaX < NOISE;
				System.out.println(enterX);
				mLastX = deltaX;
				mLastY = deltaY;
				mLastZ = deltaZ;
				xPosition.setText("X: " +Float.toString(deltaX));
				yPosition.setText("Y: " +Float.toString(deltaY));
				zPosition.setText("Z: " +Float.toString(deltaZ));

		/*
		final TextView xPosition = (TextView) findViewById(R.id.xPosition);
		final TextView yPosition = (TextView) findViewById(R.id.yPosition);
		final TextView zPosition = (TextView) findViewById(R.id.zPosition);
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		if (!mInitialized) {
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			xPosition.setText("X: " +"0.0");
			yPosition.setText("Y: " +"0.0");
			zPosition.setText("Z: " +"0.0");
			mInitialized = true;
		}else {
			float deltaX = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);
			if (deltaX < NOISE) deltaX = (float)0.0;
			if (deltaY < NOISE) deltaY = (float)0.0;
			if (deltaZ < NOISE) deltaZ = (float)0.0;
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			xPosition.setText("X: " +Float.toString(deltaX));
			yPosition.setText("Y: " +Float.toString(deltaY));
			zPosition.setText("Z: " +Float.toString(deltaZ));
			
		}
		*/
		
	}
	public void goToElbowExtensionResult(View view){
		Intent intent = new Intent(this, ElbowExtensionResult.class);
		startActivity(intent);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
