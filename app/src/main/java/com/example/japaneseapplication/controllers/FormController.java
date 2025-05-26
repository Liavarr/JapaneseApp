package com.example.japaneseapplication.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.HashMap;

public class FormController {

    private final Context context;
    private LinearLayout linearLayout;
    private FormHandler formHandler; // guarda la instancia

    public FormController(Context context, LinearLayout linearLayout) {
        this.context = context;
        this.linearLayout = linearLayout;
        this.formHandler = new FormHandler(context, linearLayout); // Creas aquí
    }

    public void handle(String type, String action) {
        if (formHandler == null) {
            formHandler = new FormHandler(context, linearLayout);
        }
        Log.d("FormController", "Handle action " + action + " for type " + type);
        formHandler.handleAction(action, type);
    }

    public HashMap<String, Object> collectFormData(){
        if (formHandler == null) {
            Log.w("FormController", "FormHandler is null when collecting data!");
            return new HashMap<>();
        }
        return formHandler.collectFormData();
    }

}
