package com.example.japaneseapplication.ui;



import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japaneseapplication.R;

public class ProfileActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDrawer(R.layout.profile_activity); // crea este layout también
    }
}
