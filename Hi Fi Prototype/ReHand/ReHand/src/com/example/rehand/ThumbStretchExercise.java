package com.example.rehand;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ThumbStretchExercise extends Activity {
	final float xCalibration = 100;
	final float yCalibration = 250;
	ImageView redLight;  
	ImageView greenLight; 
	ImageView redCross; 
	TextView testInstruction;
	boolean touches;
	boolean initialTouch,secondTouch,thirdTouch;
	float initialX,initialY,secondX,secondY,thirdX,thirdY;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thumb_stretch_exercise);

		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		boolean gettingStarted = prefs.getBoolean("thumbStretchFirstTime", true);

		testInstruction = (TextView)findViewById(R.id.testInstruction);

		final TextView testType = (TextView)findViewById(R.id.testType);
		if(gettingStarted){
			testType.setText("Bechmarking Test");
			
		}else{
			testType.setText("Thumb Stretch Exercise");
		}
		testInstruction.setText("Tap Your Start Point");

		MediaPlayer go = MediaPlayer.create(getBaseContext(), R.drawable.go);
		go.start();
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thumb_stretch_exercise, menu);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){ 
	    int action = MotionEventCompat.getActionMasked(event);
	    redLight = (ImageView)findViewById(R.id.redLight);
	    greenLight = (ImageView)findViewById(R.id.greenLight);
	    redCross = (ImageView)findViewById(R.id.redCross);
	    MediaPlayer go = MediaPlayer.create(getBaseContext(), R.drawable.go);
	    switch(action) {
	        case (MotionEvent.ACTION_DOWN) :
	        	
	        	if(!initialTouch){
	        		initialX = event.getX()-xCalibration;
	        		initialY = event.getY()-yCalibration;
	        		redCross.setVisibility(View.VISIBLE);
	        		redCross.setX(initialX);
	        		redCross.setY(initialY);
	        	}else if(!secondTouch){
	        		secondX = event.getX()-xCalibration;
	        		secondY = event.getY()-yCalibration;
	        		redLight.setVisibility(View.VISIBLE);
	        		redLight.setX(secondX);
	        		redLight.setY(secondY);
	        	}else if(!thirdTouch){
	        		thirdX = event.getX()-xCalibration;
	        		thirdY = event.getY()-yCalibration;

	        		greenLight.setVisibility(View.VISIBLE);
	        		greenLight.setX(thirdX);
	        		greenLight.setY(thirdY);

	        	}
	            return true;
	        case (MotionEvent.ACTION_UP) :
	        	if(!initialTouch){
	        		testInstruction.setText("Go as far as you can to the left");
	        		initialTouch = true;
	        		go.start();
	        	}else if(!secondTouch){
	        		testInstruction.setText("Go as far as you can to the right");
	        		secondTouch = true;
	        		go.start();
	        	}else if(!thirdTouch){
	        		testInstruction.setText("");
	        		thirdTouch = true;
	        		MediaPlayer done = MediaPlayer.create(getBaseContext(), R.drawable.done);
	        		done.start();
	        		float scoreLeftX = Math.abs(initialX-secondX);
	        		float scoreLeftY = Math.abs(initialY-secondY);
	        		float scoreLeft = (float) Math.sqrt(scoreLeftX*scoreLeftX+scoreLeftY*scoreLeftY);

	        		float scoreRightX = Math.abs(initialX-thirdX);
	        		float scoreRightY = Math.abs(initialY-thirdY);
	        		float scoreRight = (float) Math.sqrt(scoreRightX*scoreRightX+scoreRightY*scoreRightY);
	        		
	        		float score = (scoreRight+scoreLeft)/2;
	        		Intent intent = new Intent(getBaseContext(), ThumbStretchResult.class);
	       	 		intent.putExtra("score", Float.toString(score));
	       	 		startActivity(intent);
	       	 		finish();
	        	}
	        	
	            return true;
	        default : 
	            return super.onTouchEvent(event);
	    }
	    }  
	/*
	public static float convertDpToPixel(float dp, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi / 160f);
	    return px;
	}
	public static float convertPixelsToDp(float px, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float dp = px / (metrics.densityDpi / 160f);
	    return dp;
	}
	*/

}
