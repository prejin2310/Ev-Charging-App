package com.example.evjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
    public Button sout;
    public CardView cr1,cr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        cr1= (CardView) findViewById(R.id.admin_station);
        cr2= (CardView) findViewById(R.id.admin_book);

        sout=(Button) findViewById(R.id.signout);
        sout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(AdminActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        cr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminActivity.this,Admin_Stations.class);
                startActivity(i);
            }
        });
        cr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminActivity.this,Admin_Book.class);
                startActivity(i);
            }
        });


    }
}