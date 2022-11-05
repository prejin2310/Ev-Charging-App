package com.example.evjava;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class UploadStations extends AppCompatActivity {
    EditText stat_name,stat_desc,stat_loc,stat_city,stat_map,stat_avail,stat_type,stat_kw;
    ImageView uploadbtn,stat_pic;
    Button submit;
    Uri ImageUrl;
    RelativeLayout relativeLayout;


    public FirebaseDatabase database=FirebaseDatabase.getInstance();
    public FirebaseStorage firebaseStorage;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_stations);

        stat_name=findViewById(R.id.stat_name);
        stat_desc=findViewById(R.id.stat_desc);
        stat_loc=findViewById(R.id.stat_loc);
        stat_city=findViewById(R.id.stat_city);
        stat_map=findViewById(R.id.stat_map);
        stat_avail=findViewById(R.id.stat_avail);
        uploadbtn=findViewById(R.id.uploadbtn);
        stat_type=findViewById(R.id.stat_type);
        stat_kw=findViewById(R.id.stat_kw);

        stat_pic=findViewById(R.id.stat_pic);
        submit=findViewById(R.id.submit);
        relativeLayout=findViewById(R.id.relative);


        firebaseStorage=FirebaseStorage.getInstance();
        dialog=new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);
        dialog.setTitle("Data Uploading");
        dialog.setCanceledOnTouchOutside(false);

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
                relativeLayout.setVisibility(View.VISIBLE);
                uploadbtn.setVisibility(View.GONE);

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
                final StorageReference reference=firebaseStorage.getReference().child("stations")
                        .child(System.currentTimeMillis()+"");
                reference.putFile(ImageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                dbstation model=new dbstation();
                                model.setStat_name(stat_name.getText().toString());
                                model.setStat_desc(stat_desc.getText().toString());
                                model.setStat_loc(stat_loc.getText().toString());
                                model.setStat_city(stat_city.getText().toString());
                                model.setStat_map(stat_map.getText().toString());
                                model.setStat_avail(stat_avail.getText().toString());
                                model.setStat_type(stat_type.getText().toString());
                                model.setStat_kw(stat_kw.getText().toString());

                                model.setStat_pic(uri.toString());

                                database.getReference().child("stations").push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                                Toast.makeText(UploadStations.this, "Station Data Uploaded", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                dialog.dismiss();
                                                Toast.makeText(UploadStations.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            }
                        });
                    }
                });

            }
        });


    }

    private void UploadImage() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent i = new Intent();
                        i.setType("image/*");
                        i.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(i, 101);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(UploadStations.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && resultCode==RESULT_OK){
            ImageUrl=data.getData();
            stat_pic.setImageURI(ImageUrl);


        }
    }
}