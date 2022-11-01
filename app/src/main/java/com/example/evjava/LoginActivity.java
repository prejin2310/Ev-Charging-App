package com.example.evjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextView mail,pass;
    Button login;
    ProgressBar progressbar;
    TextView createAcc;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail=findViewById(R.id.emailreg);
        pass=findViewById(R.id.passtxt);
        login=findViewById(R.id.login_btn);
        createAcc=findViewById(R.id.create_account);
        progressbar=findViewById(R.id.progressbar);
        mAuth=FirebaseAuth.getInstance();

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar.setVisibility(View.VISIBLE);
                String email=mail.getText().toString();
                String passx=pass.getText().toString();
                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(passx)){
                    Toast.makeText(LoginActivity.this, "Please enter your credentials/", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    mAuth.signInWithEmailAndPassword(email,passx).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressbar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "login successfu", Toast.LENGTH_SHORT).show();
                                Intent i= new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            } else{
                                progressbar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
////This function checks the user is already login , then automatically redirect to main page for next time app open
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            Intent i =new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
        }
    }
}