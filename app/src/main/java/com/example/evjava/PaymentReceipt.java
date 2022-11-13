package com.example.evjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PaymentReceipt extends AppCompatActivity {

    TextView bookid,city,connType,timedate;
    Button backHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_receipt);

       Intent home=getIntent();
       String paymentid=home.getStringExtra("bookid");
       String pcity=home.getStringExtra("city");
       String ptype=home.getStringExtra("type");
       String ptime=home.getStringExtra("time");
       String pdate=home.getStringExtra("date");

       bookid=findViewById(R.id.bookid);
       city=findViewById(R.id.city);
       connType=findViewById(R.id.connType);
       timedate=findViewById(R.id.timedate);

       bookid.setText(paymentid);
       city.setText(pcity);
       connType.setText(ptype);
       timedate.setText(pdate+" "+ptime);

       backHome=findViewById(R.id.backHome);
       backHome.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(PaymentReceipt.this,MainActivity.class);
               startActivity(i);
           }
       });


    }
}