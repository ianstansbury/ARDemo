
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
 * Administration List, when a location is clicked opens corresponding ProjectSetup 
 * 
 * @author ian
 * 
 */
public class AdminListActivity extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//sets list elements to the String buildings
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
				case 0:// Mandeville
					ArActivity.startWithSetup(AdminListActivity.this,
							new ProjectSetup(new GeoObj(33.571266, -85.098664,
									330)));
					break;
				case 1:// Excel
					ArActivity.startWithSetup(AdminListActivity.this,
							new ProjectSetup(new GeoObj(33.572109, -85.096022,
									330)));
					break;
				case 2:// Health Services
					ArActivity.startWithSetup(AdminListActivity.this,
							new ProjectSetup(new GeoObj(33.571921, -85.098759,
									327)));
					break;
				case 3:// UZ6
					ArActivity.startWithSetup(AdminListActivity.this,
							new ProjectSetup(new GeoObj(33.572658, -85.105679,
									318)));
					break;
				case 4:// Parking
					ArActivity.startWithSetup(AdminListActivity.this,
							new ProjectSetup(new GeoObj(33.571976, -85.099870,
									321)));
					break;
				case 5:// Career Services
					ArActivity.startWithSetup(AdminListActivity.this,
							new ProjectSetup(new GeoObj(33.574153, -85.097198,
									327)));
					break;
				case 6:// University Police
					ArActivity.startWithSetup(AdminListActivity.this,
							new ProjectSetup(new GeoObj(33.571976, -85.099870,
									321)));
					break;
				case 7:// My House
					ArActivity.startWithSetup(AdminListActivity.this,
							new ProjectSetup(new GeoObj(33.571421, -85.098935,
									328)));
					break;
				case 8:
					ArActivity.startWithSetup(AdminListActivity.this,
							new ProjectSetup(new GeoObj(33.74711, -84.898254)));
					break;
				case 9:
					ArActivity.startWithSetup(AdminListActivity.this,
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
			Intent launch = new Intent(AdminListActivity.this,
					BuildingListActivity.class);
			startActivity(launch);
			return true;
		case R.id.administrationlist:
			Toast.makeText(getApplicationContext(), "Already on this menu",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.facultylist:
			Intent launch2 = new Intent(AdminListActivity.this,
					FacultyListActivity.class);
			startActivity(launch2);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// String array for loading into list adapter with all needed names
	private String[] buildings = new String[] { "Admissions", "Excel Center",
			"Health Services", "Z6", "Financial Aid", "Parking Services",
			"Career Services", "University Police" };

}
