/**
 * 
 */
package edu.westga.IanStansbury.SchoolARProject;

import android.provider.BaseColumns;

/**
 * @author ian
 *
 */
public final class LocationDatabase {

	/**
	 * 
	 */
	public LocationDatabase() {
		// TODO Auto-generated constructor stub
	}
	
	static final class Locations implements BaseColumns{
		
		public Locations(){
			
		}
		
		public static final String LOCATIONS_TABLE_NAME = "locations";
		public static final String ID= "_id";
		public static final String BUILDING_NAME = "name";
		public static final String LATITUDE= "latitude";
		public static final String LONGITUDE= "longitude";
	}
	
	

}
