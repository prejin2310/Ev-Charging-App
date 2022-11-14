package com.example.evjava;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SingleStationActivity extends AppCompatActivity {
    TextView Title,Desc,Loc,City,Type,Kw,Avail;
    ImageView Image;
    Button btnBook,btnDir;

    private FirebaseUser user;
    private DatabaseReference reference;
    private  String userId;
    public String MapLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_station);

        Title=findViewById(R.id.sTitle);
        Desc=findViewById(R.id.sDesc);
        Loc=findViewById(R.id.sLoc);
        City=findViewById(R.id.sCity);
        Title=findViewById(R.id.sTitle);
        Kw=findViewById(R.id.sKw);
        Type=findViewById(R.id.sType);
        Avail=findViewById(R.id.sAvail);
        Image=findViewById(R.id.sImage);
        btnDir=findViewById(R.id.btnDir);

        Picasso.get().load(getIntent().getStringExtra("sImage"))
                .placeholder(R.drawable.img_err)
                .into(Image);
        Title.setText(getIntent().getStringExtra("sTitle"));
        Desc.setText(getIntent().getStringExtra("sDesc"));
        Loc.setText(getIntent().getStringExtra("sLoc"));
        City.setText(getIntent().getStringExtra("sCity"));
        Avail.setText(getIntent().getStringExtra("sAvail"));
        Type.setText(getIntent().getStringExtra("sType"));
        Kw.setText(getIntent().getStringExtra("sKw"));
//        btnDir.setText(getIntent().getStringExtra("MapLink"));

        String Title=getIntent().getStringExtra("sTitle");
        String sLoc=getIntent().getStringExtra("sLoc");
        String type=getIntent().getStringExtra("sType");
        String city=getIntent().getStringExtra("sCity");
        String link=getIntent().getStringExtra("MapLink");


        //button click code
        btnBook=findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SingleStationActivity.this, SelectTime.class);
                i.putExtra("STitle",Title);
                i.putExtra("sLoc",sLoc);
                i.putExtra("sType",type);
                i.putExtra("sCity",city);
                startActivity(i);
            }
        });

        btnDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_VIEW,Uri.parse(link));
                startActivity(intent);
            }
        });





    }

}