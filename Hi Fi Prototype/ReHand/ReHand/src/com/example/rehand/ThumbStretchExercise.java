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
		final MediaPlayer intro = MediaPlayer.create(this, R.drawable.thumbstretchintro);
		final TextView testType = (TextView)findViewById(R.id.testType);
		if(gettingStarted){
			testType.setText("Baseline Test");
			
		}else{
			testType.setText("Thumb Stretch Exercise");
		}
		testInstruction.setText("Tap Your Start Point");

		
		intro.start();
		
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

		final MediaPlayer left = MediaPlayer.create(getBaseContext(), R.drawable.thumbstretchleft);
		final MediaPlayer right = MediaPlayer.create(getBaseContext(), R.drawable.thumbstretchright);
	    switch(action) {
	        case (MotionEvent.ACTION_DOWN) :
	        	
	        	if(!initialTouch){
	        		initialX = event.getX()-xCalibration;
	        		initialY = event.getY()-yCalibration;
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
	        		left.start();
	        	}else if(!secondTouch){
	        		testInstruction.setText("Go as far as you can to the right");
	        		secondTouch = true;
	        		left.stop();
	        		left.release();
	        		right.start();
	        	}else if(!thirdTouch){
	        		testInstruction.setText("");
	        		thirdTouch = true;
	        		right.stop();
	        		right.release();
	        		Resources resources = getBaseContext().getResources();
	        	    DisplayMetrics metrics = resources.getDisplayMetrics();
	        		float scoreLeftX = Math.abs(initialX-secondX);
	        		scoreLeftX=scoreLeftX/metrics.xdpi;
	        		float scoreLeftY = Math.abs(initialY-secondY);
	        		scoreLeftY=scoreLeftY/metrics.xdpi;
	        		float scoreLeft = (float) Math.sqrt(scoreLeftX*scoreLeftX+scoreLeftY*scoreLeftY);

	        		float scoreRightX = Math.abs(initialX-thirdX);
	        		scoreRightX=scoreRightX/metrics.xdpi;
	        		float scoreRightY = Math.abs(initialY-thirdY);
	        		scoreRightY=scoreRightY/metrics.xdpi;
	        		float scoreRight = (float) Math.sqrt(scoreRightX*scoreRightX+scoreRightY*scoreRightY);
	        		
	        		float score = (scoreRight+scoreLeft)/2;
	        		System.out.println("xdpi: "+metrics.xdpi);
	        		System.out.println("ydpi: "+metrics.ydpi);
	        		System.out.println("scoreLeftX: "+scoreLeftX);
	        		System.out.println("scoreLeftY: "+scoreLeftY);

	        		System.out.println("scoreRightX: "+scoreRightX);
	        		System.out.println("scoreRightY: "+scoreRightY);
	        		
	        		Intent intent = new Intent(getBaseContext(), ThumbStretchResult.class);
	        		intent.putExtra("scoreLeft", Float.toString(scoreLeft));
	        		intent.putExtra("scoreRight", Float.toString(scoreRight));
	       	 		intent.putExtra("score", Float.toString(score));
	       	 		startActivity(intent);
	       	 		left.release();
	       	 		right.release();
	       	 		finish();
	        	}
	        	
	            return true;
	        default : 
	            return super.onTouchEvent(event);
	    }
	    }  

}
