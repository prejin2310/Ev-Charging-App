package com.example.evjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StationActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<dbstation> recycleList;
    List<dbstation> filterList=new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    SearchView searchView;
    EditText search;
    StationAdapter stationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        recyclerView=findViewById(R.id.recycleView);
        recycleList=new ArrayList<>();
        search=findViewById(R.id.search);



        firebaseDatabase=FirebaseDatabase.getInstance();
        StationAdapter recyclerAdapter=new StationAdapter(recycleList,getApplicationContext());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerAdapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterList.clear();
                if(editable.toString().isEmpty()){
                    recyclerView.setAdapter(new StationAdapter(recycleList, getApplicationContext()));
                    stationAdapter.notifyDataSetChanged();
                }
                else{
                    Filter(editable.toString());
                }

            }
        });


        firebaseDatabase.getReference().child("stations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    dbstation projectmodel=dataSnapshot.getValue(dbstation.class);
                    recycleList.add(projectmodel);
                }
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void Filter(String text) {
        for(dbstation post:recycleList){
            if(post.getStat_loc().equals(text)){
                filterList.add(post);
            }
        }
        recyclerView.setAdapter(new StationAdapter((ArrayList<dbstation>) filterList,getApplicationContext()));
        stationAdapter.notifyDataSetChanged();
    }


}