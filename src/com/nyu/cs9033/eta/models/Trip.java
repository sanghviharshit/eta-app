package com.nyu.cs9033.eta.models;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Trip implements Parcelable {
	
	// Member fields should exist here, what else do you need for a trip?
	// Please add additional fields
	private int tripId;
	private String tripName;
	private String tripLocation;
	private String tripDate;
	private String tripTime;
	private String tripFriends[];	//TO-DO
	
	/**
	 * Parcelable creator. Do not modify this function.
	 */
	public static final Parcelable.Creator<Trip> CREATOR = new Parcelable.Creator<Trip>() {
		public Trip createFromParcel(Parcel p) {
			return new Trip(p);
		}

		public Trip[] newArray(int size) {
			return new Trip[size];
		}
	};
	
	/**
	 * Create a Trip model object from a Parcel. This
	 * function is called via the Parcelable creator.
	 * 
	 * @param p The Parcel used to populate the
	 * Model fields.
	 */
	public Trip(Parcel p) {
		tripId = p.readInt();
		tripName = p.readString();
		tripLocation = p.readString();
		tripDate = p.readString();
		tripTime = p.readString();
	}
	
	/**
	 * Create a Trip model object from arguments
	 * 
	 * @param name  Add arbitrary number of arguments to
	 * instantiate Trip class based on member variables.
	 */
	public Trip(String tripName, String tripLocation, String tripDate, String tripTime) {
		this.tripName = tripName;
		this.tripLocation = tripLocation;
		this.tripDate = tripDate;
		this.tripTime = tripTime;
	}

	/**
	 * Serialize Trip object by using writeToParcel. 
	 * This function is automatically called by the
	 * system when the object is serialized.
	 * 
	 * @param dest Parcel object that gets written on 
	 * serialization. Use functions to write out the
	 * object stored via your member variables. 
	 * 
	 * @param flags Additional flags about how the object 
	 * should be written. May be 0 or PARCELABLE_WRITE_RETURN_VALUE.
	 * In our case, you should be just passing 0.
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(tripId);
		dest.writeString(tripName);
		dest.writeString(tripLocation);
		dest.writeString(tripDate);
		dest.writeString(tripTime);
	}
	
	/**
	 * Feel free to add additional functions as necessary below.
	 */
	
	/**
	 * Do not implement
	 */
	@Override
	public int describeContents() {
		// Do not implement!
		return 0;
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
	public String getTripLocation() {
		return tripLocation;
	}

	/**
	 * @param tripLocation the tripLocation to set
	 */
	public void setTripLocation(String tripLocation) {
		this.tripLocation = tripLocation;
	}

	/**
	 * @return the tripDate
	 */
	public String getTripDate() {
		return tripDate;
	}

	/**
	 * @param tripDate the tripDate to set
	 */
	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}

	/**
	 * @return the tripTime
	 */
	public String getTripTime() {
		return tripTime;
	}

	/**
	 * @param tripTime the tripTime to set
	 */
	public void setTripTime(String tripTime) {
		this.tripTime = tripTime;
	}
}
