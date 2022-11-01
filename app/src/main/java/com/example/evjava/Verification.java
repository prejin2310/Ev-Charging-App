package com.example.evjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Verification extends AppCompatActivity {

    Button verification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        verification=findViewById(R.id.veri_btn);
        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i =new Intent(Verification.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}