package com.example.floweraplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.example.floweraplication.databinding.ActivityAdminButonsBinding;
import com.example.floweraplication.databinding.ActivityPlantDetailBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;

public class PlantDetailActivity extends AppCompatActivity {

    private ActivityPlantDetailBinding binding;

    TextView PlName,PlText,PlType,PlHabitat,PlSize,PlEndurance,PlPurpose,PlTox;
    ImageView PlImage;
    AppBarLayout toolbar;

   Toolbar tlb;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlantDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tlb=binding.toolbar;
        setSupportActionBar(tlb);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        PlName = binding.PlName;
        PlText = binding.PlText;
        PlType = binding.PlType;
        PlHabitat = binding.PlHabitat;
        PlSize = binding.PlSize;
        PlEndurance = binding.PlEndurance;
        PlPurpose = binding.PlPurpose;
        PlTox = binding.PlTox;
        PlImage = binding.PlImage;

        Glide.with(PlantDetailActivity.this).load(getIntent().getStringExtra("PlImage")).into(PlImage);

        PlName.setText(getIntent().getStringExtra("PlName"));
        PlText.setText(getIntent().getStringExtra("PlText"));
        PlType.setText(getIntent().getStringExtra("PlType"));
        PlHabitat.setText(getIntent().getStringExtra("PlHabitat"));
        PlSize.setText(getIntent().getStringExtra("PlSize"));
        PlEndurance.setText(getIntent().getStringExtra("PlEndurance"));
        PlPurpose.setText(getIntent().getStringExtra("PlPurpose"));
        PlTox.setText(getIntent().getStringExtra("PlTox"));

    }
}