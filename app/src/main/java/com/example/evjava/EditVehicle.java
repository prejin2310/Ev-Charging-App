package com.example.evjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class EditVehicle extends AppCompatActivity{
    RadioGroup radioGroup;
    RadioButton radioButton;
    String evtype;
    EditText model,acdc,av_range,fullcharge,evNumber;
    Button updateEV;

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vehicle);
        radioGroup=findViewById(R.id.radioGroup);
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        evtype=radioButton.getText().toString();

        model=findViewById(R.id.model);
        acdc=findViewById(R.id.acdc);
        av_range=findViewById(R.id.av_range);
        fullcharge=findViewById(R.id.fullcharge);
        evNumber=findViewById(R.id.evNumber);
        updateEV=findViewById(R.id.updateEV);



        updateEV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String modeltype=model.getText().toString();
                String typeofmode=acdc.getText().toString();
                String irange=av_range.getText().toString();
                String range=irange+" Km/h";

                String charge=fullcharge.getText().toString();
                String chargetym=charge + " Min";

                String number=evNumber.getText().toString();
                String uid=currentFirebaseUser.getUid();
                vehicledb vdb= new vehicledb(uid,modeltype,typeofmode,range,chargetym,number,evtype);

                final String pushId = FirebaseDatabase.getInstance().getReference().push().getKey();
                firebaseDatabase.getReference().child("user-vehicle").child(uid).child(pushId).setValue(vdb)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                AlertDialog.Builder builder=new AlertDialog.Builder(EditVehicle.this);
                                builder.setTitle("Sucessfully !");
                                builder.setMessage("Your vehicle added sucessfully in your list");
                                builder.show();
                                builder.setPositiveButton("OK",null);
                                Intent i=new Intent(EditVehicle.this,ManageActivity.class);
                                startActivity(i);
                            }
                        });
            }
        });



    }
}