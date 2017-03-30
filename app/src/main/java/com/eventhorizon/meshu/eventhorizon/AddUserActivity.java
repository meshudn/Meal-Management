package com.eventhorizon.meshu.eventhorizon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.eventhorizon.meshu.eventhorizon.Model.Event;
import com.eventhorizon.meshu.eventhorizon.Model.Host;
import com.eventhorizon.meshu.eventhorizon.Model.User;

import java.util.Date;
import java.util.List;

public class AddUserActivity extends AppCompatActivity {

    private String eventId;
    private String eventName;

    EditText userName,userAddress,userPhone;

    LinearLayout btnDone,btnReturn;

    int seatAvailable,userCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            eventId = (String) bd.get("id");
            eventName = (String) bd.get("name");
        }

        userName = (EditText) findViewById(R.id.editUserName);
        userPhone = (EditText) findViewById(R.id.editUserPhone);
        userAddress = (EditText) findViewById(R.id.editUerAddress);

        btnDone = (LinearLayout) findViewById(R.id.btnHostDone);
        btnReturn = (LinearLayout) findViewById(R.id.homeReturn);

        Event event = (new Select()).from(Event.class).where("eventId =?", eventId).executeSingle();
        seatAvailable = Integer.parseInt(event.seat);

        List<User> allEvent = (new Select()).from(User.class).where("eventId=?", eventId).execute();

        for (User temp: allEvent) {
            userCount += 1;
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = new Date();
                long hostId = date.getTime();
                String sHostId = Long.toString(hostId);

                String name = userName.getText().toString();
                String phone = userPhone.getText().toString();
                String address = userAddress.getText().toString();

                if(userCount < seatAvailable){
                    User user = new User(sHostId,name,eventId,phone,address);
                    user.save();
                    toastMsg(true);
                }else{
                    toastMsg(false);
                }




                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }



            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddUserActivity.this, DetailsActivity.class);
                intent.putExtra("name",eventName);
                intent.putExtra("id",eventId);
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
