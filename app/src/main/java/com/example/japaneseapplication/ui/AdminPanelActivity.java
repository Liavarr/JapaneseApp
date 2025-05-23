package com.example.japaneseapplication.ui;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;

import com.example.japaneseapplication.R;
public class AdminPanelActivity extends BaseActivity{
    // Variables
    CardView vocabulary;
    CardView kanji;
    CardView grammar;
    CardView exam;
    CardView user;
    String toEdit;

    //Initializer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDrawer(R.layout.admin_panel_activity); // crea este layout también
        initializeButtons();
        redirectButtons();
    }

    // Assign the buttons to the variable to the id of the activity layout
    private void initializeButtons(){
        vocabulary = findViewById(R.id.edit_vocabulary);
        kanji = findViewById(R.id.edit_kanji);
        grammar = findViewById(R.id.edit_grammar);
        exam = findViewById(R.id.edit_exam);
        user = findViewById(R.id.edit_user);
    }

    // Assign to each button what it does
    private void redirectButtons(){
        vocabulary.setOnClickListener(view -> {
            startEditActivity("vocabulary");
        });

        kanji.setOnClickListener(view -> {
            startEditActivity("kanji");
        });

        grammar.setOnClickListener(view -> {
            startEditActivity("grammar");
        });

        exam.setOnClickListener(view -> {
            startEditActivity("exam");
        });

        user.setOnClickListener(view -> {
            startEditActivity("user");
        });
    }

    // Class to send in the parameter what you are going to do
    private void startEditActivity(String toEdit) {
        Intent intent = new Intent(this, AdminPanelCRUDActivity.class);
        intent.putExtra("toEdit", toEdit);
        startActivity(intent);
    }
}
