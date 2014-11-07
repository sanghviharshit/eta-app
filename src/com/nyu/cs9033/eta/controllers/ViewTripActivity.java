package com.nyu.cs9033.eta.controllers;

import java.util.ArrayList;
import java.util.Calendar;

import com.nyu.cs9033.eta.database.TripDatabaseHelper;
import com.nyu.cs9033.eta.models.Person;
import com.nyu.cs9033.eta.models.Trip;
import com.nyu.cs9033.eta.models.TripLocation;
import com.nyu.cs9033.eta.ui.FriendListRow;
import com.nyu.cs9033.eta.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ViewTripActivity extends Activity {
	private static final String TAG = "ViewTripActivity";

	TripDatabaseHelper db;
	
	private static Trip trip;
	private static TripLocation tripLocation;
	
	private ArrayList<Person> tripFriendsArray = new ArrayList<Person>();
	
	SharedPreferences sharedpreferences;

	private static long tripId;
	
	private static TextView tripDisplayName;
	private static TextView tripDisplayLocation;
	private static TextView tripDisplayDate;
	private static TextView tripDisplayTime;

	private static Button btnEditTrip;

	Calendar cal;

	private static int hour, minute = 0;
	private static int year, month, day = 0;
	
	private String locationName = "";
	/*
	private String locationAddress = "";
	private double locationLat;
	private double locationLong;
	*/ 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_view);
		setTitle("ETA: View Trip");
		initElements();
		
		findTrip();
		findTripLocation();
		findTripFriends();
		setNameOnView();
		setLocationOnView();
		setDateOnView();
		setTimeOnView();
		setFriendsOnView();
		
		addListenerOnButton();
	}

	private void initElements() {
		db = new TripDatabaseHelper(getApplicationContext());
		
		tripId = Long.parseLong((getIntent().getExtras().getString(TripHistoryActivity.TRIP_ID)));
		Log.i(TAG,"Trip Id passed is: " + tripId);
		sharedpreferences = getSharedPreferences(MainActivity.APP_PREFERENCES,
				Context.MODE_PRIVATE);

		tripDisplayName = (TextView) this.findViewById(R.id.tripName);
		tripDisplayLocation = (TextView) this.findViewById(R.id.tripLocation);
		tripDisplayDate = (TextView) this.findViewById(R.id.tripDate);
		tripDisplayTime = (TextView) this.findViewById(R.id.tripTime);

	}

	public void editTrip() {
		// TODO Edit Trip
		Toast.makeText(getApplicationContext(), "Not yet implemented", Toast.LENGTH_SHORT)
		.show();
	}

	/**
	 * @return whether the Trip was successfully saved.
	 */
	public boolean findTrip() {
		Log.i(TAG,"Looking up trip with id: " + tripId);
		trip = db.getTrip(tripId);
		if (trip == null) {
			Log.i(TAG,"Couldn't find trip with id: " + tripId);
			return false;
		}
		return true;
	}

	public boolean findTripLocation() {
		Log.i(TAG,"Looking up tripLocation with id: " + tripId);
		tripLocation = db.getTripLocation(tripId);
		return true;
	}
	
	public boolean findTripFriends() {
		tripFriendsArray = (ArrayList<Person>) db.getAllTripFriends(tripId);
		return true;
	}
	
	public void addListenerOnButton() {
		btnEditTrip = (Button) findViewById(R.id.btnUpdateTrip);

		btnEditTrip.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO
				editTrip();
			}
		});
	}

	public void setNameOnView() {
		tripDisplayName.setText(trip.getTripName());
	}
	
	public void setLocationOnView() {
		tripDisplayLocation.setText(tripLocation.getTripLocationName() + ", " + tripLocation.getTripLocationAddress());
	}
	
	// display current date
	public void setDateOnView() {
		cal = Calendar.getInstance();
		cal.setTimeInMillis(trip.getTripTimeInMillis());
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);

		// set current date into textview
		tripDisplayDate.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(month + 1).append("/").append(day).append("/")
				.append(year).append(" "));

	}

	// display current time
	public void setTimeOnView() {
		cal = Calendar.getInstance();
		cal.setTimeInMillis(trip.getTripTimeInMillis());
		tripDisplayTime = (TextView) findViewById(R.id.tripTime);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);

		// set current time into textview
		tripDisplayTime.setText(new StringBuilder().append(pad(hour))
				.append(":").append(pad(minute)));

	}

	public void setFriendsOnView() {
		LinearLayout parent = (LinearLayout) findViewById(R.id.tripAddedFriends);
		
		for(int i=0;i<tripFriendsArray.size();i++) {
			// create the Box and added to the parent above
			FriendListRow friend = new FriendListRow(this, tripFriendsArray.get(i).getEmail());
			parent.addView(friend);
		}
	}
	
	
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO settings
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}