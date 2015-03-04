/**
 * 
 */
package edu.westga.IanStansbury.SchoolARProject;


import system.ArActivity;
import edu.westga.IanStansbury.SchoolARProject.LocationDatabase.Locations;
import geo.GeoObj;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Faculty List, when a location is clicked opens corresponding ProjectSetup 
 * 
 * @author ian
 * 
 */
public class FacultyListActivity extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// creates list view with buildings string array
		setListAdapter(new ArrayAdapter<String>(this, R.layout.buildings,
				this.buildings));

		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		getListView().setTextFilterEnabled(true);

		getListView().setOnItemClickListener(new OnItemClickListener() {

			
        	/**
        	 * on click switch for calling the setup activity
        	 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:// Yoder
					ArActivity.startWithSetup(FacultyListActivity.this,
							new ProjectSetup(new GeoObj(33.574901, -85.098103,
									322)));
					break;
				case 1:// Adel
					ArActivity.startWithSetup(FacultyListActivity.this,
							new ProjectSetup(new GeoObj(33.574901, -85.098103,
									322)));
					break;
				case 2:// Hibbard(humanities)
					ArActivity.startWithSetup(FacultyListActivity.this,
							new ProjectSetup(new GeoObj(33.574133, -85.096258,
									321)));
					break;
				case 3:// Blair(cobb)
					ArActivity.startWithSetup(FacultyListActivity.this,
							new ProjectSetup(new GeoObj(33.572109, -85.096022,
									330)));
					break;
				case 4:// Humanities
					ArActivity
							.startWithSetup(FacultyListActivity.this,
									new ProjectSetup(new GeoObj(33.574133,
											-85.096258)));
					break;
				case 5:// Pafford
					ArActivity
							.startWithSetup(FacultyListActivity.this,
									new ProjectSetup(new GeoObj(33.574153,
											-85.097198)));
					break;
				case 6:// Library
					ArActivity
							.startWithSetup(FacultyListActivity.this,
									new ProjectSetup(new GeoObj(33.573513,
											-85.097875)));
					break;
				case 7:// My House
					ArActivity
							.startWithSetup(FacultyListActivity.this,
									new ProjectSetup(new GeoObj(33.746968,
											-84.898335)));
					break;
				case 8:
					ArActivity.startWithSetup(FacultyListActivity.this,
							new ProjectSetup(new GeoObj(33.74711, -84.898254)));
					break;
				case 9:
					ArActivity.startWithSetup(FacultyListActivity.this,
							new ProjectSetup(new GeoObj(33.74711, -84.898254)));
					break;
				}

			}
		});

	}

	/**
	 * inflator for options menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.uptionsmenu, menu);
		return true;

	}

	/**
	 * on click for options menu sends to other correct list or toasts already
	 * on the correct menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.buildingslist:
			Intent launch = new Intent(FacultyListActivity.this,
					BuildingListActivity.class);
			startActivity(launch);
			return true;
		case R.id.administrationlist:
			Intent launch2 = new Intent(FacultyListActivity.this,
					AdminListActivity.class);
			startActivity(launch2);
			return true;
		case R.id.facultylist:
			Toast.makeText(getApplicationContext(), "Already on this menu",
					Toast.LENGTH_SHORT).show();

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// String array for loading into list adapter with all needed names
	private String[] buildings = new String[] { "Dr. Yoder", "Dr. Adel",
			"Dr. Hiddard(humanities)", "Dr. Blair(cobb)" };

}
