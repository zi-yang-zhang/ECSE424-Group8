package com.example.rehand;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ElbowExtensionResult extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = this.getSharedPreferences("com.example.rehand", Context.MODE_PRIVATE);
		//prefs.edit().putString("ElbowExtension", "Done").commit();
		ScoreDatabaseHelper db = new ScoreDatabaseHelper(this);
		boolean gettingStarted = prefs.getBoolean("elbowExtensionFirstTime", true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elbow_extension_result);
		final TextView socreText = (TextView) findViewById(R.id.socreText);
		String score = this.getIntent().getStringExtra("score");
		if(gettingStarted){
			socreText.setText("Your Benchmark: "+score);
			ExerciseResult elbowExtensionResult = new ExerciseResult(2,"Elbow Extension",Double.parseDouble(score),180 );
			
			db.addResult(elbowExtensionResult);
			prefs.edit().putBoolean("elbowExtensionFirstTime", false).commit();
		}else{
			socreText.setText("Your Score: "+score);
			ExerciseResult elbowExtensionResult = db.getResult(2);
			if(elbowExtensionResult.getPreviousScore()==0){
				
			}
			
		}
		
		
		//ExerciseResult fingerGripResult = new ExerciseResult(3,"Finger Grip",200, Double.parseDouble(score));
		/*if(started ==1){
			
		}
		*/
		//db.deleteResult(fingerGripResult);
		//System.out.println(db.getResult(3).toString());
		//db.addResult(result);
		//db.deleteResult(result);
		//System.out.println(started);
		//System.out.println(result.toString());
		//System.out.println(db.getResult(2).toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.elbow_extension_result, menu);
		return true;
	}

	public void goToExercisesPage(View view){
		Intent intent = new Intent(this, ExerciseListPage.class);
		startActivity(intent);
		finish();
	}
}
