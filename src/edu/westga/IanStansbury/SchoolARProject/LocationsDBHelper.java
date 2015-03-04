/**
 * 
 */
package edu.westga.IanStansbury.SchoolARProject;

import edu.westga.IanStansbury.SchoolARProject.LocationDatabase.Locations;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author ian
 *
 */
public class LocationsDBHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "locations.db";
	private static final int DATABASE_VERSION = 1;
	
	public LocationsDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
	db.execSQL("CREATE TABLE " + Locations.LOCATIONS_TABLE_NAME + " ("
			+ Locations.ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ Locations.BUILDING_NAME + " TEXT,"
			+ Locations.LATITUDE + " TEXT"
			+ Locations.LONGITUDE + " TEXT"
			+ ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
}
