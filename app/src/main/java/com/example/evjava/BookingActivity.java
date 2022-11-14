package com.example.evjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    RecyclerView bookingRview;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private final List<bookdb> myitems= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        bookingRview=findViewById(R.id.bookingRview);
        bookingRview.setHasFixedSize(true);
        bookingRview.setLayoutManager(new LinearLayoutManager(BookingActivity.this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myitems.clear();
                String uid=currentFirebaseUser.getUid();
                final String pushId = FirebaseDatabase.getInstance().getReference().push().getKey();
                for(DataSnapshot users: snapshot.child("booking-details").child(uid).getChildren()){
                    if(users.hasChild("date") && users.hasChild("sname")) {
                        final String date = users.child("date").getValue(String.class);
                        final String sname = users.child("sname").getValue(String.class);
                        final String stcity = users.child("stcity").getValue(String.class);
                        final String stloc = users.child("stloc").getValue(String.class);
                        final String sttype = users.child("sttype").getValue(String.class);
                        final String time = users.child("time").getValue(String.class);
                        final String pend = users.child("pend").getValue(String.class);

                        bookdb data = new bookdb(uid, sname, stloc, stcity, sttype, time, date, pend);
                        myitems.add(data);
                    }
                }
                bookingRview.setAdapter(new BookRVAdapter(myitems,BookingActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}