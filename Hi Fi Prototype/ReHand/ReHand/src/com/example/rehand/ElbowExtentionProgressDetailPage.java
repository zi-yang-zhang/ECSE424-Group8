package com.example.rehand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ElbowExtentionProgressDetailPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elbow_extention_progress_detail_page);
		ScoreDatabaseHelper db = new ScoreDatabaseHelper(this);
		// Show the Up button in the action bar.
		setupActionBar();

		final TextView levelText = (TextView) findViewById(R.id.level);
		final TextView personalBestScore = (TextView) findViewById(R.id.personalBestScore);
		final TextView progressText = (TextView) findViewById(R.id.progress);
		final TextView benchmarkText = (TextView) findViewById(R.id.benchmark);
		ExerciseResult elbowExtensionResult = db.getResult(2);
		double currentProgress = elbowExtensionResult.getCurrentProgress();
		double personalBest = elbowExtensionResult.getPersonalBest();
		int level = elbowExtensionResult.getLevel();
		double benchmark = elbowExtensionResult.getBenchmark();
		benchmarkText.setText(String.valueOf(benchmark));
		levelText.setText(String.valueOf(level));
		personalBestScore.setText(String.valueOf(personalBest));
		progressText.setText(String.valueOf(currentProgress));
		ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
        bar.setProgress((int) currentProgress);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.elbow_extention_progress_detail_page,
				menu);
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
