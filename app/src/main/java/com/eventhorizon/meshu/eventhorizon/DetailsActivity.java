package com.eventhorizon.meshu.eventhorizon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.eventhorizon.meshu.eventhorizon.Model.Event;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private String eventId;

    TextView name;
    Button addUser, listUser;
    private String eventName;
    private TextView seatA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            eventId = (String) bd.get("id");
            eventName = (String) bd.get("name");
        }

        Event event = (new Select()).from(Event.class).where("eventId =?", eventId).executeSingle();
        int seatAvailable = Integer.parseInt(event.seat);

        name = (TextView) findViewById(R.id.detailsName);
        seatA = (TextView) findViewById(R.id.seatAvailable);

        name.setText("Event# "+eventName);
        seatA.setText("Available Seat# "+seatAvailable);

        addUser = (Button) findViewById(R.id.addUser);
        listUser = (Button) findViewById(R.id.listUser);

        addUser.setOnClickListener(this);
        listUser.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (addUser == v){
            Intent intent = new Intent (this,AddUserActivity.class);
            intent.putExtra("name",eventName);
            intent.putExtra("id",eventId);
            startActivity(intent);
        }
        if(listUser == v){
            Intent intent = new Intent (this,ListOfUser.class);
            intent.putExtra("name",eventName);
            intent.putExtra("id",eventId);
            startActivity(intent);
        }
    }
}
