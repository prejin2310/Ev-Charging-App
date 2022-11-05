package com.example.evjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SingleStationActivity extends AppCompatActivity {
    TextView Title,Desc,Loc,City,Type,Kw,Avail;
    ImageView Image;

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



    }
}