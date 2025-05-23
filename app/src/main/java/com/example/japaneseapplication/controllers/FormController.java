package com.example.japaneseapplication.controllers;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

public class FormController {

    private final Context context;
    private LinearLayout linearLayout;

    public FormController(Context context, LinearLayout linearLayout) {
        this.context = context;
        this.linearLayout = linearLayout;
    }

    public void handle(String type, String action) {
        switch (type) {
            case "vocabulary":
                Log.d("FormController", "Llamamos al formulario de vocabulario");
                new VocabularyFormHandler(context, linearLayout).handleAction(action);
                break;
            case "kanji":
                Log.d("FormController", "Llamamos al formulario de vocabulario");
                new KanjiFormHandler(context, linearLayout).handleAction(action);
                break;
            // ...
        }
    }

}
