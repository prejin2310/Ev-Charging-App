package com.example.evjava;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class SelectTime extends AppCompatActivity {

    GridView gridViewSample;
    static final String[] gridViewStringValue = new String[]{
            "05:00 AM", "06:00 AM", "07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM",
            "03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM", "08:00 PM", "09:00 PM", "10:00 PM"
    };
    String TimePick;
    TextView selectTime,CurrentDate;
    Button btnConfirm;
    TextView sta_name,sta_loc,sta_city;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);
        selectTime=findViewById(R.id.selectTime);
        CurrentDate=findViewById(R.id.CurrentDate);
        gridViewSample =findViewById(R.id.simple_grid_view_example);
        btnConfirm=findViewById(R.id.btnConfirm);
        sta_name=findViewById(R.id.sta_name);
        sta_loc=findViewById(R.id.sta_loc);
        sta_city=findViewById(R.id.sta_city);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, gridViewStringValue);
        gridViewSample.setAdapter(adapter);

        //current date instance
        Calendar calendar=Calendar.getInstance();
        String currentDate= DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());

        //collecting details from previous selected station
        Intent i=getIntent();
        String stationname= i.getStringExtra("STitle");
        String stloc=i.getStringExtra("sLoc");
        String sttype=i.getStringExtra("sType");
        String stcity=i.getStringExtra("sCity");

        sta_name.setText(stationname);
        sta_loc.setText(stloc);
        sta_city.setText(stcity);

        //firebase authentication




        //grid click details fetch (time slot)

        gridViewSample.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText() + " is Clicked", Toast.LENGTH_SHORT).show();
                TimePick=((TextView) v).getText().toString();
                selectTime.setText("Time: "+TimePick);
                CurrentDate.setText("Date : "+currentDate);


            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid=currentFirebaseUser.getUid();
                final String pushId = FirebaseDatabase.getInstance().getReference().push().getKey();
                bookdb book=new bookdb(uid,stationname,stloc,stcity,sttype,TimePick,currentDate,"no");
                firebaseDatabase.getReference().child("booking-details").child(uid).child(pushId).setValue(book)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SelectTime.this, "Make Payment", Toast.LENGTH_SHORT).show();
                                Intent i1=new Intent(SelectTime.this,BookingConfirm.class);
                                i1.putExtra("Date",currentDate);
                                i1.putExtra("Time",TimePick);
                                i1.putExtra("Name",stationname);
                                i1.putExtra("Loc",stloc);
                                i1.putExtra("City",sttype);
                                i1.putExtra("Type",sttype);
                                startActivity(i1);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SelectTime.this, "Server Busy!. Unable to book", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });







    }
}