package com.example.floweraplication;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.floweraplication.databinding.ActivityDizBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DizActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;

    private static final String TAG = "ADD_PLANT_TAG";
    private FirebaseAuth firebaseAuth;
    private ActivityDizBinding binding;
    TextView PlName, Descr, Descr1;
    TextView Dob;
    ImageView pngView;
    MaterialToolbar toolbar;
    ImageView PlImage;


    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        Descr = binding.Description;
        Descr1 = binding.description1;
        //configure progress dialog

        toolbar = binding.toolbar;


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        pngView = binding.PlImage;
        PlName = binding.PlName;

        //Uri uri = Uri.parse(getIntent().getStringExtra("PlPicture"));

        PlName.setText(getIntent().getStringExtra("PlName"));

        //Bundle extras = i.getExtras();
        byte[] bytes = getIntent().getByteArrayExtra("Bitmap");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
        Log.i(TAG, "check"+bytes);
        pngView.setImageBitmap(bmp);
        Uri uri = Uri.parse(getIntent().getStringExtra("Pa"));
        Log.i(TAG, "Uriririri"+uri);

        Bundle arguments = getIntent().getExtras();
        String Desiase_name = arguments.get("PlName").toString();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Desiase")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0)
                            {
                                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                                    String name = String.valueOf(dataSnapshot.child("name").getValue());
                                    if(name.equals(Desiase_name)){
                                        String plant_descr = String.valueOf(dataSnapshot.child("description").getValue());
                                        Log.d(TAG, "Хоть бы не сдохнкть "+plant_descr);

                                        Descr.setText(plant_descr);

                                        String sick_discr = String.valueOf(dataSnapshot.child("Sick_care/description").getValue());
                                        Log.d(TAG, "Хоть бы не сдохнкть "+sick_discr);
                                        Descr1.setText(sick_discr);
                                        //apppref1 = getSharedPreferences(APP_PREFERENCES1, Context.MODE_PRIVATE);
                                        //editor1.putString("mAppIUD", plant_id);
                                        //editor1.apply();
                                        
                                    }
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}