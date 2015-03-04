/**
 * 
 */
package edu.westga.IanStansbury.SchoolARProject;

import system.ArActivity;
import geo.GeoObj;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Building list, when a location is clicked opens corresponding ProjectSetup 
 * 
 * @author ian
 *
 */
public class BuildingListActivity extends ListActivity{
	


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //sets list elements to the String buildings
        setListAdapter(new ArrayAdapter<String>(this,
        		R.layout.buildings, this.buildings));
        
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setTextFilterEnabled(true);
        
        getListView().setOnItemClickListener(new OnItemClickListener() {

        	/**
        	 * on click switch for calling the setup activity
        	 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch(arg2){
				case 0://TLC
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.574901, -85.098103, 322)));
					break;
				case 1://Boyd
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.573460, -85.096698, 323)));
					break;	
				case 2://Cobb
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.572109, -85.096022, 330)));
					break;
				case 3://UCC
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.574913, -85.099327, 320)));
					break;
				case 4://Humanities
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.574133, -85.096258, 321)));
					break;
				case 5://Pafford
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.574153, -85.097198, 321)));
					break;
				case 6://Library
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.573513, -85.097875, 322)));
					break;
				case 7://Biology
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.573095, -84.103901, 319)));
					break;
				case 8://education
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.74711, -84.898254, 318)));
					break;
				case 9://Business
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.572777, -85.096972, 327)));
					break;	
				case 10://home
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.747069, -84.898543, 319)));
					break;
				case 11://curent location
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.747417, -84.89858, 319)));
					break;
				case 12:
					ArActivity.startWithSetup(BuildingListActivity.this, new ProjectSetup(new GeoObj(33.74711, -84.898254)));
					break;
				}
				
			}
		});
        
    
        
    }
    
    /**
     * inflator for options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.uptionsmenu, menu);
		return true;
    	
    }
    
    /**
     * on click for options menu
     * sends to other correct list or toasts already on the correct menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case R.id.buildingslist:
    Toast.makeText(getApplicationContext(), "Already on this menu",
    Toast.LENGTH_SHORT).show();
    return true;
    case R.id.administrationlist:
		Intent launch = new Intent(BuildingListActivity.this, AdminListActivity.class);
		startActivity(launch);
    return true;
    case R.id.facultylist:
		Intent launch2 = new Intent(BuildingListActivity.this, FacultyListActivity.class);
		startActivity(launch2);
    return true;
    default:
    return super.onOptionsItemSelected(item);
    }
    }

    
	// String array for loading into list adapter with all needed names
	private String[] buildings = new String[] {"TLC", "Boyd", "Cobb", "UCC", "Humanities", "Pafford", "Library", "Biology", "Education", "Richards College of Business", "home test", "Location Tests"};


	
	
}
