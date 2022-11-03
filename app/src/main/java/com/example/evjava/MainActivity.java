package com.example.evjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public CardView c1,c2,c3,c4;
    public Button sout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private  String userId;
    public TextView welcome;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c1=(CardView) findViewById(R.id.manage_ev);
        c2=(CardView) findViewById(R.id.find_station);
        c3=(CardView) findViewById(R.id.view_books);
        c4=(CardView) findViewById(R.id.user);
        sout=(Button) findViewById(R.id.signout);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);

        sout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        user=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("userdetails");
        userId=user.getUid();

        final TextView greetingTextView=(TextView) findViewById(R.id.ds_welcome);
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user user=snapshot.getValue(com.example.evjava.user.class);
                if(user !=null){
                    String fullname=user.name;
                    String email=user.mail;
                    greetingTextView.setText("Welcome, "+fullname);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Something Wrong happened!", Toast.LENGTH_SHORT).show();

            }
        });

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