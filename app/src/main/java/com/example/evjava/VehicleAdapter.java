package com.example.evjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder> {

    private final List<vehicledb> items;
    private final Context context;

    public VehicleAdapter(List<vehicledb> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public VehicleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VehicleAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_adapter,null));
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.MyViewHolder holder, int position) {
        vehicledb myItems=items.get(position);
        holder.EVname.setText(myItems.getModeltype());
        holder.Evnumber.setText(myItems.getNumber());
        holder.EvRange.setText(myItems.getRange());
        holder.EVtime.setText(myItems.getChargetym());
        holder.EVmode.setText(myItems.getTypeofmode());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView EVname,Evnumber,EvRange,EVtime,EVmode;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            EVname=itemView.findViewById(R.id.EVname);
            Evnumber=itemView.findViewById(R.id.Evnumber);
            EvRange=itemView.findViewById(R.id.EvRange);
            EVtime=itemView.findViewById(R.id.EVtime);
            EVmode=itemView.findViewById(R.id.EVmode);

        }
    }
}
