package com.nyu.cs9033.eta.controllers;

import com.nyu.cs9033.eta.models.Trip;
import com.nyu.cs9033.eta.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ViewTripActivity extends Activity {

	private static final String TAG = "ViewTripActivity";
	private Trip passedTrip;
	TextView msgViewTrip;
	TextView tripName;
	TextView tripDate;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trips_view);

		Intent fromIntent = getIntent();
		if (fromIntent.hasExtra(MainActivity.SAVED_TRIP)) {
			Bundle newTripBundle = getIntent().getExtras();
			passedTrip = (Trip) newTripBundle.getParcelable(MainActivity.SAVED_TRIP);
			System.out.println("--------Passed Trip Id: " + passedTrip.getTripId() + "---------");
			//message.setText("New Trip Saved");
		}
		
		Trip trip = getTrip(getIntent());
		viewTrip(passedTrip);
	}
	
	/**
	 * Create a Trip object via the recent trip that
	 * was passed to TripViewer via an Intent.
	 * 
	 * @param i The Intent that contains
	 * the most recent trip data.
	 * 
	 * @return The Trip that was most recently
	 * passed to TripViewer, or null if there
	 * is none.
	 */
	public Trip getTrip(Intent i) {
		
		if (i.hasExtra(MainActivity.SAVED_TRIP)) {
			/*
			Bundle newTripBundle = getIntent().getExtras();
			passedTrip = (Trip) newTripBundle.getParcelable(CreateTripActivity.NEW_TRIP);
			System.out.println("--------Passed Trip Id2: " + passedTrip.getTripId() + "---------");
			*/
			return passedTrip;
		}
		else {
			return null;
		}
	}

	/**
	 * Populate the View using a Trip model.
	 * 
	 * @param trip The Trip model used to
	 * populate the View.
	 */
	public void viewTrip(Trip trip) {
		if(trip == null) {
			msgViewTrip = (TextView) this.findViewById(R.id.msg_no_trip);
			msgViewTrip.setText("You have not added any trips yet.");
		}
			
		else {
			tripName = (TextView) this.findViewById(R.id.tripName);
			tripDate = (TextView) this.findViewById(R.id.trip_date);
			
			tripName.setText(passedTrip.getTripName());
			tripDate.setText(Long.toString(passedTrip.getTripTimeInMillis()));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO settings
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
