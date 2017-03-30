package com.eventhorizon.meshu.eventhorizon.helper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eventhorizon.meshu.eventhorizon.R;

import java.util.List;

/**
 * Created by meshu on 3/22/2017.
 */


public class DetailsRecyclerView extends  RecyclerView.Adapter<DetailsRecyclerView.CostReclyclerViewHolder>{
    public List<String> userName,userPhone,userAddress;


    public DetailsRecyclerView(List<String> userName, List<String> userPhone, List<String> userAddress) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    @Override
    public DetailsRecyclerView.CostReclyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_details,parent,false);

        DetailsRecyclerView.CostReclyclerViewHolder vholder = new DetailsRecyclerView.CostReclyclerViewHolder(view);
        return vholder;
    }

    @Override
    public void onBindViewHolder(DetailsRecyclerView.CostReclyclerViewHolder holder, int position) {

        holder.count.setText(Integer.toString(position+1)+".");
        holder.etuserName.setText("Name: "+userName.get(position));
        holder.etuserPhone.setText("Contact No: "+userPhone.get(position));
        holder.etuserDetails.setText("Address: "+userAddress.get(position));


    }

    @Override
    public int getItemCount() {
        return userName.size();
    }


    public static class CostReclyclerViewHolder extends RecyclerView.ViewHolder{
        TextView etuserName,etuserPhone,etuserDetails,count;

        public CostReclyclerViewHolder(View itemView) {
            super(itemView);
            count = (TextView) itemView.findViewById(R.id.event);
            etuserName = (TextView) itemView.findViewById(R.id.userName);
            etuserPhone = (TextView) itemView.findViewById(R.id.userPhone);
            etuserDetails = (TextView) itemView.findViewById(R.id.userAddress);
        }

    }
}