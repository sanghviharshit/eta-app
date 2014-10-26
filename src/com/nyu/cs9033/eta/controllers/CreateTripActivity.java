package com.nyu.cs9033.eta.controllers;

import java.util.Calendar;

import com.nyu.cs9033.eta.models.Trip;
import com.nyu.cs9033.eta.R;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


public class CreateTripActivity extends FragmentActivity {
	public final static Integer MAX_ALLOWED_TRIPS = 20;
	private static final String TAG = "CreateTripActivity";
	public final static String NEW_TRIP = "New Trip Object";
	
	private static Trip trips[] = new Trip[MAX_ALLOWED_TRIPS];
	private static Trip newTrip;
	private static Integer tripId = 0;
	private static TextView tripDisplayName;
	private static TextView tripDisplayLocation;
	private static TextView tripDisplayDate;
	private static TextView tripDisplayTime;
	private static TextView tripFriends[];
	//private DatePicker dpResult;
	private static Button btnChangeDate;
	private static Button btnChangeTime;
	 
	//private TimePicker tripTimePicker;
	static Calendar cal;
	
	private static int hour, minute = 0;
	private static int year, month, day = 0;

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
		tripDisplayName = (TextView) this.findViewById(R.id.tripName);
		tripDisplayLocation = (TextView) this.findViewById(R.id.tripLocation);
		tripDisplayDate = (TextView) this.findViewById(R.id.tripDate);
		tripDisplayTime = (TextView) this.findViewById(R.id.tripTime);
		cal = Calendar.getInstance();
	}

	/**
	 * This method should be used to
	 * instantiate a Trip model object.
	 * 
	 * @return The Trip as represented
	 * by the View.
	 */
	public Trip createTrip() {
				
		/* TO-Do
		tripDate = (DatePicker) this.findViewById(R.id.trip_date);
		tripTime = (TimePicker) this.findViewById(R.id.trip_time);
		tripFriends = (TextView) this.findViewById(R.id.trip_friends);
		*/
		
		trips[tripId] = new Trip(tripDisplayName.getText().toString(),tripDisplayLocation.getText().toString(),
				Integer.toString(year), tripDisplayTime.toString());
		newTrip = trips[tripId];
		tripId++;
		return newTrip;
	}

	/**
	 * @return whether the Trip was successfully 
	 * saved.
	 */
	public boolean saveTrip(View view) {	
		createTrip();
		Intent returnIntent = new Intent(this, MainActivity.class);
		
		returnIntent.putExtra(NEW_TRIP, newTrip);
		/*
		Bundle newTripBundle = new Bundle();
        newTripBundle.putParcelable(NEW_TRIP, newTrip);  
        main.putExtras(newTripBundle);
        */
		setResult(RESULT_OK, returnIntent);
        finish();
		return true;
	}

	/**
	 * This method should be used when a
	 * user wants to cancel the creation of
	 * a Trip.
	 * 
	 * Note: You most likely want to call this
	 * if your activity dies during the process
	 * of a trip creation or if a cancel/back
	 * button event occurs. Should return to
	 * the previous activity without a result
	 * using finish() and setResult().
	 */
	public void cancelTrip() {
	
		// TODO - fill in here
	}
	
	public void addListenerOnButton() {
		 
		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
		btnChangeTime = (Button) findViewById(R.id.btnChangeTime);
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
			.append(month + 1).append("-").append(day).append("-")
			.append(year).append(" "));
 
	}
	
	// display current time
	public void setCurrentTimeOnView() {
 
		tripDisplayTime = (TextView) findViewById(R.id.tripTime);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);
 
		// set current time into textview
		tripDisplayTime.setText(
                    new StringBuilder().append(pad(hour))
                                       .append(":").append(pad(minute)));

	}
	
	public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener 
	{
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) 
		{
			// Use the current date as the default date in the picker
			Log.i(TAG, "onCreateDialog");
//			int year = cal.get(Calendar.YEAR);
//			int month = cal.get(Calendar.MONTH);
//			int day = cal.get(Calendar.DAY_OF_MONTH);
//			
			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) 
		{
			// Do something with the date chosen by the user
			Log.i(TAG, "onDateSet");
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			// set selected date into textview
			tripDisplayDate.setText(new StringBuilder().append(month + 1)
			   .append("-").append(day).append("-").append(year)
			   .append(" "));			
		}
	}
	
	public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			
			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
		}
			
		public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
			// Do something with the time chosen by the user
			hour = selectedHour;
			minute = selectedMinute;
			// set current time into textview
			tripDisplayTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute)));

		}
	}

	private static String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else
		   return "0" + String.valueOf(c);
	}
}