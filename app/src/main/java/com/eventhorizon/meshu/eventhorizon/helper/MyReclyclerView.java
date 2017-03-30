package com.eventhorizon.meshu.eventhorizon.helper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.eventhorizon.meshu.eventhorizon.CostActivity;
import com.eventhorizon.meshu.eventhorizon.DetailsActivity;
import com.eventhorizon.meshu.eventhorizon.NewEvent;
import com.eventhorizon.meshu.eventhorizon.R;

import java.util.List;

/**
 * Created by meshu on 3/19/2017.
 */

public class MyReclyclerView extends RecyclerView.Adapter<MyReclyclerView.MyReclyclerViewHolder> {
    public List<String> EventName,EventDate,EventId;
    private Context myContext;

    public MyReclyclerView(List<String> eventName, List<String> eventDate, List<String> eventId, Context myContext) {
        EventName = eventName;
        EventDate = eventDate;
        EventId = eventId;
        this.myContext = myContext;
    }

    @Override
    public MyReclyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);

        MyReclyclerViewHolder vholder = new MyReclyclerViewHolder(view);
        return vholder;
    }

    @Override
    public void onBindViewHolder(MyReclyclerViewHolder holder, final int position) {
         String str = EventName.get(position);
         String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
         holder.eventName.setText(cap);

         holder.eventDate.setText(EventDate.get(position));
         holder.count.setText(Integer.toString(position+1)+".");

         holder.btnCost.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(myContext, CostActivity.class);
                 intent.putExtra("id",EventId.get(position));
                 myContext.startActivity(intent);
             }
         });

        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, DetailsActivity.class);
                intent.putExtra("id",EventId.get(position));
                intent.putExtra("name",EventName.get(position));
                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return EventName.size();
    }


    public static class MyReclyclerViewHolder extends RecyclerView.ViewHolder{
        TextView eventName,eventDate,count;
        ImageButton btnCost,btnDetails;

        public MyReclyclerViewHolder(View itemView) {
            super(itemView);
            count = (TextView) itemView.findViewById(R.id.event);
            eventName = (TextView) itemView.findViewById(R.id.RcEventName);
            eventDate = (TextView) itemView.findViewById(R.id.RcEventDate);
            btnCost = (ImageButton) itemView.findViewById(R.id.btnEventCost);
            btnDetails = (ImageButton) itemView.findViewById(R.id.btnDetails);



        }

    }
}
