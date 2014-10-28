package com.nyu.cs9033.eta.models;


public class TripLocation {
	private long tripId;
	private String tripLocationName;
	private String tripLocationAddress;
	private double tripLocationLat;
	private double tripLocationLong;
	public static final double MAX_LAT = 90;
    public static final double MIN_LAT = -90;
    public static final double MAX_LNG = 180;
    public static final double MIN_LNG = -180;
    
    public TripLocation() {
    	
    }
	public TripLocation(String tripLocationName, String tripLocationAddress, double tripLocationLat, double tripLocationLong) {
		setTripLocationName(tripLocationName);
		setTripLocationAddress(tripLocationAddress);
		setTripLocationLat(tripLocationLat);
		setTripLocationLong(tripLocationLong);
	}


	public void setTripLocation(String tripLocationName, String tripLocationAddress, double tripLocationLat, double tripLocationLong) {
		setTripLocationName(tripLocationName);
		setTripLocationAddress(tripLocationAddress);
		setTripLocationLat(tripLocationLat);
		setTripLocationLong(tripLocationLong);
	}
	
	
	public String getTripLocationName() {
		return tripLocationName;
	}

	public String getTripLocationAddress() {
		return tripLocationAddress;
	}

	public double getTripLocationLat() {
		return tripLocationLat;
	}

	public double getTripLocationLong() {
		return tripLocationLong;
	}

	public void setTripLocationName(String tripLocationName) {
		this.tripLocationName = tripLocationName;
	}

	public void setTripLocationAddress(String tripLocationAddress) {
		this.tripLocationAddress = tripLocationAddress;
	}

	public void setTripLocationLat(double tripLocationLat) {
		// TODO validation
		this.tripLocationLat = tripLocationLat;
	}

	public void setTripLocationLong(double tripLocationLong) {
		// TODO validation
		this.tripLocationLong = tripLocationLong;
	}
	public long getTripId() {
		return tripId;
	}
	public void setTripId(long tripId) {
		this.tripId = tripId;
	}
	
	
}