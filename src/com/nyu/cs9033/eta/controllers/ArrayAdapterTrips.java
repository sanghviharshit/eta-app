
package com.nyu.cs9033.eta.controllers;

import java.util.ArrayList;
import java.util.Calendar;

import com.nyu.cs9033.eta.R;
import com.nyu.cs9033.eta.models.Person;
import com.nyu.cs9033.eta.models.Trip;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;


// here's our beautiful adapter

public class ArrayAdapterTrips extends ArrayAdapter<Trip> {
    Context mContext;
    int layoutResourceId;
    ArrayList<Trip> data;
    public ArrayAdapterTrips(Context mContext, int layoutResourceId, ArrayList<Trip> data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }
        // object item based on the position
        Trip trip = data.get(position);
        if (trip == null) {
        	return null;
        }
        // get the TextView and then set the text (item name) and tag (item ID) values
        //TO-DO: it should be friend name/email field.
        TextView tripDisplayName = (TextView) convertView.findViewById(R.id.tripRowName);
        tripDisplayName.setText(trip.getTripName());
        
        TextView tripDisplayLocation = (TextView) convertView.findViewById(R.id.tripRowLocation);
        tripDisplayLocation.setText((CharSequence) trip.getTripLocation().getTripLocationName());
       
		Calendar tripTime = Calendar.getInstance();
		tripTime.setTimeInMillis(trip.getTripTimeInMillis());
		int year = tripTime.get(Calendar.YEAR);
		int month = tripTime.get(Calendar.MONTH) + 1;
		int day = tripTime.get(Calendar.DAY_OF_MONTH);
		int hour = tripTime.get(Calendar.HOUR_OF_DAY);
		int minute = tripTime.get(Calendar.MINUTE);
		
		String displayTime = month + "/" + day + "/" + year + " " + hour + ":" + minute;
        TextView tripDisplayTime = (TextView) convertView.findViewById(R.id.tripRowTime);
		tripDisplayTime.setText(displayTime);
        
        //textViewItem.setTag(trip.getTripId());
        return convertView;
    }

}
