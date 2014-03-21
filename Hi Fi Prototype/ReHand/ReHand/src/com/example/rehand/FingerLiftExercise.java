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
	private int indexCounter, middleCounter, ringCounter, littleCounter;
	private boolean gameStart;
	private boolean indexStart, middleStart, ringStart, littleStart;
	private long[] startScores = new long[4];
	private long[] endScores = new long[4];
	private long[] holdTime = new long[4];
	private boolean indexdone, middledone, ringdone, littledone;
	CountDownTimer indexTimer;
	CountDownTimer middleTimer;
	CountDownTimer ringTimer;
	CountDownTimer littleTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences(
				"com.example.rehand", Context.MODE_PRIVATE);
		boolean gettingStarted = prefs.getBoolean("fingerLiftFirstTime", true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finger_lift_exercise);
		final ImageView indexFingerGreenLight = (ImageView) findViewById(R.id.indexFingerGreenLight);
		final ImageView indexFingerRedLight = (ImageView) findViewById(R.id.indexFingerRedLight);
		final ImageView indexFingerArrow = (ImageView) findViewById(R.id.indexFingerArrow);
		final Button indexFingerButton = (Button) findViewById(R.id.indexFingerButton);

		final ImageView middleFingerGreenLight = (ImageView) findViewById(R.id.middleFingerGreenLight);
		final ImageView middleFingerRedLight = (ImageView) findViewById(R.id.middleFingerRedLight);
		final ImageView middleFingerArrow = (ImageView) findViewById(R.id.middleFingerArrow);
		final Button middleFingerButton = (Button) findViewById(R.id.middleFingerButton);

		final ImageView ringFingerGreenLight = (ImageView) findViewById(R.id.ringFingerGreenLight);
		final ImageView ringFingerRedLight = (ImageView) findViewById(R.id.ringFingerRedLight);
		final ImageView ringFingerArrow = (ImageView) findViewById(R.id.ringFingerArrow);
		final Button ringFingerButton = (Button) findViewById(R.id.ringFingerButton);

		final ImageView littleFingerGreenLight = (ImageView) findViewById(R.id.littleFingerGreenLight);
		final ImageView littleFingerRedLight = (ImageView) findViewById(R.id.littleFingerRedLight);
		final ImageView littleFingerArrow = (ImageView) findViewById(R.id.littleFingerArrow);
		final Button littleFingerButton = (Button) findViewById(R.id.littleFingerButton);
		final TextView gameInstruction = (TextView) findViewById(R.id.gameInstruction);
		final TextView fingerLiftInstruction = (TextView) findViewById(R.id.fingerLiftInstruction);

		final TextView indexHoldTime = (TextView) findViewById(R.id.indexHoldTime);
		final TextView middleHoldTime = (TextView) findViewById(R.id.middleHoldTime);
		final TextView ringHoldTime = (TextView) findViewById(R.id.ringHoldTime);
		final TextView littleHoldTime = (TextView) findViewById(R.id.littleHoldTime);
		indexTimer = new CountDownTimer(17000, 1000) {

			public void onTick(long millisUntilFinished) {
				indexHoldTime.setText(String.valueOf(indexCounter));
				indexCounter++;
			}

			public void onFinish() {

				indexdone = true;
				holdTime[0] = -1;
				gameInstruction.setText("Lift your middle finger");
				indexHoldTime.setText("Timeout");
				indexFingerArrow.setVisibility(View.INVISIBLE);
				middleFingerArrow.setVisibility(View.VISIBLE);
				ringFingerArrow.setVisibility(View.INVISIBLE);
				littleFingerArrow.setVisibility(View.INVISIBLE);
			}
		};
		middleTimer = new CountDownTimer(17000, 1000) {

			public void onTick(long millisUntilFinished) {
				middleHoldTime.setText(String.valueOf(middleCounter));
				middleCounter++;
			}

			public void onFinish() {

				middledone = true;
				holdTime[1] = -1;
				gameInstruction.setText("Lift your ring finger");
				middleHoldTime.setText("Timeout");
				indexFingerArrow.setVisibility(View.INVISIBLE);
				middleFingerArrow.setVisibility(View.INVISIBLE);
				ringFingerArrow.setVisibility(View.VISIBLE);
				littleFingerArrow.setVisibility(View.INVISIBLE);
			}
		};
		ringTimer = new CountDownTimer(17000, 1000) {

			public void onTick(long millisUntilFinished) {
				ringHoldTime.setText(String.valueOf(ringCounter));
				ringCounter++;
			}

			public void onFinish() {
				holdTime[2] = -1;
				ringdone = true;
				gameInstruction.setText("Lift your little finger");
				ringHoldTime.setText("Timeout");
				indexFingerArrow.setVisibility(View.INVISIBLE);
				middleFingerArrow.setVisibility(View.INVISIBLE);
				ringFingerArrow.setVisibility(View.INVISIBLE);
				littleFingerArrow.setVisibility(View.VISIBLE);
			}
		};
		littleTimer = new CountDownTimer(17000, 1000) {

			public void onTick(long millisUntilFinished) {
				littleHoldTime.setText(String.valueOf(littleCounter));
				littleCounter++;
			}

			public void onFinish() {
				holdTime[3] = -1;
				littledone = true;
				littleHoldTime.setText("Timeout");
				int count = 0;
				double score = 0.0;
				for (int i = 0; i < 4; i++) {
					if (holdTime[i] != -1) {
						score = score + holdTime[i];
						count++;
					}
				}
				if (count > 0) {
					score = score / count;
				}
				indexFingerArrow.setVisibility(View.INVISIBLE);
				middleFingerArrow.setVisibility(View.INVISIBLE);
				ringFingerArrow.setVisibility(View.INVISIBLE);
				littleFingerArrow.setVisibility(View.INVISIBLE);
				Intent intent = new Intent(getBaseContext(),
						FingerLiftResult.class);

				intent.putExtra("score", Double.toString(score));
				startActivity(intent);
				finish();
			}
		};

		indexFingerButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (!gameStart) {
						indexStart = true;
						if (indexStart && middleStart && ringStart
								&& littleStart) {
							gameStart = true;
							gameInstruction
									.setText("Exercise Start! Lift your index finger");
							indexFingerArrow.setVisibility(View.VISIBLE);
							middleFingerArrow.setVisibility(View.INVISIBLE);
							ringFingerArrow.setVisibility(View.INVISIBLE);
							littleFingerArrow.setVisibility(View.INVISIBLE);
						}
					}
					if (gameStart && (!indexdone) && (startScores[0] != 0)) {
						indexTimer.cancel();
						endScores[0] = System.currentTimeMillis();
						holdTime[0] = (endScores[0] - startScores[0]) / 1000L;
						indexdone = true;
						gameInstruction.setText("Lift your middle finger");
						indexHoldTime.setText(String.valueOf(holdTime[0])
								+ "seconds");
						indexFingerArrow.setVisibility(View.INVISIBLE);
						middleFingerArrow.setVisibility(View.VISIBLE);
						ringFingerArrow.setVisibility(View.INVISIBLE);
						littleFingerArrow.setVisibility(View.INVISIBLE);
					}
					indexFingerRedLight.setVisibility(View.INVISIBLE);
					indexFingerGreenLight.setVisibility(View.VISIBLE);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					indexStart = false;
					if (gameStart && (!indexdone)) {
						indexTimer.start();
						startScores[0] = System.currentTimeMillis();
						MediaPlayer hold = MediaPlayer.create(getBaseContext(),
								R.drawable.hold);
						hold.start();
					}
					indexFingerGreenLight.setVisibility(View.INVISIBLE);
					indexFingerRedLight.setVisibility(View.VISIBLE);
				}
				return false;
			}

		});
		middleFingerButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (!gameStart) {
						middleStart = true;
						if (indexStart && middleStart && ringStart
								&& littleStart) {
							gameStart = true;

							gameInstruction
									.setText("Exercise Start! Lift your index finger");
							indexFingerArrow.setVisibility(View.VISIBLE);
							middleFingerArrow.setVisibility(View.INVISIBLE);
							ringFingerArrow.setVisibility(View.INVISIBLE);
							littleFingerArrow.setVisibility(View.INVISIBLE);
						}
					}

					if (gameStart && (!middledone) && (indexdone)
							&& (startScores[1] != 0)) {
						endScores[1] = System.currentTimeMillis();
						holdTime[1] = (endScores[1] - startScores[1]) / 1000L;
						middledone = true;
						middleTimer.cancel();
						gameInstruction.setText("Lift your ring finger");
						middleHoldTime.setText(String.valueOf(holdTime[1])
								+ "seconds");
						indexFingerArrow.setVisibility(View.INVISIBLE);
						middleFingerArrow.setVisibility(View.INVISIBLE);
						ringFingerArrow.setVisibility(View.VISIBLE);
						littleFingerArrow.setVisibility(View.INVISIBLE);
					}
					middleFingerRedLight.setVisibility(View.INVISIBLE);
					middleFingerGreenLight.setVisibility(View.VISIBLE);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					middleStart = false;

					if (gameStart && (!middledone) && (indexdone)) {
						middleTimer.start();
						startScores[1] = System.currentTimeMillis();
						MediaPlayer hold = MediaPlayer.create(getBaseContext(),
								R.drawable.hold);
						hold.start();
					}
					middleFingerGreenLight.setVisibility(View.INVISIBLE);
					middleFingerRedLight.setVisibility(View.VISIBLE);
				}
				return false;
			}

		});
		ringFingerButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (!gameStart) {
						ringStart = true;
						if (indexStart && middleStart && ringStart
								&& littleStart) {
							gameStart = true;

							gameInstruction
									.setText("Exercise Start! Lift your index finger");
							indexFingerArrow.setVisibility(View.VISIBLE);
							middleFingerArrow.setVisibility(View.INVISIBLE);
							ringFingerArrow.setVisibility(View.INVISIBLE);
							littleFingerArrow.setVisibility(View.INVISIBLE);
						}
					}
					if (gameStart && (!ringdone) && middledone && indexdone
							&& (startScores[2] != 0)) {
						ringTimer.cancel();
						endScores[2] = System.currentTimeMillis();
						holdTime[2] = (endScores[2] - startScores[2]) / 1000L;
						ringdone = true;
						gameInstruction.setText("Lift your little finger");
						ringHoldTime.setText(String.valueOf(holdTime[2])
								+ "seconds");
						indexFingerArrow.setVisibility(View.INVISIBLE);
						middleFingerArrow.setVisibility(View.INVISIBLE);
						ringFingerArrow.setVisibility(View.INVISIBLE);
						littleFingerArrow.setVisibility(View.VISIBLE);
					}
					ringFingerRedLight.setVisibility(View.INVISIBLE);
					ringFingerGreenLight.setVisibility(View.VISIBLE);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					ringStart = false;

					if (gameStart && (!ringdone) && middledone && indexdone) {
						ringTimer.start();
						startScores[2] = System.currentTimeMillis();
						MediaPlayer hold = MediaPlayer.create(getBaseContext(),
								R.drawable.hold);
						hold.start();
					}
					ringFingerGreenLight.setVisibility(View.INVISIBLE);
					ringFingerRedLight.setVisibility(View.VISIBLE);
				}
				return false;
			}

		});
		littleFingerButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (!gameStart) {
						littleStart = true;
						if (indexStart && middleStart && ringStart
								&& littleStart) {
							gameStart = true;

							gameInstruction
									.setText("Exercise Start! Lift your index finger");
							indexFingerArrow.setVisibility(View.VISIBLE);
							middleFingerArrow.setVisibility(View.INVISIBLE);
							ringFingerArrow.setVisibility(View.INVISIBLE);
							littleFingerArrow.setVisibility(View.INVISIBLE);
						}
					}

					if (gameStart && (!littledone) && ringdone && middledone
							&& indexdone && (startScores[3] != 0)) {
						littleTimer.cancel();
						endScores[3] = System.currentTimeMillis();
						holdTime[3] = (endScores[3] - startScores[3]) / 1000L;
						littledone = true;
						littleHoldTime.setText(String.valueOf(holdTime[3])
								+ "seconds");
						indexFingerArrow.setVisibility(View.INVISIBLE);
						middleFingerArrow.setVisibility(View.INVISIBLE);
						ringFingerArrow.setVisibility(View.INVISIBLE);
						littleFingerArrow.setVisibility(View.INVISIBLE);

						double score = 0;
						for (int i = 0; i < 4; i++) {
							score = score + holdTime[i];
						}
						score = score / 4;
						indexFingerArrow.setVisibility(View.INVISIBLE);
						middleFingerArrow.setVisibility(View.INVISIBLE);
						ringFingerArrow.setVisibility(View.INVISIBLE);
						littleFingerArrow.setVisibility(View.INVISIBLE);
						Intent intent = new Intent(getBaseContext(),
								FingerLiftResult.class);

						intent.putExtra("score", Double.toString(score));
						startActivity(intent);
						finish();

					}
					littleFingerRedLight.setVisibility(View.INVISIBLE);
					littleFingerGreenLight.setVisibility(View.VISIBLE);

				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					littleStart = false;

					if (gameStart && (!littledone) && ringdone && middledone
							&& indexdone) {
						littleTimer.start();
						startScores[3] = System.currentTimeMillis();
						MediaPlayer hold = MediaPlayer.create(getBaseContext(),
								R.drawable.hold);
						hold.start();
					}
					littleFingerGreenLight.setVisibility(View.INVISIBLE);
					littleFingerRedLight.setVisibility(View.VISIBLE);
				}
				return false;
			}

		});

		if (gettingStarted) {
			fingerLiftInstruction.setText("BenchMarking Test");
		} else {
			fingerLiftInstruction.setText("Finger Lift Exercise");

		}

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
