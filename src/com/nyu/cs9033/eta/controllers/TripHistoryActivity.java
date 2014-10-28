package com.nyu.cs9033.eta.controllers;

import java.util.ArrayList;

import com.nyu.cs9033.eta.R;
import com.nyu.cs9033.eta.database.TripDatabaseHelper;
import com.nyu.cs9033.eta.models.Person;
import com.nyu.cs9033.eta.models.Trip;
import com.nyu.cs9033.eta.models.TripLocation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class TripHistoryActivity extends Activity {

	private ArrayList<Trip> trips = new ArrayList<Trip>();
	TripDatabaseHelper db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_all);
		final ListView listViewTrips = (ListView) findViewById(R.id.Layout_Trip_all);

		db = new TripDatabaseHelper(getApplicationContext());

		// our adapter instance
		trips = (ArrayList) db.getAllTrips();
		for(int i=0;i<trips.size();i++) {
			TripLocation tmpTripLocation = db.getTripLocation(trips.get(i).getTripId());
			trips.get(i).setTripLocation(tmpTripLocation);
		}
		// personData[0] = new Person("Heta1","shethheta1991@gmail.com");
		// personData[1] = new Person("Heta2","shethheta1992@gmail.com");
		// personData[2] = new Person("Heta3","shethheta1993@gmail.com");

		ArrayAdapterTrips adapter = new ArrayAdapterTrips(this, R.layout.trip_row, trips);
		// create a new ListView, set the adapter and item click listener
		// ListView listViewTrips = new ListView(this);
		// ListView listViewPersons = (ListView)
		// this.findViewById(R.id.tripAddedFriends);
		listViewTrips.setAdapter(adapter);
		// LinearLayout mainLayout = (LinearLayout)
		// this.findViewById(R.id.Layout_Trip_add);
		// mainLayout.addView(listViewPersons);
		// put the ListView in the Screen
		// listViewItems.setOnItemClickListener(new
		// OnItemClickListenerListViewItem());

		listViewTrips.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				final Trip tripItem = (Trip) parent.getItemAtPosition(position);		
				//Toast.makeText(getApplicationContext(), "Click ListItem Number " + position, Toast.LENGTH_SHORT)
				//.show();
				Toast.makeText(getApplicationContext(), "Clicked trip: " + tripItem.getTripName(), Toast.LENGTH_SHORT)
				.show();
			}
		});
		
	}

}
