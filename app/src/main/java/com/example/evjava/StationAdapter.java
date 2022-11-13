package com.example.evjava;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder>{

    List<dbstation> list;


    public StationAdapter(ArrayList<dbstation> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.activity_stationlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        dbstation model= list.get(position);
        Picasso.get().load(model.getStat_pic()).placeholder(R.drawable.img_err).into(holder.itemImage);
        holder.stHeading.setText(model.stat_name);
        holder.show_avail.setText(model.stat_avail);
        holder.show_city.setText(model.stat_city);
        holder.show_desc.setText(model.stat_desc);
        holder.show_loc.setText(model.stat_loc);
        holder.show_type.setText(model.stat_type);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,SingleStationActivity.class);
                i.putExtra("sImage",model.getStat_pic());
                i.putExtra("sTitle",model.getStat_name());
                i.putExtra("sDesc",model.getStat_desc());
                i.putExtra("sLoc",model.getStat_loc());
                i.putExtra("sCity",model.getStat_city());
                i.putExtra("sType",model.getStat_type());
                i.putExtra("sKw",model.getStat_kw());
                i.putExtra("sAvail",model.getStat_avail());
                i.putExtra("MapLink",model.getStat_map());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public View cardView;
        TextView show_avail,show_city,show_desc,show_loc,stHeading,show_type;
        ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stHeading=itemView.findViewById(R.id.stHeading);
            show_avail=itemView.findViewById(R.id.show_avail);
            show_city=itemView.findViewById(R.id.show_city);
            show_desc=itemView.findViewById(R.id.show_desc);
            show_loc=itemView.findViewById(R.id.show_loc);
            itemImage=itemView.findViewById(R.id.itemImage);
            show_type=itemView.findViewById(R.id.show_type);

        }
    }

}
