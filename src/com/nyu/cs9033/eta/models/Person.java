package com.nyu.cs9033.eta.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Person {
	
	// Member fields should exist here, what else do you need for a person?
	// Please add additional fields
	private int personId;
	private int tripId;
	
	private String name;
	private String email;
	private String cellNo;
	private String homeAddress;
	private String workAddress;
	private String curAddress;
	
	public static final double MAX_LAT = 90;
    public static final double MIN_LAT = -90;
    public static final double MAX_LNG = 180;
    public static final double MIN_LNG = -180;
    
	private double curLocationLat = MAX_LAT + 1;
	private double curLocationLong = MAX_LNG + 1;
	

	/**
	 * Create a Person model object from arguments
	 * 
	 * @param name Add arbitrary number of arguments to
	 * instantiate Person class based on member variables.
	 */
	public Person(String name) {
		this.name = name;
	}

	public Person(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public Person() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellNo() {
		return cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getCurAddress() {
		return curAddress;
	}

	public void setCurAddress(String curAddress) {
		this.curAddress = curAddress;
	}
	public double getCurLocationLat() {
		return curLocationLat;
	}

	public double getCurLocationLong() {
		return curLocationLong;
	}

	public void setCurLocationLat(double curLocationLat) {
		this.curLocationLat = curLocationLat;
	}

	public void setCurLocationLong(double curLocationLong) {
		this.curLocationLong = curLocationLong;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

}
