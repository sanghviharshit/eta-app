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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class CreateTripActivity extends Activity {
	public final static Integer MAX_ALLOWED_TRIPS = 20;
	private static final String TAG = "CreateTripActivity";
	public final static String NEW_TRIP = "New Trip Object";
	
	private Trip trips[] = new Trip[MAX_ALLOWED_TRIPS];
	private Trip newTrip;
	private static Integer tripId = 0;
	private TextView tripDisplayName;
	private TextView tripDisplayLocation;
	private TextView tripDisplayDate;
	private TextView tripDisplayTime;
	private TextView tripFriends[];
	//private DatePicker dpResult;
	private Button btnChangeDate;
	private Button btnChangeTime;
	 
	//private TimePicker tripTimePicker;
	
	private int hour, minute = 0;
	private int year, month, day = 0;
	
	static final int DATE_DIALOG_ID = 998;
	static final int TIME_DIALOG_ID = 999;
	 
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
	 * For HW2 you should treat this method as a 
	 * way of sending the Trip data back to the
	 * main Activity.
	 * 
	 * Note: If you call finish() here the Activity 
	 * will end and pass an Intent back to the
	 * previous Activity using setResult().
	 * 
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
				showDialog(DATE_DIALOG_ID);
			}
		});
		btnChangeTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});
	}
	
	// display current date
	public void setCurrentDateOnView() {
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		
		// set current date into textview
		tripDisplayDate.setText(new StringBuilder()
			// Month is 0 based, just add 1
			.append(month + 1).append("-").append(day).append("-")
			.append(year).append(" "));
 
	}
	
	// display current time
	public void setCurrentTimeOnView() {
 
		tripDisplayTime = (TextView) findViewById(R.id.tripTime);
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
 
		// set current time into textview
		tripDisplayTime.setText(
                    new StringBuilder().append(pad(hour))
                                       .append(":").append(pad(minute)));
 
//		// set current time into timepicker
//		timePicker1.setCurrentHour(hour);
//		timePicker1.setCurrentMinute(minute);
// 
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, datePickerListener, year, month, day);

		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, 
	                                    timePickerListener, hour, minute, false);
	
		}
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener datePickerListener = 
			new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
			int selectedMonth, int selectedDay) {
				year = selectedYear;
				month = selectedMonth;
				day = selectedDay;
				

				String tripDateText = year + "/" + (month+1) + "/" + day;
				
				System.out.println("YYYY/MM/DD: " + tripDateText);
				
				// set current date into textview
				tripDisplayDate.setText(tripDateText);
				
				// set selected date into textview
//				tripDate.setText(new StringBuilder().append(month + 1)
//				   .append("-").append(day).append("-").append(year)
//				   .append(" "));
//				
				// set selected date into datepicker also
				//dpResult.init(year, month, day, null);			
		}
	};
	
	private TimePickerDialog.OnTimeSetListener timePickerListener = 
			new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
			int selectedMinute) {
				hour = selectedHour;
				minute = selectedMinute;
	 
				// set current time into textview
				tripDisplayTime.setText(new StringBuilder().append(pad(hour))
						.append(":").append(pad(minute)));
	 
				// set current time into timepicker
				//timePicker1.setCurrentHour(hour);
				//timePicker1.setCurrentMinute(minute);
	 
		}
	};
	
	private static String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else
		   return "0" + String.valueOf(c);
	}
}
