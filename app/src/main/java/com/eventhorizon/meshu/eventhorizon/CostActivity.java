package com.eventhorizon.meshu.eventhorizon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.eventhorizon.meshu.eventhorizon.Model.Cost;
import com.eventhorizon.meshu.eventhorizon.Model.Event;
import com.eventhorizon.meshu.eventhorizon.helper.CostRecyclerView;
import com.eventhorizon.meshu.eventhorizon.helper.MyReclyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CostActivity extends AppCompatActivity {
    private String eventId;
    private TextView heading;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycleAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String eventName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);


        heading = (TextView) findViewById(R.id.heading);


        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            eventId = (String) bd.get("id");
        }


         /*
        * find the event name
        * */
        Event event = (new Select()).from(Event.class).where("eventId =?", eventId).executeSingle();
        eventName = event.eventName;
        
        
        /*
        * set the event name to heading
        * */
        eventName = eventName.substring(0, 1).toUpperCase() + eventName.substring(1);
        heading.setText("Event#  "+ eventName);
        
        



         List<String> costNameList = new ArrayList<String>();
         List<String> costDetailsList = new ArrayList<String>();
         List<Float> costAmount = new ArrayList<Float>();

        List<Cost> allCost =  (new Select()).from(Cost.class).where("eventId =?", eventId).execute();

        for (Cost temp : allCost) {
            costNameList.add(temp.titles);
            costDetailsList.add(temp.details);
            costAmount.add(temp.cost);
        }


        recyclerView = (RecyclerView) findViewById(R.id.cost_recyclerView);
        recycleAdapter = new CostRecyclerView(costNameList,costAmount,costDetailsList);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recycleAdapter);
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
        inflater.inflate(R.menu.costmenu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_cost:
                Intent intent = new Intent (this,AddCostActivity.class);
                intent.putExtra("eventid",eventId);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* ---------------------------***------------------------------*/
}
