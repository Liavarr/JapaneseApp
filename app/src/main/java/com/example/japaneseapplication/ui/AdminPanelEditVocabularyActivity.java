package com.example.japaneseapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.japaneseapplication.R;
import com.example.japaneseapplication.interfaces.AdminPanelCRUDInterface;
import com.example.japaneseapplication.model.Vocabulary;
import com.example.japaneseapplication.repositories.VocabularyRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AdminPanelEditVocabularyActivity extends BaseActivity implements AdminPanelCRUDInterface<VocabularyRepository,Vocabulary> {
    private Spinner spinner;
    private LinearLayout formAdd;
    private LinearLayout formUpdate;
    private LinearLayout formDelete;
    private VocabularyRepository vocabularyRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setupDrawer(R.layout.admin_panel_activity_edit_vocabulary); // crea este layout también
        initializeButtons();
    }

    private void initializeButtons(){
        VocabularyRepository vocabularyRepository = new VocabularyRepository();
        spinner = findViewById(R.id.selection_spinner);
        formAdd = findViewById(R.id.form_add);
        formUpdate = findViewById(R.id.form_update);
        formDelete = findViewById(R.id.form_delete);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();

                // Oculta todos los formularios primero
                formAdd.setVisibility(View.GONE);
                formUpdate.setVisibility(View.GONE);
                formDelete.setVisibility(View.GONE);

                // Muestra el que corresponde
                switch (selected) {
                    case "Añadir":
                        formAdd.setVisibility(View.VISIBLE);
                        break;
                    case "Actualizar":
                        formUpdate.setVisibility(View.VISIBLE);
                        break;
                    case "Borrar":
                        formDelete.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    @Override
    public void create(VocabularyRepository vocabularyRepository, Vocabulary vocabulary) {
        Vocabulary vocab = new Vocabulary(
                null, // ID generado automáticamente
                new HashMap<String, String>() {{
                    put("en", "to read");
                    put("es", "leer");
                    put("jp", "読む");
                }},
                new HashMap<String, String>() {{
                    put("en", "I read a book.");
                    put("es", "Leo un libro.");
                    put("jp", "本を読みます。");
                }},
                new HashMap<String, String>() {{
                    put("general", "Common verb for reading books, newspapers, etc.");
                }},
                "N5",           // Nivel
                true,           // Regular
                "verb",         // Categoría
                new ArrayList<>(Arrays.asList("colores", "ropa", "comida")),    // Subcategoría
                "grupo 1",      // Grupo
                new HashMap<String, String>() {{
                    // No necesita conjugaciones irregulares porque es regular
                }},
                new HashMap<String, String>() {{
                    put("読", "よ");
                }},
                2               // Dificultad
        );
        vocabularyRepository.createVocabulary(Vocabulary vocabulary);
    }

    @Override
    public void retrieve(VocabularyRepository vocabularyRepository, Vocabulary vocabulary) {

    }

    @Override
    public void update(VocabularyRepository vocabularyRepository, Vocabulary vocabulary) {

    }

    @Override
    public void delete(VocabularyRepository vocabularyRepository, Vocabulary vocabulary) {

    }
}
