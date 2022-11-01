package com.example.evjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public CardView c1,c2,c3,c4;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c1=(CardView) findViewById(R.id.manage_ev);
        c2=(CardView) findViewById(R.id.find_station);
        c3=(CardView) findViewById(R.id.view_books);
        c4=(CardView) findViewById(R.id.user);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.manage_ev:
                i=new Intent(this,ManageActivity.class);
                startActivity(i);
                break;

            case R.id.find_station:
                i=new Intent(this,StationActivity.class);
                startActivity(i);
                break;

            case R.id.view_books:
                i=new Intent(this,BookingActivity.class);
                startActivity(i);
                break;

            case R.id.user:
                i=new Intent(this,ProfileActivity.class);
                startActivity(i);
                break;
        }

    }
}