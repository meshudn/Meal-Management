package com.eventhorizon.meshu.eventhorizon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.activeandroid.query.Select;
import com.eventhorizon.meshu.eventhorizon.Model.Event;
import com.eventhorizon.meshu.eventhorizon.Model.User;
import com.eventhorizon.meshu.eventhorizon.helper.DetailsRecyclerView;
import com.eventhorizon.meshu.eventhorizon.helper.MyReclyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListOfUser extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycleAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String eventName;
    private String eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_user);


        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            eventId = (String) bd.get("id");
            eventName = (String) bd.get("name");
        }


        final List<String> allname = new ArrayList<String>();
        final List<String> allphone = new ArrayList<String>();
        final List<String> alladdress = new ArrayList<String>();

        List<User> allEvent = (new Select()).from(User.class).where("eventId=?", eventId).execute();

        for (User temp: allEvent) {
            allname.add(temp.name);
            allphone.add(temp.phone);
            alladdress.add(temp.address);
        }


        recyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);

        recycleAdapter = new DetailsRecyclerView(allname,allphone,alladdress);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recycleAdapter);
    }
}
