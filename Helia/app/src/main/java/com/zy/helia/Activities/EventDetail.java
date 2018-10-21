package com.zy.helia.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.R;


public class EventDetail extends AppCompatActivity {

    private int eventID;
    private TextView eventName;
    private TextView eventDescription;

    private Button register;
    private Button interested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Intent intent = getIntent();

        eventID = Integer.parseInt(intent.getStringExtra("EventID"));
        userID = LoginActivity.getUserID();

        DatabaseHelp dbHelper = new DatabaseHelp(this);
        Cursor eventCursor = dbHelper.viewEvent(eventID);

        eventCursor.moveToNext();

        int eventIndex = eventCursor.getColumnIndex("Event_Name");
        String name = eventCursor.getString(eventIndex);

        eventName = (TextView) findViewById(R.id.name);
        eventName.setText(name);

        int descriptionIndex = eventCursor.getColumnIndex("Event_Description");
        String description = eventCursor.getString(descriptionIndex);

        eventDescription= (TextView) findViewById(R.id.descprition);
        eventDescription.setText(description);

<<<<<<< HEAD
=======
        eventID = Integer.parseInt(intent.getStringExtra("EventID"));
<<<<<<< HEAD

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create database helper
                DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                // Gets the database in write mode
                SQLiteDatabase DB = DBHelper.getWritableDatabase();

                DBHelper.addRegistered(eventID, LoginActivity.getUserID());
                Toast.makeText(getBaseContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
            }
        });

        interested = (Button) findViewById(R.id.interest);
        interested.setOnClickListener(new View.OnClickListener() {
=======
        userID = LoginActivity.getUserID();

        String event = "EventID"+eventID+"UserID"+userID;

>>>>>>> 6ff436eff32949a2fe29a764957582e0a4577300
        register = (Button) findViewById(R.id.register);


        if ((!registeredlist.isEmpty())&&(registeredlist.indexOf(event)!=-1)) {
            register.setClickable(false);
            register.setText("Successfully Registered");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create database helper
                DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                // Gets the database in write mode
                SQLiteDatabase DB = DBHelper.getWritableDatabase();

                DBHelper.addRegistered(eventID, LoginActivity.getUserID());
                Toast.makeText(getBaseContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
            }
        });

        if (dbHelper.isRegistered(eventID, userID)) {
            register.setText("Remove from Registered");
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                    DBHelper.removeRegistered(eventID, userID);
                    register.setClickable(false);
                    register.setText("Removal Successful");
                }
            });

        }
        else {
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                    DBHelper.addRegistered(eventID, userID);
                    register.setClickable(false);
                    register.setText("Successfully Registered");
                }
            });
        }


        register.setOnClickListener(new View.OnClickListener() {
>>>>>>> 4e9c9bf3536c9e579a7049f849a7d79a11c09597
            @Override
            public void onClick(View v) {
                // Create database helper
                DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                // Gets the database in write mode
                SQLiteDatabase DB = DBHelper.getWritableDatabase();
<<<<<<< HEAD

                DBHelper.addInterested(eventID, LoginActivity.getUserID());
                Toast.makeText(getBaseContext(), "Added to Interested List", Toast.LENGTH_SHORT).show();
            }
        });
=======
                if(register.getText().equals("Register")){
                    DBHelper.addRegistered(eventID, LoginActivity.getUserID());
                    register.setText("Registered");
                    Toast.makeText(getBaseContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                }
                if(register.getText().equals("Registered")){
                    DBHelper.removeRegistered(eventID, LoginActivity.getUserID());
                    register.setText("Register");
                    Toast.makeText(getBaseContext(), "Registration Cancelled", Toast.LENGTH_SHORT).show();
                }
            }
        });



        interested = (Button) findViewById(R.id.interest);
        if (dbHelper.isInterested(eventID, userID)){
            interested.setText("Remove from Interested List");
            interested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                    DBHelper.removeInterested(eventID, userID);
                    interested.setClickable(false);
                    interested.setText("Removal Successful");
                }
            });
        }
        else{
            interested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelp DBHelper = new DatabaseHelp(getBaseContext());
                    DBHelper.addInterested(eventID, userID);
                    interested.setClickable(false);
                    interested.setText("Added into Interested List");
                }
            });
        }
>>>>>>> 4e9c9bf3536c9e579a7049f849a7d79a11c09597

    }
}}
