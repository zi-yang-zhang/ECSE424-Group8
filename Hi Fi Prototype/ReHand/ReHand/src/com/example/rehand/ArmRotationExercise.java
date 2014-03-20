package com.example.rehand;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ArmRotationExercise extends Activity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mOrientation;
	private final float NOISE = (float) 1;
	private float mLastX, mLastY, mLastZ;
	private long timer;
	private float start, end;
	private boolean gameStart = false;
	private boolean gameOver = false;
	private boolean stopped = false;
	private String state = "A";
	private int thresholdX = 10;
	private int thresholdY = 10;
	private int thresholdZ = 5;
	private long wait_time = 2000;
	private float first = 0;
	private float second = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arm_rotation_exercise);
/*
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	*/	
	    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	    mSensorManager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);

	    timer = System.currentTimeMillis();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.arm_rotation_exercise, menu);
		return true;
	}

	@Override
	public void onSensorChanged(SensorEvent event){
		final TextView countDown = (TextView) findViewById(R.id.countDown);
	    float z = event.values[0];
	    float x = event.values[1];
	    float y = event.values[2];
	    if(!gameStart) {
	    	long time = System.currentTimeMillis() - timer;
	    	if(time > 4000) {
	    		countDown.setText("");
		    	MediaPlayer ready = MediaPlayer.create(this, R.drawable.ready);
		    	ready.start();
		    	mLastX = x;
				mLastY = y;
				mLastZ = z;
		    	gameStart = true;
		    	timer = System.currentTimeMillis();
	    	} else if(time > 3000){
		    	countDown.setText("1");
		    } else if(time > 2000){
		    	countDown.setText("2");
		    } else if(time > 1000){
		    	countDown.setText("3");
		    }
	    } else if(!gameOver) {
			float deltaX = Math.abs(mLastX - x);
			//float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);
			//if (deltaX < NOISE) deltaX = mLastX; else deltaX = x;
			//if (deltaY < NOISE) deltaY = mLastY; else deltaY = y;
			//if (deltaZ < NOISE) deltaZ = mLastZ; else deltaZ = z;

	    	//countDown.setText("" + ((Math.abs(x) < thresholdX)?"Y":"N") + ((Math.abs(y) < thresholdY)?"Y":"N") + ((Math.abs(mLastZ - z) < thresholdZ)?"Y":"N"));
			if(Math.abs(deltaX) < thresholdX && Math.abs(y) < thresholdY && Math.abs(mLastZ - z) < thresholdZ) {
				stopped = true;
			} else {
				stopped = false;
				timer = System.currentTimeMillis();
			}

			if(stopped) {
				long time = System.currentTimeMillis() - timer;
				if(time > wait_time) {
					//stopped moving for enough time
					if(state.equals("F")) {
						//DONE
						MediaPlayer done = MediaPlayer.create(this, R.drawable.done);
						done.start();
						state = "G";
						gameOver = true;
					}
					if(state.equals("E")) {
						//HOLD
						MediaPlayer hold = MediaPlayer.create(this, R.drawable.hold);
						hold.start();
						state = "F";
						second = x;
						wait_time = 4000;
					}
					if(state.equals("D")) {
						//GO
						if(Math.abs(Math.abs(x) - 80) < thresholdX) {
							MediaPlayer go = MediaPlayer.create(this, R.drawable.go);
							go.start();
							state = "E";
							wait_time = 3000;
						}
					}
					if(state.equals("C")) {
						//BACK
						MediaPlayer back = MediaPlayer.create(this, R.drawable.back);
						back.start();
						state = "D";
						wait_time = 2000;
					}
					if(state.equals("B")) {
						//HOLD
				    	MediaPlayer hold = MediaPlayer.create(this, R.drawable.hold);
						hold.start();
						state = "C";
						first = x;
						wait_time = 4000;
					}
                    if(state.equals("A")) {
                    	//GO
                    	if(Math.abs(Math.abs(x) - 80) < thresholdX) {
                    		MediaPlayer go = MediaPlayer.create(this, R.drawable.go);
                        	go.start();
                        	state = "B";
                        	wait_time = 3000;
                    	}
					}
                	timer = System.currentTimeMillis();
                	stopped = false;
				}
			}

			mLastX = x;
			//mLastY = deltaY;
			mLastZ = z;
	    } else {
	    	float range = second - first;
	    	if(range < 0) {
	    		range += 360;
	    	}
			mSensorManager.unregisterListener(this, mOrientation);
			Intent intent = new Intent(this, ArmRotationResult.class);
			intent.putExtra("score", Float.toString(range));
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

	public void goToArmRotationResult(View view) {
		Intent intent = new Intent(this, ArmRotationResult.class);
		startActivity(intent);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
}
