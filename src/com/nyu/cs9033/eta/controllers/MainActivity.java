package com.nyu.cs9033.eta.controllers;

import com.nyu.cs9033.eta.models.Trip;
import com.nyu.cs9033.eta.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	public final static String SAVED_TRIP = "Saved Trip Object";
	private TextView message;
	private Button button_trip_view;
	protected LinearLayout mainLayout;
	private Trip savedTrip;
	int requestCode;
	SharedPreferences sharedpreferences;
	public static final String APP_PREFERENCES = "App_Preferences";
	public static final String startLocation = "startLocation"; 
   
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sharedpreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		editor.putString(startLocation, "Jersey City, New Jersey");
		editor.commit(); 
	}

	/**
	 * This method should start the
	 * Activity responsible for creating
	 * a Trip.
	 */
	public void startCreateTripActivity(View view) {
		requestCode = 1;
		Intent startCreateTrip = new Intent(this, CreateTripActivity.class);
		startActivityForResult(startCreateTrip, requestCode);
		
	}
	
	/**
	 * This method should start the
	 * Activity responsible for viewing
	 * a Trip.
	 */
	public void startViewTripActivity(View view) {
		Intent startViewTrip = new Intent(this, ViewTripActivity.class);
		
		if(savedTrip != null) {
			//startViewTrip.putExtra(SAVED_TRIP, savedTrip);	
		}	
		startActivity(startViewTrip);
	}
	
	/**
	 * Receive result from CreateTripActivity here.
	 * Can be used to save instance of Trip object
	 * which can be viewed in the ViewTripActivity.
	 * 
	 * Note: This method will be called when a Trip
	 * object is returned to the main activity. 
	 * Remember that the Trip will not be returned as
	 * a Trip object; it will be in the persisted
	 * Parcelable form. The actual Trip object should
	 * be created and saved in a variable for future
	 * use, i.e. to view the trip.
	 * 
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if(resultCode == RESULT_OK){
				//savedTrip = (Trip) data.getParcelableExtra(CreateTripActivity.NEW_TRIP);
				Toast.makeText(this, "New Trip Saved", Toast.LENGTH_LONG).show();	            
	        }
	        if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        	Toast.makeText(this, "Cancelled.", Toast.LENGTH_LONG).show();
	        }
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
