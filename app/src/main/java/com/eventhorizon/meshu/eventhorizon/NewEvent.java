package com.eventhorizon.meshu.eventhorizon;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.eventhorizon.meshu.eventhorizon.Model.Event;
import com.eventhorizon.meshu.eventhorizon.Model.Host;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewEvent extends AppCompatActivity {
    TextView tv;
    LinearLayout btnDone,homeReturn;
    EditText eventName,eventDate,etuser;

    private String selectedHost;
    private String selectedHostId;
    private String eventDateValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);


        Spinner spinner = (Spinner) findViewById(R.id.spn_host);

        eventName = (EditText) findViewById(R.id.editEventName);
        eventDate = (EditText) findViewById(R.id.editEventDate);
        etuser = (EditText) findViewById(R.id.editEventUser);




        btnDone = (LinearLayout) findViewById(R.id.btnEventDone);
        homeReturn = (LinearLayout) findViewById(R.id.homeReturn);


        final List<String> hostName = new ArrayList<String>();
        final List<String> hostId = new ArrayList<String>();

        List<Host> allHost =  (new Select()).from(Host.class).execute();


        for (Host temp: allHost) {
            hostId.add(temp.hostId);
            hostName.add(temp.hostName.toUpperCase());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hostName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedHost = hostName.get(position);
                selectedHostId = hostId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                long hostId = date.getTime();
                String sEventId = Long.toString(hostId);

                Event event = new Event(
                        sEventId,
                        eventName.getText().toString(),
                        selectedHostId,
                        selectedHost,
                        eventDate.getText().toString(),
                        etuser.getText().toString()
                        );
                event.save();

                keyboardDown();

                /*    Toast Display for successfully inserted */
                Context context = getApplicationContext();
                CharSequence text = "Successfully Inserted";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text, duration);
                toast.show();


            }
        });


        homeReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewEvent.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

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
