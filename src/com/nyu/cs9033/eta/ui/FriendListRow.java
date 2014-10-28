package com.nyu.cs9033.eta.ui;

import com.nyu.cs9033.eta.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;


public class FriendListRow extends LinearLayout {
    private TextView textContent;

    public FriendListRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        //setOrientation(LinearLayout.HORIZONTAL);
        //setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.friend_list_row, this, true);
            
        //((Activity)getContext()).getLayoutInflater().inflate(R.layout.list_view_row_person, this);
    }
    public FriendListRow(Context context) {
        super(context);
        ((Activity)getContext()).getLayoutInflater().inflate(R.layout.friend_list_row, this);

    }
    public FriendListRow(Context context, String friend) {
        super(context);
        ((Activity)getContext()).getLayoutInflater().inflate(R.layout.friend_list_row, this);
        textContent = (TextView) findViewById(R.id.textViewContent);
        textContent.setText(friend);
        setupViews();
        
    }
    private void setupViews() {
        Button btnRemoveFriend = (Button) findViewById(R.id.removeFriendButton);
        btnRemoveFriend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
				// TODO
				
			}
        });
	}

    
}

