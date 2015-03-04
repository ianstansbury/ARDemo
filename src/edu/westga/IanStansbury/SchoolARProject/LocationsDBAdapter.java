/**
 * 
 */
package edu.westga.IanStansbury.SchoolARProject;

import edu.westga.IanStansbury.SchoolARProject.LocationDatabase.Locations;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author ian
 *
 */
public class LocationsDBAdapter {
	
	private LocationsDBHelper databaseHelper = null;
	private SQLiteDatabase theDB = null;
	private Context context = null;
	
	public LocationsDBAdapter(Context context) {
		this.context = context;
	}
	
	public LocationsDBAdapter open() throws SQLException {
		this.databaseHelper = new LocationsDBHelper(this.context);
		this.theDB = this.databaseHelper.getWritableDatabase();
		return this;
		}
	
	public void close() {
		this.databaseHelper.close();
	}
	
	public Cursor fetchAllLocations(){
		String[] columns = new String[] {Locations.ID, Locations.BUILDING_NAME,
				Locations.LATITUDE, Locations.LONGITUDE};
				return this.theDB.query(Locations.LOCATIONS_TABLE_NAME, columns,
				null, null, null, null, null);
	}
	
	public Cursor fetchLocations(long rowId){
		String[] columns = new String[] {Locations.ID, Locations.BUILDING_NAME,
				Locations.LATITUDE, Locations.LONGITUDE};
				Cursor mCursor = this.theDB.query(true, Locations.BUILDING_NAME,
				columns, Locations.ID + "=" + rowId, null, null, null, null, null);
				// If have a Cursor move to first record
				if (mCursor != null) {
				mCursor.moveToFirst();
				}
				return mCursor;
	}

}
