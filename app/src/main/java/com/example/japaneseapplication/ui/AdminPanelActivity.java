package com.example.japaneseapplication.ui;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;

import com.example.japaneseapplication.R;
public class AdminPanelActivity extends BaseActivity{

    CardView vocabulary;
    CardView kanji;
    CardView grammar;
    CardView exam;
    CardView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDrawer(R.layout.admin_panel_activity); // crea este layout también
        initializeButtons();
        redirectButtons();
    }

    private void initializeButtons(){
        vocabulary = findViewById(R.id.edit_vocabulary);
        kanji = findViewById(R.id.edit_kanji);
        grammar = findViewById(R.id.edit_grammar);
        exam = findViewById(R.id.edit_exam);
        user = findViewById(R.id.edit_user);
    }

    private void redirectButtons(){
        vocabulary.setOnClickListener(view -> {
            startActivity(new Intent(this, AdminPanelEditVocabularyActivity.class));
        });
        kanji.setOnClickListener(View -> {
            startActivity(new Intent(this, AdminPanelEditVocabularyActivity.class));
        });
        grammar.setOnClickListener(View -> {
            startActivity(new Intent(this, AdminPanelEditVocabularyActivity.class));
        });
        exam.setOnClickListener(View -> {
            startActivity(new Intent(this, AdminPanelEditVocabularyActivity.class));
        });
        user.setOnClickListener(View -> {
            startActivity(new Intent(this, AdminPanelEditVocabularyActivity.class));
        });
    }
}
