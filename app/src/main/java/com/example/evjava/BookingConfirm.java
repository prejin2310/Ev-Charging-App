package com.example.evjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class BookingConfirm extends AppCompatActivity implements PaymentResultListener {
    TextView sta_name,sta_loc,sta_city,sta_date,sta_time;
    Button payment1;
    String stloc,sttype,TimePick,currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        Intent i1=getIntent();
        Intent i=getIntent();
        String stationname1= i.getStringExtra("Name");
        stloc=i.getStringExtra("Loc");
        sttype=i.getStringExtra("Type");
        String stcity=i.getStringExtra("City");
        TimePick=i1.getStringExtra("Time");
        currentDate=i1.getStringExtra("Date");

        sta_name=findViewById(R.id.sta_name);
        sta_loc=findViewById(R.id.sta_loc);
        sta_city=findViewById(R.id.sta_city);
        sta_date=findViewById(R.id.sta_date);
        sta_time=findViewById(R.id.sta_time);

        sta_name.setText(stationname1);
        sta_loc.setText(stloc);
        sta_city.setText(stcity);
        sta_date.setText(currentDate);
        sta_time.setText(TimePick);


        payment1=findViewById(R.id.payment1);
        String sAmount="150";
        int amount=Math.round(Float.parseFloat(sAmount)*100);
        payment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialize razopay checkout
                Checkout checkout=new Checkout();
                //set key id
                checkout.setKeyID("rzp_test_pD6L1yncumoBjZ");
                //initialize json object
                JSONObject object=new JSONObject();
                try {
                    object.put("name","User 1");
                    object.put("description","Test payment");
                    object.put("theme.color","#FF54B435");
                    object.put("currency","INR");
                    object.put("amount",amount);
                   checkout.open(BookingConfirm.this,object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public void onPaymentSuccess(String s) {



        //Initialize alert dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //set title
        builder.setTitle("Payment Confirmed \n Payment ID");
        //set message
        builder.setMessage(s);
        //show alert dialog
        builder.show();
        Intent home=new Intent(BookingConfirm.this,PaymentReceipt.class);
        home.putExtra("bookid",s);
        home.putExtra("city",stloc);
        home.putExtra("type",sttype);
        home.putExtra("time",TimePick);
        home.putExtra("date",currentDate);
        startActivity(home);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();

    }
}