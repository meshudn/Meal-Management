package com.eventhorizon.meshu.eventhorizon.helper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.eventhorizon.meshu.eventhorizon.R;

import java.util.List;

/**
 * Created by meshu on 3/21/2017.
 */

public class CostRecyclerView extends  RecyclerView.Adapter<CostRecyclerView.CostReclyclerViewHolder>{
    public List<String> costName,costDetailsList;
    public List<Float> costAmount;

    public CostRecyclerView(List<String> costName, List<Float> costAmount,List<String> costDetailsList) {
        this.costName = costName;
        this.costDetailsList = costDetailsList;
        this.costAmount = costAmount;
    }

    @Override
    public CostRecyclerView.CostReclyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cost,parent,false);

        CostRecyclerView.CostReclyclerViewHolder vholder = new CostRecyclerView.CostReclyclerViewHolder(view);
        return vholder;
    }

    @Override
    public void onBindViewHolder(CostRecyclerView.CostReclyclerViewHolder holder, int position) {

        holder.count.setText(Integer.toString(position+1)+".");

        String str = costName.get(position);
        String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
        holder.costText.setText(cap);

        holder.amountText.setText("$"+Float.toString(costAmount.get(position)));

        holder.costDetails.setText(costDetailsList.get(position));
    }

    @Override
    public int getItemCount() {
        return costName.size();
    }


    public static class CostReclyclerViewHolder extends RecyclerView.ViewHolder{
        TextView costText,amountText,count,costDetails;

        public CostReclyclerViewHolder(View itemView) {
            super(itemView);
            count = (TextView) itemView.findViewById(R.id.costCount);
            costText = (TextView) itemView.findViewById(R.id.txtCostTitle);
            amountText = (TextView) itemView.findViewById(R.id.txtAmount);
            costDetails = (TextView) itemView.findViewById(R.id.txtDetails);
        }

    }
}
