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
	float movementZ;
	private boolean mInitialized;
	private boolean gameStart;
	private int timer;
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
		final TextView countDown = (TextView) findViewById(R.id.countDown);
		final TextView horizontalMovement = (TextView) findViewById(R.id.horizontalMovement);
	    float z = event.values[0];
	    float x = event.values[1];
	    float y = event.values[2];

	    timer = timer +1;
	    if(timer/5 == 1){
	    	countDown.setText("3");
	    }else if(timer/5 == 2){
	    	countDown.setText("2");
	    }else if(timer/5 == 3){
	    	countDown.setText("1");
	    }else if(timer/5 == 4){
	    	countDown.setText("");
	    	mLastX = x;
			mLastY = y;
			mLastZ = z;
			xPosition.setText("");
			yPosition.setText("");
			zPosition.setText("");
			horizontalMovement.setText("");
	    	movementZ=0;
	    }else if(timer/5>=5&&timer/5<15){
			float deltaX = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);
			if (deltaX < NOISE) deltaX = mLastX;else deltaX = x;
			if (deltaY < NOISE) deltaY = mLastY;else deltaY = y;
			if (deltaZ < NOISE) deltaZ = mLastZ;else deltaZ = z;movementZ = movementZ + (deltaZ-mLastZ);
			
			mLastX = deltaX;
			mLastY = deltaY;
			mLastZ = deltaZ;
			
			horizontalMovement.setText("moved: " +Float.toString(movementZ));
			xPosition.setText("X: " +Float.toString(deltaX));
			yPosition.setText("Y: " +Float.toString(deltaY));
			zPosition.setText("Z: " +Float.toString(deltaZ));
	    }else if(timer==75){
			mSensorManager.unregisterListener(this, mOrientation);
			Intent intent = new Intent(this, ElbowExtensionResult.class);
			intent.putExtra("score", Float.toString(movementZ));
			startActivity(intent);
			this.finish();
	    }

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
	@Override
	public void onDestroy() {
	    super.onDestroy();  // Always call the superclass
	    
	    // Stop method tracing that the activity started during onCreate()
	    android.os.Debug.stopMethodTracing();
	}
}
