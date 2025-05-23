package com.example.japaneseapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.japaneseapplication.R;
import com.example.japaneseapplication.auth.SessionManager;
import com.example.japaneseapplication.controllers.FormController;
import com.example.japaneseapplication.interfaces.AdminPanelCRUDInterface;
import com.example.japaneseapplication.model.Vocabulary;
import com.example.japaneseapplication.repositories.VocabularyRepository;
import com.google.android.gms.tasks.OnSuccessListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AdminPanelCRUDActivity extends BaseActivity {
    // Attributes
    private Spinner spinner;
    private LinearLayout formAdd;
    private LinearLayout formUpdate;
    private LinearLayout formDelete;
    private VocabularyRepository vocabularyRepository;
    private Button create;
    private FormController formController;
    private String toEdit;
    // main to initialiaze
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // We receive what we gonna edit here:
        Intent intent = getIntent();
        toEdit = intent.getStringExtra("toEdit");
        Log.d("AdminPanelCrudActivity", "Estamos editando "+toEdit);
        super.onCreate(savedInstanceState);
        setupDrawer(R.layout.admin_panel_activity_crud); // crea este layout también
        initializeButtons();
    }

    //Assign to the buttons the actions
    private void initializeButtons(){

        VocabularyRepository vocabularyRepository = new VocabularyRepository();
        spinner = findViewById(R.id.selection_spinner);
        formAdd = findViewById(R.id.form_add);
        formUpdate = findViewById(R.id.form_update);
        formDelete = findViewById(R.id.form_delete);
        create = findViewById(R.id.create);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                // Limpiamos los formularios
                formAdd.removeAllViews();
                formUpdate.removeAllViews();
                formDelete.removeAllViews();
                // Oculta todos los formularios primero
                formAdd.setVisibility(View.GONE);
                formUpdate.setVisibility(View.GONE);
                formDelete.setVisibility(View.GONE);

                // Muestra el que corresponde
                switch (selected) {
                    case "add":
                        Log.d("AdminPanelCrudActivity", "Entramos a formulario de añadir");
                        formController = new FormController(getApplicationContext(), formAdd);
                        formController.handle(toEdit, "add");
                        formAdd.setVisibility(View.VISIBLE);
                        break;
                    case "delete":
                        Log.d("AdminPanelCrudActivity", "Entramos a formulario de borrar");
                        formController = new FormController(getApplicationContext(), formDelete);
                        formController.handle(toEdit, "delete");
                        formUpdate.setVisibility(View.VISIBLE);
                        break;
                    case "update":
                        Log.d("AdminPanelCrudActivity", "Entramos a formulario de actualizar");
                        formController = new FormController(getApplicationContext(), formUpdate);
                        formController.handle(toEdit, "update");
                        formDelete.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("AdminPanelCrudActivity", "Nada elegido en el spinner");
            }
        });

        create.setOnClickListener(v ->{
            SessionManager sessionManager = new SessionManager();
            sessionManager.loginWithEmailPassword("","", onSuccess ->{
                System.out.println("Te has podido logear");
            }, onFailure ->{
                System.out.println("No te has podido logear");
            });
            //create(vocabularyRepository, new Vocabulary());
        });
    }


    /*@Override
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
                    put("jp", "本や新聞などを読む時によく使う動詞。");
                    put("en", "Common verb for reading books, newspapers, etc.");
                    put("es", "Verbo común para leer libros, noticias, etc.");
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
        //OnSuccessListener<Void> onSuccess = null;
        // OnFailureListener onFailure = null;
        //Runnable onMeaningAlreadyExist = null;
        vocabularyRepository.createVocabulary(vocab, onSuccess ->{
                System.out.println("Creado");
            }, onFailure -> {
                System.out.println("No creado");
            }, () ->{
                    System.out.println("El significado ya existe");
            });
    }

    @Override
    public void retrieve(VocabularyRepository vocabularyRepository, Vocabulary vocabulary) {

    }

    @Override
    public void update(VocabularyRepository vocabularyRepository, Vocabulary vocabulary) {

    }

    @Override
    public void delete(VocabularyRepository vocabularyRepository, Vocabulary vocabulary) {

    }*/
}
