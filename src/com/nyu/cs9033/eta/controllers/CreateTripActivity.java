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

public class CreateTripActivity extends FragmentActivity {
	public final static Integer MAX_ALLOWED_FRIENDS = 10;

	private static final String TAG = "CreateTripActivity";
	public final static String NEW_TRIP = "New Trip Object";

	TripDatabaseHelper db;
	
	// private static Trip trip;
	private ArrayList<Person> tripFriendsArray = new ArrayList<Person>();
	private Person newFriend;
	// private static Person[] personData = new Person[MAX_ALLOWED_FRIENDS];
	// private static Integer tripId = 0;
	private static Integer numTripFriend = 0;

	SharedPreferences sharedpreferences;

	private static long tripId;
	private static long tripLocationId;
	
	private static Trip newTrip;
	private static TripLocation newTripLocation;
	private static TextView tripDisplayName;
	private static TextView tripDisplayLocation;
	private static TextView tripDisplayDate;
	private static TextView tripDisplayTime;
	// private DatePicker dpResult;
	private static Button btnChangeDate;
	private static Button btnChangeTime;
	private static Button btnAddFriend;
	private static Button btnChangeLocation;
	private static Button btnCreateTrip;

	// private TimePicker tripTimePicker;
	static Calendar cal;

	private static int hour, minute = 0;
	private static int year, month, day = 0;
	private String locationName = "";
	private String locationAddress = "";
	private double locationLat;
	private double locationLong;

	public static final int PICK_CONTACT = 2;
	public static final int PICK_LOCATION = 3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_create);

		initElements();
		setCurrentDateOnView();
		setCurrentTimeOnView();
		addListenerOnButton();
	}

	private void initElements() {
		
		sharedpreferences = getSharedPreferences(MainActivity.APP_PREFERENCES,
				Context.MODE_PRIVATE);

		tripDisplayName = (TextView) this.findViewById(R.id.tripName);
		tripDisplayLocation = (TextView) this.findViewById(R.id.tripLocation);
		tripDisplayDate = (TextView) this.findViewById(R.id.tripDate);
		tripDisplayTime = (TextView) this.findViewById(R.id.tripTime);
		cal = Calendar.getInstance();
		// our adapter instance

		// personData[0] = new Person("Heta1","shethheta1991@gmail.com");
		// personData[1] = new Person("Heta2","shethheta1992@gmail.com");
		// personData[2] = new Person("Heta3","shethheta1993@gmail.com");

		// ArrayAdapterPersons adapter = new ArrayAdapterPersons(this,
		// R.layout.list_view_row_person, personData);
		// create a new ListView, set the adapter and item click listener
		// ListView listViewPersons = new ListView(this);
		// ListView listViewPersons = (ListView)
		// this.findViewById(R.id.tripAddedFriends);
		// listViewPersons.setAdapter(adapter);
		// LinearLayout mainLayout = (LinearLayout)
		// this.findViewById(R.id.Layout_Trip_add);
		// mainLayout.addView(listViewPersons);
		// put the ListView in the Screen
		// listViewItems.setOnItemClickListener(new
		// OnItemClickListenerListViewItem());

	}

	/**
	 * This method should be used to instantiate a Trip model object.
	 * 
	 * @return The Trip as represented by the View.
	 */
	public void createTrip() {

		/*
		 * TO-Do tripDate = (DatePicker) this.findViewById(R.id.trip_date);
		 * tripTime = (TimePicker) this.findViewById(R.id.trip_time);
		 * tripFriends = (TextView) this.findViewById(R.id.trip_friends);
		 */

		newTrip = new Trip(tripDisplayName.getText().toString());
		newTrip.setTripTimeInMillis(year, month, day, hour, minute);
		newTripLocation = new TripLocation(locationName, locationAddress,
				locationLat, locationLong);
		newTrip.setTripLocation(newTripLocation);
		if (sharedpreferences.contains(MainActivity.startLocation)) {
			newTrip.setStartLocation(sharedpreferences.getString(
					MainActivity.startLocation, ""));
		}
		newTrip.setTripFriendsArray(tripFriendsArray);
		// Toast.makeText(this, (CharSequence)
		// Long.toString(newTrip.getTripTimeInMillis()),
		// Toast.LENGTH_LONG).show();
		newTrip.displayTrip();
	}

	/**
	 * @return whether the Trip was successfully saved.
	 */
	public boolean saveTrip() {

		// TODO: Save in Database
		
		db = new TripDatabaseHelper(getApplicationContext());
		tripId = db.insertTrip(newTrip);
		tripLocationId = db.insertTripLocation(tripId, newTripLocation);
		db.insertTripFriends(tripId, tripFriendsArray);
		
		Intent returnIntent = new Intent(this, MainActivity.class);
		// Pass the Trip object to main activity
		// returnIntent.putExtra(NEW_TRIP, newTrip);

		setResult(RESULT_OK, returnIntent);
		finish();
		return true;
	}

	/**
	 * This method should be used when a user wants to cancel the creation of a
	 * Trip.
	 * 
	 * Note: You most likely want to call this if your activity dies during the
	 * process of a trip creation or if a cancel/back button event occurs.
	 * Should return to the previous activity without a result using finish()
	 * and setResult().
	 */
	public void cancelTrip() {

		// TODO - fill in here
	}

	public void addListenerOnButton() {

		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
		btnChangeTime = (Button) findViewById(R.id.btnChangeTime);
		btnAddFriend = (Button) findViewById(R.id.btnAddFriend);
		btnChangeLocation = (Button) findViewById(R.id.btnChangeLocation);
		btnCreateTrip = (Button) findViewById(R.id.btnCreateTrip);

		btnChangeDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDatePickerDialog(v);
			}
		});
		btnChangeTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showTimePickerDialog(v);
			}
		});

		btnAddFriend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK,
						People.CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT);
			}
		});
		btnChangeLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String locationArea = "";
				String locationAreaType = "";
				EditText locationAreaText = (EditText) findViewById(R.id.tripArea);
				EditText locationAreaTyprText = (EditText) findViewById(R.id.tripAreaType);

				locationArea = locationAreaText.getText().toString();
				locationAreaType = locationAreaTyprText.getText().toString();

				if (locationArea.length() == 0
						|| locationAreaType.length() == 0) {
					Context context = getApplicationContext();
					CharSequence text = "Please enter location area and area type.";
					int duration = Toast.LENGTH_LONG;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();

				} else {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri
							.parse("location://com.example.nyu.hw3api"));
					intent.putExtra("searchVal", locationArea + "::"
							+ locationAreaType);
					startActivityForResult(intent, PICK_LOCATION);
				}
			}
		});

		btnCreateTrip.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createTrip();
				saveTrip();
			}
		});
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}

	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(getSupportFragmentManager(), "timePicker");
	}

	// display current date
	public void setCurrentDateOnView() {
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
	public void setCurrentTimeOnView() {

		tripDisplayTime = (TextView) findViewById(R.id.tripTime);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);

		// set current time into textview
		tripDisplayTime.setText(new StringBuilder().append(pad(hour))
				.append(":").append(pad(minute)));

	}

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			Log.i(TAG, "onCreateDialog");
			// int year = cal.get(Calendar.YEAR);
			// int month = cal.get(Calendar.MONTH);
			// int day = cal.get(Calendar.DAY_OF_MONTH);
			//
			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			// Do something with the date chosen by the user
			Log.i(TAG, "onDateSet");
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			// set selected date into textview
			tripDisplayDate.setText(new StringBuilder().append(month + 1)
					.append("/").append(day).append("/").append(year)
					.append(" "));
		}
	}

	public static class TimePickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker

			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
		}

		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			// Do something with the time chosen by the user
			hour = selectedHour;
			minute = selectedMinute;
			// set current time into textview
			tripDisplayTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute)));

		}
	}

	/*
	 * Code used from
	 * http://code.tutsplus.com/tutorials/android-essentials-using
	 * -the-contact-picker--mobile-2017
	 */
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);
		if (resultCode == RESULT_OK && reqCode == PICK_CONTACT) {
			Cursor cursor = null;
			String name = "";
			String email = "";
			try {

				Uri result = data.getData();
				Log.v(TAG, "Got a contact result: " + result.toString());

				// get the contact id from the Uri
				String id = result.getLastPathSegment();

				// query for everything email
				cursor = getContentResolver().query(Email.CONTENT_URI, null,
						Email.CONTACT_ID + "=?", new String[] { id }, null);
				int emailIdx = cursor.getColumnIndex(Email.DATA);

				// let's just get the first email
				if (cursor.moveToFirst()) {
					email = cursor.getString(emailIdx);
					Log.v(TAG, "Got email: " + email);
				} else {
					Log.w(TAG, "No email");
				}
				cursor = getContentResolver().query(result, null, null, null,
						null);
				if (cursor.moveToFirst()) {
					name = cursor
							.getString(cursor
									.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					Log.v(TAG, "Got name: " + name);
				} else {
					Log.w(TAG, "No name");
				}
			} catch (Exception e) {
				Log.e(TAG, "Failed to get name/email data", e);
			} finally {
				if (cursor != null) {
					cursor.close();
				}
				if (email.length() == 0 || name.length() == 0) {
					Toast.makeText(this, "No name/email found for contact.",
							Toast.LENGTH_LONG).show();
				} else {
					if (numTripFriend < MAX_ALLOWED_FRIENDS) {
						LinearLayout parent = (LinearLayout) findViewById(R.id.tripAddedFriends);
						// create the Box and added to the parent above
						FriendListRow friend = new FriendListRow(this, name); // if
																				// you
																				// are
																				// in
																				// an
																				// Activity
						// some LayoutParams to replicate your xml attributes
						// LinearLayout.LayoutParams params = new
						// LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
						// LinearLayout.LayoutParams.WRAP_CONTENT);
						// set the Orientation for the Box
						// add the Box
						parent.addView(friend);
						newFriend = new Person(name, email);
						tripFriendsArray.add(newFriend);
						numTripFriend++;
					} else {
						Toast.makeText(
								this,
								"Sorry, You can't add any more friends to this trip",
								Toast.LENGTH_LONG).show();
					}
				}
			}
		} else if (reqCode == PICK_LOCATION) {
			Bundle bundle = data.getExtras();
			for (String key : bundle.keySet()) {
				Object value = bundle.get(key);
				Log.d(TAG, String.format("%s %s (%s)", key, value.toString(),
						value.getClass().getName()));
			}

			ArrayList<String> locationArray = data.getExtras()
					.getStringArrayList("retVal");
			CharSequence text = locationArray.get(0) + ", "
					+ locationArray.get(1);
			// Toast.makeText(this, text, Toast.LENGTH_LONG).show();
			tripDisplayLocation.setText(text);
			locationName = locationArray.get(0);
			locationAddress = locationArray.get(1);
			locationLat = Double.parseDouble(locationArray.get(2));
			locationLong = Double.parseDouble(locationArray.get(3));

		} else {
			Toast.makeText(
					this,
					(CharSequence) "The app encountered an error, please try again",
					Toast.LENGTH_LONG).show();
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