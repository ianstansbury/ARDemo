package edu.westga.IanStansbury.SchoolARProject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Main class for launching App, contains background image and start button
 * 
 * @author ian
 * 
 */
public class SchoolARProjectActivity extends Activity {
	Button startButton;
	Button optionsButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		startButton = (Button) this.findViewById(R.id.startButton);

		startButton.getBackground().setColorFilter(0xFF00FF00,
				PorterDuff.Mode.MULTIPLY);

		// on click listener, starts building list activity
		startButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent launchMain = new Intent(SchoolARProjectActivity.this,
						BuildingListActivity.class);
				startActivity(launchMain);

			}
		});
	}
}