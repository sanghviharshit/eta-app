package com.nyu.cs9033.eta.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.nyu.cs9033.eta.controllers.CreateTripActivity;

import android.os.Parcel;
import android.os.Parcelable;

public class Trip {
	
	// Member fields should exist here, what else do you need for a trip?
	// Please add additional fields
	private int tripId;
	private String tripName;

	private String startLocation = "";
	private ArrayList<Person> tripFriendsArray;
	private static int numFriends = 0;
	private TripLocation tripLocation;
	
	
	private long tripTimeInMillis;

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
	public int getTripId() {
		return tripId;
	}

	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId(int tripId) {
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
		this.tripTimeInMillis = calendar.getTimeInMillis();;
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
		System.out.println(tripName);
		System.out.println(startLocation);	
		System.out.println(tripLocation.getTripLocationName());
		System.out.println(tripLocation.getTripLocationAddress());
		System.out.println(tripLocation.getTripLocationLat());
		System.out.println(tripLocation.getTripLocationLong());
		System.out.println(tripTimeInMillis);
		for(int i=0;i<tripFriendsArray.size();i++) {
			System.out.println(tripFriendsArray.get(i).getName() + ", " + tripFriendsArray.get(i).getEmail());
		}
	}




}
