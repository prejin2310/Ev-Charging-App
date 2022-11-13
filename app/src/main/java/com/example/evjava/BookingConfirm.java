package com.example.evjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BookingConfirm extends AppCompatActivity {
    TextView sta_name,sta_loc,sta_city,sta_date,sta_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        Intent in=getIntent();
        String stationname1= in.getStringExtra("STitle");
        String stloc=in.getStringExtra("sLoc");
        String sttype=in.getStringExtra("sType");
        String stcity=in.getStringExtra("sCity");
        String time=in.getStringExtra("Time");
        String date=in.getStringExtra("Date");

        sta_name=findViewById(R.id.sta_name);
        sta_loc=findViewById(R.id.sta_loc);
        sta_city=findViewById(R.id.sta_city);
        sta_date=findViewById(R.id.sta_date);
        sta_time=findViewById(R.id.sta_time);

        sta_name.setText(stationname1);
        sta_loc.setText(stloc);
        sta_city.setText(stcity);
        sta_date.setText(date);
        sta_time.setText(time);


    }
}