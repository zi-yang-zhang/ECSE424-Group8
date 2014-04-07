package com.example.rehand;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class WristFlexProgressDetailPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wrist_flex_progress_detail_page);
		// Show the Up button in the action bar.
		setupActionBar();
		ScoreDatabaseHelper db = new ScoreDatabaseHelper(this);
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		boolean gettingStarted = prefs.getBoolean("wristFlexFirstTime", true);
		DecimalFormat f = new DecimalFormat("##.##");
		final TextView levelText = (TextView) findViewById(R.id.level);
		final TextView personalBestScore = (TextView) findViewById(R.id.personalBestScore);
		final TextView lowestProgressText = (TextView) findViewById(R.id.lowestProgress);
		final TextView middleProgressText = (TextView) findViewById(R.id.middleProgress);
		final TextView highestProgressText = (TextView) findViewById(R.id.highestProgress);
		final TextView benchmarkText = (TextView) findViewById(R.id.benchmark);
		final TextView benchmarkTextView = (TextView) findViewById(R.id.benchmarkText);
		ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
		if(gettingStarted){
			benchmarkTextView.setText("Please do Baseline Test");
			benchmarkText.setText("");
			levelText.setText("");
			personalBestScore.setText("");
			lowestProgressText.setText("");
			middleProgressText.setText("");
			highestProgressText.setText("");
	        bar.setProgress((int) 0);
		}else{
		ExerciseResult wristFlexResult = db.getResult(4);
		double currentProgress = wristFlexResult.getCurrentProgress();
		double personalBest = wristFlexResult.getPersonalBest();
		int level = wristFlexResult.getLevel();
		double benchmark = wristFlexResult.getBenchmark();
		double levelIncrements = (wristFlexResult.getMaximumMark()-benchmark)/3;
		benchmarkText.setText(String.valueOf(f.format(benchmark))+"\u00b0");
		levelText.setText(String.valueOf(level));
		personalBestScore.setText(String.valueOf(f.format(personalBest))+"\u00b0");
		if(benchmark-levelIncrements<0){
			lowestProgressText.setText("0\u00b0");
		}else{
			lowestProgressText.setText(String.valueOf(f.format(benchmark-levelIncrements))+"\u00b0");
		}
		middleProgressText.setText(String.valueOf(f.format(benchmark))+"\u00b0");
		highestProgressText.setText(String.valueOf(f.format(benchmark+levelIncrements))+"\u00b0");
        bar.setProgress((int) currentProgress);
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wrist_flex_progress_detail_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void goToDetailPage(View view){
		Intent intent = new Intent(this, DetailsPage.class);
		startActivity(intent);
	}

}
