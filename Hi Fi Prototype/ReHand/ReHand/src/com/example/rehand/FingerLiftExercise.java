package com.example.rehand;

import java.lang.reflect.Array;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FingerLiftExercise extends Activity {
	int fingerIndex;
	int [] questions = new int[6];
	int [] answers = new int[6];
	int i;
	int timeCount;
	boolean index,middle,ring,little;
	long [] benchmarkStartScores = new long[4];
	long [] benchmarkEndScores = new long[4];
	boolean indexFirstPressed,middleFirstPressed,ringFirstPressed,littleFirstPressed;
	boolean indexdone,middledone,ringdone,littledone;
	int timer = 60;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		boolean gettingStarted = prefs.getBoolean("fingerLiftFirstTime", true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finger_lift_exercise);
		final ImageView indexFingerGreenLight = (ImageView)findViewById(R.id.indexFingerGreenLight);
		final ImageView indexFingerRedLight = (ImageView)findViewById(R.id.indexFingerRedLight);
		final ImageView indexFingerArrow = (ImageView)findViewById(R.id.indexFingerArrow);
		final Button indexFingerButton = (Button) findViewById(R.id.indexFingerButton);

		final ImageView middleFingerGreenLight = (ImageView)findViewById(R.id.middleFingerGreenLight);
		final ImageView middleFingerRedLight = (ImageView)findViewById(R.id.middleFingerRedLight);
		final ImageView middleFingerArrow = (ImageView)findViewById(R.id.middleFingerArrow);
		final Button middleFingerButton = (Button) findViewById(R.id.middleFingerButton);
		
		final ImageView ringFingerGreenLight = (ImageView)findViewById(R.id.ringFingerGreenLight);
		final ImageView ringFingerRedLight = (ImageView)findViewById(R.id.ringFingerRedLight);
		final ImageView ringFingerArrow = (ImageView)findViewById(R.id.ringFingerArrow);
		final Button ringFingerButton = (Button) findViewById(R.id.ringFingerButton);
		
		final ImageView littleFingerGreenLight = (ImageView)findViewById(R.id.littleFingerGreenLight);
		final ImageView littleFingerRedLight = (ImageView)findViewById(R.id.littleFingerRedLight);
		final ImageView littleFingerArrow = (ImageView)findViewById(R.id.littleFingerArrow);
		final Button littleFingerButton = (Button) findViewById(R.id.littleFingerButton);
		final TextView gameCounter = (TextView)findViewById(R.id.gameCounter);
		final TextView fingerLiftInstruction = (TextView)findViewById(R.id.fingerLiftInstruction);
		
		indexFingerButton.setOnTouchListener(new OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        if(event.getAction() == MotionEvent.ACTION_DOWN) {
		        	index = false;
		        	indexFingerRedLight.setVisibility(View.INVISIBLE);
		        	indexFingerGreenLight.setVisibility(View.VISIBLE);
		        } else if (event.getAction() == MotionEvent.ACTION_UP) {
		        	index = true;
		        	indexFingerGreenLight.setVisibility(View.INVISIBLE);
		        	indexFingerRedLight.setVisibility(View.VISIBLE);
		        }
				return false;
		    }
		
		});
		middleFingerButton.setOnTouchListener(new OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        if(event.getAction() == MotionEvent.ACTION_DOWN) {
		        	middle = false;
		        	middleFingerRedLight.setVisibility(View.INVISIBLE);
		        	middleFingerGreenLight.setVisibility(View.VISIBLE);
		        } else if (event.getAction() == MotionEvent.ACTION_UP) {
		        	middle = true;
		        	middleFingerGreenLight.setVisibility(View.INVISIBLE);
		        	middleFingerRedLight.setVisibility(View.VISIBLE);
		        }
				return false;
		    }
		
		});
		ringFingerButton.setOnTouchListener(new OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        if(event.getAction() == MotionEvent.ACTION_DOWN) {
		        	ring = false;
		        	ringFingerRedLight.setVisibility(View.INVISIBLE);
		        	ringFingerGreenLight.setVisibility(View.VISIBLE);
		        } else if (event.getAction() == MotionEvent.ACTION_UP) {
		        	ring = true;
		        	ringFingerGreenLight.setVisibility(View.INVISIBLE);
		        	ringFingerRedLight.setVisibility(View.VISIBLE);
		        }
				return false;
		    }
		
		});
		littleFingerButton.setOnTouchListener(new OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        if(event.getAction() == MotionEvent.ACTION_DOWN) {
		        	little = false;
		        	littleFingerRedLight.setVisibility(View.INVISIBLE);
		        	littleFingerGreenLight.setVisibility(View.VISIBLE);
		        } else if (event.getAction() == MotionEvent.ACTION_UP) {
		        	little = true;
		        	littleFingerGreenLight.setVisibility(View.INVISIBLE);
		        	littleFingerRedLight.setVisibility(View.VISIBLE);
		        }
				return false;
		    }
		
		});
  		 
		if(gettingStarted){
			fingerLiftInstruction.setText("BenchMarking Test");
					}else{
			fingerLiftInstruction.setText("Finger Lift Exercise");
			/*
		 new CountDownTimer(30000, 1000) {
			 
		     public void onTick(long millisUntilFinished) {
		    	 
		    	 System.out.println(i);
		    	 
		    	 if(timeCount >= 0 && timeCount <5){
		    		 indexFingerArrow.setVisibility(View.INVISIBLE);
	    	 		 middleFingerArrow.setVisibility(View.INVISIBLE);
	    	 		 ringFingerArrow.setVisibility(View.INVISIBLE);
	    	 		 littleFingerArrow.setVisibility(View.INVISIBLE);
		    	 }else{
		    		 
		    		 
		    		 if(timeCount%5==0){
		    			 fingerIndex =randInt(0,3);
			    		 if(i>0){
				    		 while(fingerIndex == questions[i-1]){
				    			 fingerIndex =randInt(0,3);
					    	 }
			    		 }
			    		 questions[i] = fingerIndex;
				    	 switch (fingerIndex) {
				    	 case 0: 
				    		 indexFingerArrow.setVisibility(View.VISIBLE);
			    	 		 middleFingerArrow.setVisibility(View.INVISIBLE);
			    	 		 ringFingerArrow.setVisibility(View.INVISIBLE);
			    	 		 littleFingerArrow.setVisibility(View.INVISIBLE);
			    	 		 System.out.println("Point to index finger");
				    		 break;
				    	 case 1: 
				    		 indexFingerArrow.setVisibility(View.INVISIBLE);
			    	 		 middleFingerArrow.setVisibility(View.VISIBLE);
			    	 		 ringFingerArrow.setVisibility(View.INVISIBLE);
			    	 		 littleFingerArrow.setVisibility(View.INVISIBLE);
			    	 		System.out.println("Point to middle finger");
				    		 break;
				    	 case 2: 
				    		 indexFingerArrow.setVisibility(View.INVISIBLE);
			    	 		 middleFingerArrow.setVisibility(View.INVISIBLE);
			    	 		 ringFingerArrow.setVisibility(View.VISIBLE);
			    	 		 littleFingerArrow.setVisibility(View.INVISIBLE);
			    	 		System.out.println("Point to ring finger");
				    		 break;
				    	 case 3: 
				    		 indexFingerArrow.setVisibility(View.INVISIBLE);
			    	 		 middleFingerArrow.setVisibility(View.INVISIBLE);
			    	 		 ringFingerArrow.setVisibility(View.INVISIBLE);
			    	 		 littleFingerArrow.setVisibility(View.VISIBLE);
			    	 		System.out.println("Point to little finger");
				    		 break;
				    	 }
				    	 i++;
			    	 }
						if(timeCount%5==3){
							if(index){
					    		 answers[i-1]=0;
					    		 System.out.println("index finger released");
					    	 }
					    	 if(middle){
					    		 answers[i-1]=1;
					    		 System.out.println("middle finger released");
					    	 }
					    	 if(ring){
					    		 answers[i-1]=2;
					    		 System.out.println("ring finger released");
					    	 }
					    	 if(little){
					    		 answers[i-1]=3;
					    		 System.out.println("little finger released");
					    	 }
							}
							
		    		 }
		    		 
		    	 timeCount++;
		     }

		     public void onFinish() {
		    	 indexFingerArrow.setVisibility(View.INVISIBLE);
    	 		 middleFingerArrow.setVisibility(View.INVISIBLE);
    	 		 ringFingerArrow.setVisibility(View.INVISIBLE);
    	 		 littleFingerArrow.setVisibility(View.INVISIBLE);
    	 		 double score = 0; 
    	 		 for(i=0;i<questions.length-1;i++){
    	 			 System.out.println("Question" +i+": "+ questions[i]);
    	 			 System.out.println("Answer: " +i+": "+ answers[i]);
    	 			if(questions[i] == answers[i]){
    	 				score++;
    	 			}
    	 		 }

    	 		Intent intent = new Intent(getBaseContext(), FingerLiftResult.class);

    	 		intent.putExtra("score", Double.toString(score));
    	 		startActivity(intent);
    	 		finish();
		     }
		  }.start();
		  */
		}
		CountDownTimer startTimer = new CountDownTimer(60000, 100) {
			 
		     public void onTick(long millisUntilFinished) {
		    	 if(i%10==0){
		    		 gameCounter.setText(String.valueOf(timer));
		    		 timer--;
		    		 
		    	 }
		    	 i++;
		    	 if(!indexFirstPressed){
		    		 indexFingerArrow.setVisibility(View.VISIBLE);
		    		 middleFingerArrow.setVisibility(View.INVISIBLE);
		    		 ringFingerArrow.setVisibility(View.INVISIBLE);
		    		 littleFingerArrow.setVisibility(View.INVISIBLE);
		    	 }
		    	 if((!indexFirstPressed)&&indexFingerButton.isPressed()){
		    		 indexFirstPressed=true;
		    	 }
		    	 if((!indexFingerButton.isPressed())&&benchmarkStartScores[0]==0&&indexFirstPressed){
		    		 System.out.println("start index");
		    		 MediaPlayer hold = MediaPlayer.create(getBaseContext(), R.drawable.hold);
						hold.start();
		    		 benchmarkStartScores[0]=System.currentTimeMillis();
		    	 }
		    	 if((indexFingerButton.isPressed())&&benchmarkEndScores[0]==0&&benchmarkStartScores[0]!=0&&indexFirstPressed){
		    		 benchmarkEndScores[0]=System.currentTimeMillis();
		    		 System.out.println("end index");
		    		 indexFingerArrow.setVisibility(View.INVISIBLE);
		    		 middleFingerArrow.setVisibility(View.VISIBLE);
		    		 ringFingerArrow.setVisibility(View.INVISIBLE);
		    		 littleFingerArrow.setVisibility(View.INVISIBLE);
		    		 indexdone=true;
		    	 }
		    	 
		    	 if((!middleFirstPressed)&&middleFingerButton.isPressed()){
		    		 middleFirstPressed=true;
		    	 }
		    	 if((!middleFingerButton.isPressed())&&benchmarkStartScores[1]==0&&middleFirstPressed&&indexdone){
		    		 System.out.println("start middle");
		    		 MediaPlayer hold = MediaPlayer.create(getBaseContext(), R.drawable.hold);
						hold.start();
		    		 benchmarkStartScores[1]=System.currentTimeMillis();
		    	 }
		    	 if((middleFingerButton.isPressed())&&benchmarkEndScores[1]==0&&benchmarkStartScores[1]!=0&&middleFirstPressed){
		    		 benchmarkEndScores[1]=System.currentTimeMillis();
		    		 System.out.println("end middle");
		    		 indexFingerArrow.setVisibility(View.INVISIBLE);
		    		 middleFingerArrow.setVisibility(View.INVISIBLE);
		    		 ringFingerArrow.setVisibility(View.VISIBLE);
		    		 littleFingerArrow.setVisibility(View.INVISIBLE);
		    		 middledone=true;
		    	 }
		    	 
		    	 if((!ringFirstPressed)&&ringFingerButton.isPressed()){
		    		 ringFirstPressed=true;
		    	 }
		    	 if((!ringFingerButton.isPressed())&&benchmarkStartScores[2]==0&&ringFirstPressed&&middledone){
		    		 System.out.println("start ring");
		    		 MediaPlayer hold = MediaPlayer.create(getBaseContext(), R.drawable.hold);
						hold.start();
		    		 benchmarkStartScores[2]=System.currentTimeMillis();
		    	 }
		    	 if((ringFingerButton.isPressed())&&benchmarkEndScores[2]==0&&benchmarkStartScores[2]!=0&&ringFirstPressed){
		    		 benchmarkEndScores[2]=System.currentTimeMillis();
		    		 System.out.println("end ring");
		    		 indexFingerArrow.setVisibility(View.INVISIBLE);
		    		 middleFingerArrow.setVisibility(View.INVISIBLE);
		    		 ringFingerArrow.setVisibility(View.INVISIBLE);
		    		 littleFingerArrow.setVisibility(View.VISIBLE);
		    		 ringdone = true;
		    	 }
		    	 
		    	 if((!littleFirstPressed)&&littleFingerButton.isPressed()){
		    		 littleFirstPressed=true;
		    	 }
		    	 if((!littleFingerButton.isPressed())&&benchmarkStartScores[3]==0&&littleFirstPressed&&ringdone){
		    		 System.out.println("start little");
		    		 MediaPlayer hold = MediaPlayer.create(getBaseContext(), R.drawable.hold);
						hold.start();
		    		 benchmarkStartScores[3]=System.currentTimeMillis();
		    	 }
		    	 if((littleFingerButton.isPressed())&&benchmarkEndScores[3]==0&&benchmarkStartScores[3]!=0&&littleFirstPressed){
		    		 
		    		 System.out.println("end little");
		    		 benchmarkEndScores[3]=System.currentTimeMillis();
			    	 indexFingerArrow.setVisibility(View.INVISIBLE);
		   	 		 middleFingerArrow.setVisibility(View.INVISIBLE);
		   	 		 ringFingerArrow.setVisibility(View.INVISIBLE);
		   	 		 littleFingerArrow.setVisibility(View.INVISIBLE);
		   	 		 littledone = true;
		    	 }
		    	 if(littledone){
		    		 super.cancel();
		    	 }
		    	 
	    	 		 
		     }
		     public void onFinish() {
		    	 double score=0;
		    	 for(i=0;i<4;i++){
		    		 System.out.println(i);
		    		 System.out.println((benchmarkEndScores[i]-benchmarkStartScores[i])/1000);
		    		
		    		 score = score+(benchmarkEndScores[i]-benchmarkStartScores[i]);
		    	 }
		    	 score = score/4/1000;
		    	 gameCounter.setText("Lift index finger to start bencmarking");
		    	 indexFingerArrow.setVisibility(View.INVISIBLE);
   	 		 middleFingerArrow.setVisibility(View.INVISIBLE);
   	 		 ringFingerArrow.setVisibility(View.INVISIBLE);
   	 		 littleFingerArrow.setVisibility(View.INVISIBLE);
   	 		Intent intent = new Intent(getBaseContext(), FingerLiftResult.class);

   	 		intent.putExtra("score", Double.toString(score));
   	 		startActivity(intent);
   	 		finish();
		     }
		  };
		  startTimer.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finger_lift_exercise, menu);
		return true;
	}
	
	
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
