package com.nyu.cs9033.eta.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.nyu.cs9033.eta.controllers.CreateTripActivity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Trip {
	
	// Member fields should exist here, what else do you need for a trip?
	// Please add additional fields
	private long tripId;
	private String tripName;
	private String startLocation = "";
	private ArrayList<Person> tripFriendsArray;
	private static int numFriends = 0;
	private TripLocation tripLocation;
	private long tripTimeInMillis;

	private static final String TAG = "Trip";
	
	public Trip() {
	}
	/**
	 * Create a Trip model object from arguments
	 * 
	 * @param name  Add arbitrary number of arguments to
	 * instantiate Trip class based on member variables.
	 */
	public Trip(String tripName) {
		this.tripName = tripName;
	}


	/**
	 * @return the tripId
	 */
	public long getTripId() {
		return tripId;
	}

	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId(long tripId) {
		this.tripId = tripId;
	}

	/**
	 * @return the tripName
	 */
	public String getTripName() {
		return tripName;
	}

	/**
	 * @param tripName the tripName to set
	 */
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}

	/**
	 * @return the tripLocation
	 */
	public TripLocation getTripLocation() {
		return tripLocation;
	}

	/**
	 * @param tripLocation the tripLocation to set
	 */
	public void setTripLocation(TripLocation tripLocation) {
		this.tripLocation = tripLocation;
	}

	/**
	 * @return the tripTime
	 */
	public long getTripTimeInMillis() {
		return tripTimeInMillis;
	}
	
	/**
	 * @param trip date and time to set
	 */
	public void setTripTimeInMillis(int year, int month, int day, int hour, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hour, minute); 
		this.tripTimeInMillis = calendar.getTimeInMillis();
	}
	/**
	 * @param trip date and time to set
	 */
	public void setTripTimeInMillis(long tripTimeInMillis) {
		this.tripTimeInMillis = tripTimeInMillis;
	}

	public String getStartLocation() {
		return startLocation;
	}


	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public ArrayList<Person> getTripFriendsArray() {
		return tripFriendsArray;
	}


	public void setTripFriendsArray(ArrayList<Person> tripFriendsArray) {
		this.tripFriendsArray = tripFriendsArray;
	}


	public void displayTrip() {
		Log.i(TAG, tripName);
		Log.i(TAG, startLocation);	
		Log.i(TAG, tripLocation.getTripLocationName());
		Log.i(TAG, tripLocation.getTripLocationAddress());
		Log.i(TAG, Double.toString(tripLocation.getTripLocationLat()));
		Log.i(TAG, Double.toString(tripLocation.getTripLocationLong()));
		Log.i(TAG, Double.toString(tripTimeInMillis));

		for(int i=0;i<tripFriendsArray.size();i++) {
			Log.i(TAG, tripFriendsArray.get(i).getName() + ", " + tripFriendsArray.get(i).getEmail());
		}
	}




}
