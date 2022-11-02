package com.example.evjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private  String userId;
    public Button sout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sout=(Button) findViewById(R.id.signout);
        sout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        user=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("users");
        userId=user.getUid();

        final TextView greetingTextView=(TextView) findViewById(R.id.gretings);
        final TextView mailTextView=(TextView) findViewById(R.id.empty_emailadds);
        final TextView nameTextView=(TextView) findViewById(R.id.empty_fulname);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user user=snapshot.getValue(com.example.evjava.user.class);
                if(user !=null){
                    String fullname=user.name;
                    String email=user.email;
                    greetingTextView.setText("Welcome, "+fullname);
                    nameTextView.setText(fullname);
                    mailTextView.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something Wrong happened!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}