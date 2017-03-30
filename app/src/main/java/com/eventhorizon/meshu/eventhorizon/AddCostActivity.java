package com.eventhorizon.meshu.eventhorizon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.eventhorizon.meshu.eventhorizon.Model.Cost;
import com.eventhorizon.meshu.eventhorizon.Model.Event;

import java.util.Date;

public class AddCostActivity extends AppCompatActivity implements View.OnClickListener{
    TextView heading;
    EditText etTitle,etAmount,etDetails;
    LinearLayout btnDone,btnReturn;

    private String eventId;
    private String eventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);

        heading = (TextView) findViewById(R.id.textView);

        etTitle = (EditText) findViewById(R.id.etCostTitle);
        etAmount = (EditText) findViewById(R.id.etAmount);
        etDetails = (EditText) findViewById(R.id.etDetails);

        btnDone = (LinearLayout) findViewById(R.id.costSubmit);
        btnReturn = (LinearLayout) findViewById(R.id.costReturn);

        btnDone.setOnClickListener(this);
        btnReturn.setOnClickListener(this);

        /*
         *
         * get the event id for this activity
         *
         * */
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            eventId = (String) bd.get("eventid");
        }
        /* ---------------------------------------- */


        /*
        * find the event name
        * */
        Event event = (new Select()).from(Event.class).where("eventId =?", eventId).executeSingle();
        eventName = event.eventName;


        /*
        * set the event name to heading
        * */
        heading.setText("Event# "+eventName);

    }

    @Override
    public void onClick(View v) {
        if(v == btnDone){
            Date date = new Date();
            long hostId = date.getTime();
            String sEventId = Long.toString(hostId);

            Cost cost = new Cost(
                    sEventId,
                    etTitle.getText().toString(),
                    eventId,
                    Float.parseFloat(etAmount.getText().toString()),
                    etDetails.getText().toString()
                    );
            cost.save();

            keyboardDown();

                /*    Toast Display for successfully inserted */
            Context context = getApplicationContext();
            CharSequence text = "Successfully Inserted";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context,text, duration);
            toast.show();

            Intent intent = new Intent(AddCostActivity.this, CostActivity.class);
            intent.putExtra("id",eventId);
            finish();
            startActivity(intent);
        }
        if(v == btnReturn){
            Intent intent = new Intent(AddCostActivity.this, MainActivity.class);
            finish();
            startActivity(intent);
        }

    }

    public void keyboardDown(){
            /* for hidding the keyboard after click done button */
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
