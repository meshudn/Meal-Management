package com.eventhorizon.meshu.eventhorizon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.activeandroid.query.Select;
import com.eventhorizon.meshu.eventhorizon.Model.Event;
import com.eventhorizon.meshu.eventhorizon.Model.Host;
import com.eventhorizon.meshu.eventhorizon.helper.MyReclyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycleAdapter;
    private RecyclerView.LayoutManager layoutManager;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final List<String> alleventName = new ArrayList<String>();
        final List<String> alleventDate = new ArrayList<String>();
        final List<String> alleventId = new ArrayList<String>();

        List<Event> allEvent =  (new Select()).from(Event.class).execute();

        for (Event temp: allEvent) {
            alleventName.add(temp.eventName);
            alleventDate.add(temp.date);
            alleventId.add(temp.eventId);
        }


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recycleAdapter = new MyReclyclerView(alleventName,alleventDate,alleventId,this);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recycleAdapter);
    }

    @Override
    public void onClick(View v) {


    }


    /* -------------------------------------------- */
    /*
    *
    * Option Menu
    *
    * --------------------------------------------- */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_host:
                Intent host_intent = new Intent (this,NewHost.class);
                startActivity(host_intent);
                return true;
            case R.id.add_event:
                Intent event_intent = new Intent (this,NewEvent.class);
                startActivity(event_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* ---------------------------***------------------------------*/
}
