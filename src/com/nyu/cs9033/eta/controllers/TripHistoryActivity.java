package com.nyu.cs9033.eta.controllers;

import java.util.ArrayList;

import com.nyu.cs9033.eta.R;
import com.nyu.cs9033.eta.database.TripDatabaseHelper;
import com.nyu.cs9033.eta.models.Person;
import com.nyu.cs9033.eta.models.Trip;
import com.nyu.cs9033.eta.models.TripLocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class TripHistoryActivity extends Activity {

	private static final String TAG = "TripHistoryActivity";
	private ArrayList<Trip> trips = new ArrayList<Trip>();
	TripDatabaseHelper db;
	public static final String TRIP_ID = "Trip Id";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_all);
		setTitle("ETA: All Trips");
		final ListView listViewTrips = (ListView) findViewById(R.id.Layout_Trip_all);

		db = new TripDatabaseHelper(getApplicationContext());

		// our adapter instance
		trips = (ArrayList) db.getAllTrips();
		for(int i=0;i<trips.size();i++) {
			TripLocation tmpTripLocation = db.getTripLocation(trips.get(i).getTripId());
			trips.get(i).setTripLocation(tmpTripLocation);
		}

		ArrayAdapterTrips adapter = new ArrayAdapterTrips(this, R.layout.trip_row, trips);
		listViewTrips.setAdapter(adapter);
		listViewTrips.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				final Trip tripItem = (Trip) parent.getItemAtPosition(position);		
				//Toast.makeText(getApplicationContext(), "Click ListItem Number " + position, Toast.LENGTH_SHORT)
				//.show();
				//Toast.makeText(getApplicationContext(), "Clicked trip: " + tripItem.getTripName(), Toast.LENGTH_SHORT)
				//.show();
				Log.i(TAG,"Clicked on Trip ID: "+ tripItem.getTripId());
				Intent startViewTrip = new Intent(getApplicationContext(), ViewTripActivity.class);
				startViewTrip.putExtra(TRIP_ID, Long.toString(tripItem.getTripId()));
				startActivity(startViewTrip);
				
			}
		});
		
	}

}
