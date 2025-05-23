package com.example.japaneseapplication.controllers;

import android.content.Context;
import android.widget.LinearLayout;

public class KanjiFormHandler {
    private final Context context;
    private LinearLayout linearLayout;
    public KanjiFormHandler(Context context, LinearLayout linearLayout) {
        this.context = context;
        this.linearLayout = linearLayout;
    }

    public void handleAction(String action) {
        switch (action) {
            case "add":
                addForm();
                break;
            case "delete":
                deleteForm();
                break;
            case "update":
                updateForm();
                break;
        }
    }

    private void addForm() {
        // Crear formulario dinámico para añadir vocabulario
    }

    private void deleteForm() {
        // Mostrar selector de vocabulario y borrarlo
    }

    private void updateForm() {
        // Mostrar formulario con vocabulario ya cargado
    }
}
