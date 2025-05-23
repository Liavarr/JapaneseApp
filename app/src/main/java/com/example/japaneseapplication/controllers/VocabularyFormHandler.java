package com.example.japaneseapplication.controllers;
import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.japaneseapplication.model.Vocabulary;
import com.example.japaneseapplication.utils.Categories;
import com.example.japaneseapplication.utils.Languages;
import com.example.japaneseapplication.utils.Levels;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


public class VocabularyFormHandler {
    private final Context context;
    private LinearLayout linearLayout;
    String[] languages = Languages.languages;
    String[] categories = Categories.category;
    String[] group = Categories.group;
    String[] subcategory = Categories.subcategory;
    private Levels levels;

    public VocabularyFormHandler(Context context, LinearLayout linearLayout) {
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
        Log.d("VocabularyFormHandler", "Estamos añadiendo en el formulario de vocabulario");
        Field[] fields = Vocabulary.class.getDeclaredFields();

        Arrays.stream(fields)
                .filter(f -> f.getAnnotation(FormIgnore.class) == null) // 👈 ignora campos con @FormIgnore
                .sorted(Comparator.comparingInt(f -> {
                    FormOrder order = f.getAnnotation(FormOrder.class);
                    return (order != null) ? order.value() : Integer.MAX_VALUE;
                }))
                .forEach(field -> {
                    field.setAccessible(true);

                    Class<?> type = field.getType();

                    if (type == String.class) {
                        EditText editText = new EditText(context);
                        editText.setHint("Enter " + field.getName());
                        linearLayout.addView(editText);

                    } else if (type == int.class || type == Integer.class) {
                        EditText editNumber = new EditText(context);
                        editNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                        editNumber.setHint("Enter " + field.getName());
                        linearLayout.addView(editNumber);

                    } else if (type == boolean.class || type == Boolean.class) {
                        CheckBox checkBox = new CheckBox(context);
                        checkBox.setText("Is " + field.getName());
                        linearLayout.addView(checkBox);

                    } else if (type == HashMap.class) {
                        TextView textView = new TextView(context);
                        textView.setText("Enter " + field.getName());
                        linearLayout.addView(textView); // título del campo
                        int contador = 0;
                        for (String language : languages) {
                            System.out.println(language);
                            // Contenedor horizontal por idioma
                            LinearLayout row = new LinearLayout(context);
                            row.setOrientation(LinearLayout.HORIZONTAL);
                            row.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                            row.setPadding(0, 8, 0, 8); // un poco de espacio vertical

                            // Texto del idioma
                            TextView langLabel = new TextView(context);
                            langLabel.setText(language);
                            langLabel.setLayoutParams(new LinearLayout.LayoutParams(
                                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1)); // 1 peso

                            // Campo de texto
                            EditText editText = new EditText(context);
                            editText.setHint("Enter in " + language);
                            editText.setLayoutParams(new LinearLayout.LayoutParams(
                                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 2)); // 2 peso

                            // Agrega al layout horizontal
                            row.addView(langLabel);
                            row.addView(editText);

                            // Agrega la fila al formulario
                            linearLayout.addView(row);
                            contador++;
                        }
                    } else {
                        TextView unsupported = new TextView(context);
                        unsupported.setText("Unsupported field: " + field.getName());
                        linearLayout.addView(unsupported);
                    }
                });

    }

    private void deleteForm() {
        // Mostrar selector de vocabulario y borrarlo
    }

    private void updateForm() {
        // Mostrar formulario con vocabulario ya cargado
    }
}
