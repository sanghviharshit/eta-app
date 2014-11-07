package com.nyu.cs9033.eta.database;

import java.util.ArrayList;
import java.util.List;

import com.nyu.cs9033.eta.models.Person;
import com.nyu.cs9033.eta.models.Trip;
import com.nyu.cs9033.eta.models.TripLocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
// TODO use Location api
//import android.location.Location;
import android.util.Log;

public class TripDatabaseHelper extends SQLiteOpenHelper {

	private static final String TAG = "TripDatabaseHelper";
	private static final int DATABASE_VERSION = 6;
	private static final String DATABASE_NAME = "eta";

	private static final String TABLE_TRIP = "trip";
	private static final String COLUMN_TRIP_ID = "_id"; // convention
	private static final String COLUMN_TRIP_NAME = "name"; // convention
	private static final String COLUMN_TRIP_TIME = "time";
	//private static final String COLUMN_TRIP_LOCATION = "location";
	//private static final String COLUMN_TRIP_FRIENDS = "friends";

	private static final String TABLE_TRIP_LOCATION = "location";
	private static final String COLUMN_LOC_TRIPID = "trip_id";
	private static final String COLUMN_LOC_NAME = "name";
	private static final String COLUMN_LOC_ADDRESS = "address";
	//TODO use location time stamp
	private static final String COLUMN_LOC_TIMESTAMP = "timestamp";
	private static final String COLUMN_LOC_LAT = "latitude";
	private static final String COLUMN_LOC_LNG = "longitude";
	//private static final String COLUMN_LOC_ALT = "altitude";
	//private static final String COLUMN_LOC_PROVIDER = "provider";

	private static final String TABLE_PERSON = "person";
	private static final String COLUMN_PERSON_ID = "_id";
	private static final String COLUMN_PERSON_TRIPID = "trip_id";
	private static final String COLUMN_PERSON_NAME = "name";
	private static final String COLUMN_PERSON_EMAIL = "email";
	private static final String COLUMN_PERSON_LOCATION_LAT = "latitude";
	private static final String COLUMN_PERSON_LOCATION_LNG = "longitude";


	private static final String CREATE_TRIP_TABLE = "CREATE TABLE " + TABLE_TRIP + "("
			+ COLUMN_TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_TRIP_NAME + " TEXT, "
			+ COLUMN_TRIP_TIME + " REAL)";
	
	private static final String CREATE_TRIP_LOCATION_TABLE = "CREATE TABLE " + TABLE_TRIP_LOCATION + "("
			+ COLUMN_LOC_TRIPID + " INTEGER PRIMARY KEY REFERENCES TRIP(_id), "
			+ COLUMN_LOC_NAME + " TEXT, "
			+ COLUMN_LOC_ADDRESS + " TEXT, "
			+ COLUMN_LOC_LAT + " REAL, "
			+ COLUMN_LOC_LNG + " REAL)";
	
	private static final String CREATE_PERSON_TABLE = "CREATE TABLE " + TABLE_PERSON + "("
			+ COLUMN_PERSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_PERSON_TRIPID + " INTEGER REFERENCES TRIP(_id), "
			+ COLUMN_PERSON_NAME + " TEXT, "
			+ COLUMN_PERSON_EMAIL + " TEXT, "
			+ COLUMN_PERSON_LOCATION_LAT + " REAL, "
			+ COLUMN_PERSON_LOCATION_LNG + " REAL)";
	
	public TripDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public TripDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	/*
	public TripDatabaseHelper(Context context, String name,
			CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}
	 */

	@Override
	public void onCreate(SQLiteDatabase db) {
		// create trip table
		Log.i(TAG,CREATE_TRIP_TABLE);
		db.execSQL(CREATE_TRIP_TABLE);
		//+ COLUMN_TRIP_FRIENDS + " TEXT)"

		// create location table
		Log.i(TAG,CREATE_TRIP_LOCATION_TABLE);
		db.execSQL(CREATE_TRIP_LOCATION_TABLE);
		//+ COLUMN_LOC_TIMESTAMP + " integer, "
		//+ COLUMN_LOC_ALT + " real, "
		//+ COLUMN_LOC_PROVIDER + " varchar(100)

		// create person table
		Log.i(TAG,CREATE_PERSON_TABLE);
		db.execSQL(CREATE_PERSON_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if exists
		Log.i(TAG,"==========>Resetting DB<==========");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP_LOCATION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
		// create tables again
		onCreate(db);
	}

	public long insertTrip(Trip trip) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_TRIP_NAME, trip.getTripName());
		cv.put(COLUMN_TRIP_TIME, trip.getTripTimeInMillis());
		// return id of new trip
		return getWritableDatabase().insert(TABLE_TRIP, null, cv);
	}

	public long insertTripLocation(long tripId, TripLocation tripLocation) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_LOC_TRIPID, tripId);
		//cv.put(COLUMN_LOC_TIMESTAMP, location.getTime());
		cv.put(COLUMN_LOC_NAME, tripLocation.getTripLocationName());
		cv.put(COLUMN_LOC_ADDRESS, tripLocation.getTripLocationAddress());
		cv.put(COLUMN_LOC_LAT, tripLocation.getTripLocationLat());
		cv.put(COLUMN_LOC_LNG, tripLocation.getTripLocationLong());
		//cv.put(COLUMN_LOC_ALT, location.getAltitude());
		//cv.put(COLUMN_LOC_PROVIDER, location.getProvider());
		// return id of new location
		return getWritableDatabase().insert(TABLE_TRIP_LOCATION, null, cv);
	}

	public void insertTripFriends(long tripId, ArrayList<Person> tripFriendsArray) {
		for(int i=0;i<tripFriendsArray.size();i++) {
			ContentValues cv = new ContentValues();
			cv.put(COLUMN_PERSON_TRIPID, tripId);
			//cv.put(COLUMN_LOC_TIMESTAMP, location.getTime());
			cv.put(COLUMN_PERSON_NAME, tripFriendsArray.get(i).getName());
			cv.put(COLUMN_PERSON_EMAIL, tripFriendsArray.get(i).getEmail());
			cv.put(COLUMN_PERSON_LOCATION_LAT, tripFriendsArray.get(i).getCurLocationLat());
			cv.put(COLUMN_PERSON_LOCATION_LNG, tripFriendsArray.get(i).getCurLocationLong());
			//cv.put(COLUMN_LOC_ALT, location.getAltitude());
			//cv.put(COLUMN_LOC_PROVIDER, location.getProvider());
			// return id of new location
			getWritableDatabase().insert(TABLE_PERSON, null, cv);
		}
	}

	public List<Trip> getAllTrips() {
		List<Trip> tripList = new ArrayList<Trip>();

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + TABLE_TRIP, null);

		// loop through all query results
		for (cursor.moveToFirst(); !cursor.isAfterLast(); 
				cursor.moveToNext()) {
			Trip trip = new Trip();
			trip.setTripId(cursor.getInt(0));
			trip.setTripName(cursor.getString(1));
			trip.setTripTimeInMillis(cursor.getLong(2));
			tripList.add(trip);
		}
		return tripList;
	}
	
	public TripLocation getTripLocation(long tripId) {
		TripLocation tripLocation = null;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + TABLE_TRIP_LOCATION + " where " + COLUMN_LOC_TRIPID + "=" + Long.toString(tripId), null);

		// loop through all query results
		if (cursor.moveToFirst()) {
			tripLocation = new TripLocation();
			tripLocation.setTripId(cursor.getInt(0));
			tripLocation.setTripLocationName(cursor.getString(1));
			tripLocation.setTripLocationAddress(cursor.getString(2));
			tripLocation.setTripLocationLat(cursor.getDouble(3));
			tripLocation.setTripLocationLong(cursor.getDouble(4));
		}
		return tripLocation;
	}
	
	public List<Person> getAllTripFriends(long tripId) {
		List<Person> tripFriendList = new ArrayList<Person>();

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + TABLE_PERSON + " where " + COLUMN_PERSON_TRIPID + "=" + Long.toString(tripId), null);

		// loop through all query results
		for (cursor.moveToFirst(); !cursor.isAfterLast(); 
				cursor.moveToNext()) {
			Person person = new Person();
			person.setPersonId(cursor.getInt(0));
			person.setTripId(cursor.getInt(1));
			person.setName(cursor.getString(2));
			person.setEmail(cursor.getString(3));
			tripFriendList.add(person);
		}
		return tripFriendList;
	}

	public Trip getTrip(long tripId) {
		Trip trip = null;

		SQLiteDatabase db = this.getReadableDatabase();
		Log.i(TAG,"select * from " + TABLE_TRIP + " where " + COLUMN_TRIP_ID + "=" + Long.toString(tripId));
		
		Cursor cursor = db.rawQuery("select * from " + TABLE_TRIP + " where " + COLUMN_TRIP_ID + "=" + Long.toString(tripId), null);

		if (cursor.moveToFirst()) {
			Log.w(TAG,"Found Trip");

			trip = new Trip();
			trip.setTripId(cursor.getInt(0));
			trip.setTripName(cursor.getString(1));
			trip.setTripTimeInMillis(cursor.getLong(2));
		}
		else {
			Log.w(TAG,"No Trip Found");
		}
		return trip;
	}
}
