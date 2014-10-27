
package com.nyu.cs9033.eta.controllers;

import com.nyu.cs9033.eta.R;
import com.nyu.cs9033.eta.models.Person;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


// here's our beautiful adapter

public class ArrayAdapterPersons extends ArrayAdapter<Person> {
    Context mContext;
    int layoutResourceId;
    Person data[] = null;
    public ArrayAdapterPersons(Context mContext, int layoutResourceId, Person[] data) {
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
        Person person = data[position];
        if (person == null) {
        	return null;
        }
        // get the TextView and then set the text (item name) and tag (item ID) values
        //TO-DO: it should be friend name/email field.
        TextView textViewItem = (TextView) convertView.findViewById(R.id.tripName);
        textViewItem.setText(person.getEmail());
        textViewItem.setTag(person.getEmail());

        return convertView;
    }

}
