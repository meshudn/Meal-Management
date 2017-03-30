package com.eventhorizon.meshu.eventhorizon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.eventhorizon.meshu.eventhorizon.Model.Event;
import com.eventhorizon.meshu.eventhorizon.Model.Host;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.id.empty;

public class NewHost extends AppCompatActivity {
    LinearLayout btnDone,returnHome;
    EditText editHostName,editOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_host);

        editHostName = (EditText) findViewById(R.id.editHostName);
        editOrg = (EditText) findViewById(R.id.editOrg);



        btnDone = (LinearLayout) findViewById(R.id.btnHostDone);
        returnHome = (LinearLayout) findViewById(R.id.homeReturn);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = new Date();
                long hostId = date.getTime();
                String sHostId = Long.toString(hostId);

                String name = editHostName.getText().toString();
                String organization = editHostName.getText().toString();

                if( name != " " && organization != " ") {
                    Host host = new Host(sHostId, editHostName.getText().toString(), editOrg.getText().toString());
                    host.save();

                    toastMsg(true);
                }else{
                    toastMsg(false);

                    editHostName.setHintTextColor(Color.RED);
                    editHostName.setHint("Please fill out this field");

                    editOrg.setHintTextColor(Color.RED);
                    editOrg.setHint("Please fill out this field");
                }


                /* for hidding the keyboard after click done button */
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }



            }
        });

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewHost.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    public void toastMsg(boolean flag){
        String message;
        if(flag == true){
            message = "Successfully Inserted";
        }else{
            message = "Sorry. Try again";
        }
        /* ------------------------------- */
                /*    Toast Display for successfully inserted */
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text, duration);
        toast.show();
    }
}
