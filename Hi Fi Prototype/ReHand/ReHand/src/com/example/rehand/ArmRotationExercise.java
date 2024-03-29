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
	private float mLastX, mLastY, mLastZ;
	private long timer, sound_timer;
	private boolean gameStart = false;
	private boolean gameOver = false;
	private boolean stopped = false;
	private String state = "start";
	private double thresholdX = 0.1;
	private double thresholdY = 0.1;
	private int thresholdZ = 5;
	private long wait_time = 0;
	private long wait_sound_time = 4000;
	private float first = 0;
	private float second = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arm_rotation_exercise);

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
	    	if(time > 2000) {
	    		countDown.setText("");
		    	MediaPlayer ready = MediaPlayer.create(this, R.drawable.ar_start);
		    	ready.start();
		    	mLastX = x;
				mLastY = y;
				mLastZ = z;
		    	gameStart = true;
		    	timer = System.currentTimeMillis();
			    sound_timer = System.currentTimeMillis();
	    	}
	    } else if(!gameOver) {
			float deltaX = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);

			if(wait_time != 0) {
				if(Math.abs(deltaX) < thresholdX && Math.abs(deltaY) < thresholdY && Math.abs(deltaZ) < thresholdZ) {
					stopped = true;
				} else {
					stopped = false;
					timer = System.currentTimeMillis();
				}				
			}

			long time = System.currentTimeMillis() - timer;
			if(stopped) {
				if(wait_time != 0 && time > wait_time) {
					//stopped moving for enough time
					if(state.equals("A")) {
						//GO
                    	if(Math.abs(Math.abs(x) - 90) < 10 && Math.abs(y) < 10) {
                    		MediaPlayer go = MediaPlayer.create(this, R.drawable.ar_flip_left);
                        	go.start();
                        	state = "AB";
                        	wait_time = 0;
                        	wait_sound_time = 2000;
                        	sound_timer = System.currentTimeMillis();
                    	} else {
                    		wait_time = 0;
		            		wait_sound_time = 4000;
		            		state = "start";
		            		MediaPlayer ready = MediaPlayer.create(this, R.drawable.ar_start);
		    		    	ready.start();
                    	}
					} else if(state.equals("B")) {
						//HOLD
				    	MediaPlayer hold = MediaPlayer.create(this, R.drawable.hold);
						hold.start();
						state = "C";
						first = x;
						wait_time = 4000;
					} else if(state.equals("C")) {
						//BACK
						MediaPlayer back = MediaPlayer.create(this, R.drawable.back);
						back.start();
						state = "D";
						wait_time = 2000;
					} else if(state.equals("D")) {
						//GO
						MediaPlayer go = MediaPlayer.create(this, R.drawable.ar_flip_right);
						go.start();
						state = "DE";
						wait_time = 0;
						wait_sound_time = 1000;
						sound_timer = System.currentTimeMillis();
					} else if(state.equals("E")) {
						//HOLD
						MediaPlayer hold = MediaPlayer.create(this, R.drawable.hold);
						hold.start();
						state = "F";
						second = x;
						wait_time = 4000;
					} else if(state.equals("F")) {
						//DONE
						MediaPlayer done = MediaPlayer.create(this, R.drawable.done);
						done.start();
						state = "done";
						wait_time = 0;
						wait_sound_time = 2000;
						sound_timer = System.currentTimeMillis();
					}
                	timer = System.currentTimeMillis();
                	stopped = false;
				}
			}
			
			time = System.currentTimeMillis() - sound_timer;
			if(wait_sound_time != 0 && time > wait_sound_time) {
				//WAIT FOR SOUND TO END
				if(state.equals("start")) {
					state = "A";
					wait_time = 2000;
					wait_sound_time = 0;
				} else if(state.equals("AB")) {
					state = "B";
					wait_time = 2000;
					wait_sound_time = 0;
				} else if(state.equals("DE")) {
					state = "E";
					wait_time = 2000;
					wait_sound_time = 0;
				} else if(state.equals("done")) {
					gameOver = true;
				}
				timer = System.currentTimeMillis();
			}

			mLastX = x;
			mLastY = y;
			mLastZ = z;
	    } else {
	    	float range = Math.round(first - second);
	    	if(range < 0) {
	    		range += 360;
	    	}
			mSensorManager.unregisterListener(this, mOrientation);
			Intent intent = new Intent(this, ArmRotationResult.class);
			intent.putExtra("score", Float.toString(range));
			startActivity(intent);
			this.finish();
	    }
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