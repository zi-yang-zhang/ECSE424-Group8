package com.example.rehand;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;
import android.support.v4.app.NavUtils;

public class WristFlexTutorialPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_wrist_flex_tutorial_page);
		// Show the Up button in the action bar.
		//setupActionBar();
		
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		VideoView videoView = new VideoView(this);
		//if you want the controls to appear
		videoView.setMediaController(new MediaController(this));
		Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.wrist_flex); //do not add any extension
		//if your file is named sherif.mp4 and placed in /raw
		//use R.raw.sherif
		videoView.setVideoURI(video);
		setContentView(videoView);
		videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
		    public void onCompletion(MediaPlayer mp) {
		    	Intent exerciseWithTutorial = new Intent(getBaseContext(), WristFlexPage.class);
		        startActivity(exerciseWithTutorial);
		    }
		});
		videoView.start();
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
		getMenuInflater().inflate(R.menu.wrist_flex_tutorial_page, menu);
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
	public void goToExercisePage(View view){
			Intent intent = new Intent(this, WristFlexExercise.class);
			startActivity(intent);
		
	}
}
