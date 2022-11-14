package com.example.evjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.MyViewHolder> {

    private final List<bookdb> items;
    private final Context context;

    //constructor
    public BookRVAdapter(List<bookdb> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public BookRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rva_dapter,null));
    }

    @Override
    public void onBindViewHolder(@NonNull BookRVAdapter.MyViewHolder holder, int position) {

        bookdb data=items.get(position);
        holder.b_name.setText(data.getSname());
        holder.book_loc.setText(data.getStloc());
        holder.book_city.setText(data.getStcity());
        holder.book_date.setText(data.getDate());
        holder.book_time.setText(data.getTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //myholder class to hold view references from every item int the recycler view
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView b_name,book_loc,book_city,book_date,book_time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            b_name=itemView.findViewById(R.id.b_name);
            book_loc=itemView.findViewById(R.id.book_loc);
            book_city=itemView.findViewById(R.id.book_city);
            book_date=itemView.findViewById(R.id.book_date);
            book_time=itemView.findViewById(R.id.book_time);
        }
    }
}
