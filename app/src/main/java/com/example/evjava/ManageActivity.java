package com.example.evjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageActivity extends AppCompatActivity {
    Button addVehicle;
    RecyclerView EVRview;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private final List<vehicledb> myitems= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        EVRview=findViewById(R.id.EvRview);
        EVRview.setHasFixedSize(true);
        EVRview.setLayoutManager(new LinearLayoutManager(ManageActivity.this));

        addVehicle=findViewById(R.id.addVehicle);
        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ManageActivity.this,EditVehicle.class);
                startActivity(i);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myitems.clear();
                String uid=currentFirebaseUser.getUid();
                final String pushId = FirebaseDatabase.getInstance().getReference().push().getKey();
                for(DataSnapshot users: snapshot.child("user-vehicle").child(uid).getChildren()){
                    if(users.hasChild("chargetym") && users.hasChild("evtype")) {
                        final String modeltype = users.child("modeltype").getValue(String.class);
                        final String typeofmode = users.child("typeofmode").getValue(String.class);
                        final String range = users.child("range").getValue(String.class);
                        final String chargetym = users.child("chargetym").getValue(String.class);
                        final String number = users.child("number").getValue(String.class);


                        vehicledb data = new vehicledb(uid,modeltype,typeofmode,range,chargetym,number,number);
                        myitems.add(data);
                    }
                }
                EVRview.setAdapter(new VehicleAdapter(myitems,ManageActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}