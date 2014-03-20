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
	//float movementZ;
	private int timer;
	private float lastDeltaX = 0.0f;
	private float lastDeltaY = 0.0f;
	private float lastDeltaZ = 0.0f;
	float movementZ;
	private boolean gameStart = false;
	private boolean gameOver = false;
	private int unit_time = 1;
	private boolean stopped = false;
	private String state = "A";
	private int threshold = 3;
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
		final TextView countDown = (TextView) findViewById(R.id.countDown);
	    float z = event.values[0];
	    float x = event.values[1];
	    float y = event.values[2];
	    if(!gameStart) {
	    	timer++;
		    if(timer/unit_time == 1){
		    	countDown.setText("3");
		    }else if(timer/unit_time == 2){
		    	countDown.setText("2");
		    }else if(timer/unit_time == 3){
		    	countDown.setText("1");
		    }else if(timer/unit_time == 4){
		    	countDown.setText("");
		    	MediaPlayer ready = MediaPlayer.create(this, R.drawable.ready);
		    	ready.start();
		    	mLastX = x;
				mLastY = y;
				mLastZ = z;
		    	movementZ=0;
		    	gameStart = true;
		    	timer = 0;
	        }
	    } else {
	    	timer++;
			float deltaX = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);
			if (deltaX < NOISE) deltaX = mLastX; else deltaX = x;
			if (deltaY < NOISE) deltaY = mLastY; else deltaY = y;
			if (deltaZ < NOISE) deltaZ = mLastZ; else deltaZ = z;

			if(Math.abs(lastDeltaX - deltaX) < threshold && Math.abs(lastDeltaY - deltaY) < threshold && Math.abs(lastDeltaZ - deltaZ) < threshold) {
				stopped = true;
			} else {
				timer = 0;
				stopped = false;
			}
			lastDeltaX = deltaX;
			lastDeltaY = deltaY;
			lastDeltaZ = deltaZ;

			if(stopped) {
				timer++;
				if(timer/unit_time == 5) {
					//stopped moving for enough time
					if(state.equals("D")) {
						//DONE
						MediaPlayer done = MediaPlayer.create(this, R.drawable.done);
						done.start();
						state = "E";
						gameOver = true;
					}
					if(state.equals("C")) {
						//Continue holding
						state = "D";
					}
					if(state.equals("B")) {
						//HOLD
				    	MediaPlayer hold = MediaPlayer.create(this, R.drawable.hold);
						hold.start();
						state = "C";
					}
                    if(state.equals("A")) {
                    	//GO
                    	MediaPlayer go = MediaPlayer.create(this, R.drawable.go);
                    	go.start();
                    	state = "B";
                    	movementZ = 0;
					}
                	timer = 0;
                	stopped = false;
				}
			}
			movementZ = movementZ + (deltaZ-mLastZ);

			mLastX = deltaX;
			mLastY = deltaY;
			mLastZ = deltaZ;
	    }

	    if(gameOver){
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
