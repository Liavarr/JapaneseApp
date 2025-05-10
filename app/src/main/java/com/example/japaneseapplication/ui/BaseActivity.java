package com.example.japaneseapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.japaneseapplication.R;
import com.example.japaneseapplication.model.Vocabulary;
import com.example.japaneseapplication.repositories.VocabularyRepository;
import com.example.japaneseapplication.repositories.listener.OnVocabularyListResultListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected NavigationView navView;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
    }

    protected void setupDrawer(int layoutResID) {
        // Inflamos el layout general con el menú lateral
        DrawerLayout fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base_drawer, null);
        View activityContainer = fullLayout.findViewById(R.id.activity_content);

        // Inflamos el contenido específico de la actividad
        getLayoutInflater().inflate(layoutResID, (ViewGroup) activityContainer, true);
        super.setContentView(fullLayout);

        drawerLayout = fullLayout;
        navView = fullLayout.findViewById(R.id.nav_view);
        toolbar = fullLayout.findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.main_screen) {
                startActivity(new Intent(this, MainActivity.class));
            }else if (id == R.id.nav_perfil) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (id == R.id.nav_config) {
                startActivity(new Intent(this, ConfigurationActivity.class));
            } else if (id == R.id.nav_admin) {
                startActivity(new Intent(this, AdminPanelActivity.class));
            } else if (id == R.id.nav_logout) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
}

