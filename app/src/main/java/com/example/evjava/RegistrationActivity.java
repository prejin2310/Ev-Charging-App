package com.example.evjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

public class RegistrationActivity extends AppCompatActivity {

    EditText name,email,pass,conpass;
    Button createbtn;
    TextView regtxt;
    ProgressBar pbar;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name=findViewById(R.id.fname);
        email=findViewById(R.id.emailreg);
        pass=findViewById(R.id.passtxt);
        conpass=findViewById(R.id.conpasstxt);
        pbar=findViewById(R.id.progressbar);
        regtxt=findViewById(R.id.login_text);
        createbtn=findViewById(R.id.create_ac_btn);
        mAuth=FirebaseAuth.getInstance();
        regtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbar.setVisibility(View.VISIBLE);
                String fname=name.getText().toString();
                String mail=email.getText().toString();
                String passw=pass.getText().toString();
                String cpassw=conpass.getText().toString();
                if(!passw.equals(cpassw)){
                    Toast.makeText(RegistrationActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(fname) && TextUtils.isEmpty(mail) && TextUtils.isEmpty(passw) && TextUtils.isEmpty(cpassw)){
                    Toast.makeText(RegistrationActivity.this, "Please Enter your credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(mail,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                pbar.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this, "User Registred!", Toast.LENGTH_SHORT).show();
                                Intent i= new Intent(RegistrationActivity.this,Verification.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                }
            }
        });




    }
}