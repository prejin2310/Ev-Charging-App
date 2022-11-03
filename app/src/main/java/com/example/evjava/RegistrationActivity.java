package com.example.evjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    EditText name,email,pass,conpass,phno;
    Button createbtn;
    TextView regtxt;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name=findViewById(R.id.fname);
        email=findViewById(R.id.emailreg);
        pass=findViewById(R.id.passtxt);
        conpass=findViewById(R.id.conpasstxt);
        phno=findViewById(R.id.phone);

        regtxt=findViewById(R.id.login_text);
        createbtn=findViewById(R.id.create_ac_btn);
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname=name.getText().toString();
                String mail=email.getText().toString();
                String pnumber=phno.getText().toString();
                String passw=pass.getText().toString();
                String cpassw=conpass.getText().toString();
                if(fname.isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();
                }
                if(mail.isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Enter your mailid", Toast.LENGTH_SHORT).show();
                }
                if(pnumber.isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Enter your mailid", Toast.LENGTH_SHORT).show();
                }
                if(passw.isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();
                }
                if(!passw.equals(cpassw)){
                    Toast.makeText(RegistrationActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                if(!(TextUtils.isEmpty(fname) && TextUtils.isEmpty(mail) && TextUtils.isEmpty(pnumber)&& TextUtils.isEmpty(passw) && TextUtils.isEmpty(cpassw))){
                    firebaseAuth.createUserWithEmailAndPassword(mail,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent i=new Intent(RegistrationActivity.this,LoginActivity.class);
                                startActivity(i);

                                String uid=task.getResult().getUser().getUid();
                                userdetails user= new userdetails(uid,fname,mail,pnumber,passw,0);
                                firebaseDatabase.getReference().child("userdetails").child(uid).setValue(user);

                            }
                            else{
                                Toast.makeText(RegistrationActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}