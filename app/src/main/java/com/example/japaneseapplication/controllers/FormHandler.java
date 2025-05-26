package com.example.japaneseapplication.controllers;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.japaneseapplication.model.Kanji;
import com.example.japaneseapplication.model.Vocabulary;
import com.example.japaneseapplication.repositories.KanjiRepository;
import com.example.japaneseapplication.repositories.VocabularyRepository;
import com.example.japaneseapplication.repositories.listener.OnResultListener;
import com.example.japaneseapplication.utils.Categories;
import com.example.japaneseapplication.utils.ConjugationForms;
import com.example.japaneseapplication.utils.Languages;
import com.example.japaneseapplication.utils.Levels;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class FormHandler {
    // Contextos y vistas
    private final Context context;
    private LinearLayout linearLayout;

    // Valores estaticos cogidos de otros lugares
    private String[] languages = Languages.languages;
    private String[] categories = Categories.category;
    private String[] groups = Categories.group;
    private String[] subcategories = Categories.subcategory;
    private String[] levels = Levels.level;
    private ArrayList<String> verbalForms = new ArrayList<>(Arrays.asList(ConjugationForms.verbalForm));


    // Variables para retornar datos:
    private final HashMap<String, Object> fieldViews = new HashMap<>();
    // Para campos que contienen múltiples EditText (List<String>)
    private final HashMap<String, List<EditText>> listFieldViews = new HashMap<>();
    // Para campos de tipo HashMap<String, String>
    private final HashMap<String, HashMap<String, EditText>> hashmapFieldViews = new HashMap<>();
    // Para almacenar los valores de furiganas si existen
    private final List<FuriganaEntry> furiganaEntries = new ArrayList<>();
    private final List<ConjugationEntry> conjugationEntries = new ArrayList<>();

    public FormHandler(Context context){
        this.context = context;
    }
    public FormHandler(Context context, LinearLayout linearLayout) {
        this.context = context;
        this.linearLayout = linearLayout;
    }


    public void handleAction(String action, String toEdit) {
        switch (action) {
            case "add":
                addForm(toEdit);
                break;
            case "delete":
                deleteForm(toEdit);
                break;
            case "update":
                updateForm(toEdit);
                break;
        }
    }

    private void addForm(String type) {
        // Crear formulario dinámico para añadir vocabulario
        Log.d("VocabularyFormHandler", "Estamos añadiendo en el formulario de vocabulario");
        Field[] fields;
        if (type.equals("vocabulary")){
            fields = Vocabulary.class.getDeclaredFields();
            generateForm(fields);
        } else if (type.equals("kanji")) {
            fields = Kanji.class.getDeclaredFields();
            generateForm(fields);
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder((Activity) context);
            builder.setTitle("El tipo no está permitido!");
        }
    }


    private void generateForm(Field[] fields){
        Arrays.stream(fields)
                .filter(f -> f.getAnnotation(FormIgnore.class) == null) // 👈 ignora campos con @FormIgnore
                .sorted(Comparator.comparingInt(f -> {
                    FormOrder order = f.getAnnotation(FormOrder.class);
                    return (order != null) ? order.value() : Integer.MAX_VALUE;
                }))
                .forEach(field -> {
                    //hacemos que el campo sea accesible y asignamos a la clase type el tipo de dato
                    field.setAccessible(true);
                    Class<?> type = field.getType();

                    // Creamos un separadoR para cada uno asi es mas visual
                    View separator = new View(context);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            2 // Alto de la línea
                    );
                    params.setMargins(0, 16, 0, 16); // Márgenes opcionales
                    separator.setLayoutParams(params);
                    separator.setBackgroundColor(Color.LTGRAY); // Puedes usar otro color
                    linearLayout.addView(separator);

                    // CHECKEO DE SI ES UN STRING
                    if (type == String.class) {
                        Log.d("VocabularyFormHandler", "addForm: entramos a crear string");
                        if (field.getName().equalsIgnoreCase("level")) {
                            generateSpinnerString(levels, "level", new Spinner(context));
                        } else if (field.getName().equalsIgnoreCase("group")) {
                            generateSpinnerString(groups, "group", new Spinner(context));
                        } else if (field.getName().equalsIgnoreCase("category")) {
                            generateSpinnerString(categories, "category", new Spinner(context));
                        } else {
                            EditText editText = new EditText(context);
                            editText.setHint("Enter " + field.getName());
                            linearLayout.addView(editText);
                            fieldViews.put(field.getName(), editText);
                        }

                    // CHECKEO DE SI ES UN numero
                    } else if (type == int.class || type == Integer.class) {
                        Log.d("VocabularyFormHandler", "addForm: entramos a crear int");
                        EditText editNumber = new EditText(context);
                        editNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                        editNumber.setHint("Enter " + field.getName());
                        linearLayout.addView(editNumber);
                        fieldViews.put(field.getName(), editNumber);

                    // CHECKEO DE SI ES UN BOOLEANO, SOPORTANDO EL REGULAR, QUE AÑADIRÁ UN FORMULARIO EXTRA PARA METER LAS FORMAS
                    } else if (type == boolean.class || type == Boolean.class) {
                        Log.d("VocabularyFormHandler", "addForm: entramos a crear boolean");
                        CheckBox checkBox = new CheckBox(context);
                        checkBox.setText("Is " + field.getName());
                        checkBox.setChecked(true);

                        Button addVerbalFormsButton = new Button(context);
                        addVerbalFormsButton.setText("Agregar forma verbal");

                        ArrayList<LinearLayout> addedRows = new ArrayList<>(); // para eliminar luego

                        linearLayout.addView(checkBox);

                        // Lógica de click del botón
                        addVerbalFormsButton.setOnClickListener(view -> {
                            if (verbalForms.isEmpty()) return;
                            // 🚨 Crear un nuevo Spinner cada vez
                            Spinner spinner = new Spinner(context);
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, verbalForms);
                            spinner.setAdapter(adapter);

                            AlertDialog.Builder builder = new AlertDialog.Builder((Activity) context);
                            builder.setTitle("Selecciona forma verbal")
                                    .setView(spinner)
                                    .setPositiveButton("Aceptar", (dialog, which) -> {
                                        String seleccionada = (String) spinner.getSelectedItem();
                                        verbalForms.remove(seleccionada);

                                        LinearLayout row = new LinearLayout(context);
                                        row.setOrientation(LinearLayout.HORIZONTAL);

                                        TextView label = new TextView(context);
                                        label.setText(seleccionada + ": ");

                                        EditText editText = new EditText(context);
                                        editText.setHint("Conjugación");

                                        Button removeBtn = new Button(context);
                                        removeBtn.setText("❌");

                                        removeBtn.setOnClickListener(vv -> {
                                            linearLayout.removeView(row);
                                            verbalForms.add(seleccionada);
                                            addedRows.remove(row);
                                        });

                                        row.addView(label);
                                        row.addView(editText);
                                        row.addView(removeBtn);

                                        linearLayout.addView(row);
                                        addedRows.add(row);

                                        // Crear un nuevo ConjugationEntry cada vez que añades una forma verbal
                                        ConjugationEntry entry = new ConjugationEntry();
                                        entry.form = label;
                                        entry.conjugation = editText;
                                        conjugationEntries.add(entry);


                                        System.out.println(conjugationEntries);
                                    })
                                    .setNegativeButton("Cancelar", null)
                                    .show();
                        });

                        // Lógica del checkbox
                        checkBox.setOnClickListener(v -> {
                            if (!checkBox.isChecked()) {
                                verbalForms = new ArrayList<>(Arrays.asList(ConjugationForms.verbalForm));
                                linearLayout.addView(addVerbalFormsButton);
                            } else {
                                // Eliminar el botón
                                linearLayout.removeView(addVerbalFormsButton);

                                // Eliminar todas las filas agregadas
                                for (LinearLayout row : addedRows) {
                                    linearLayout.removeView(row);
                                }
                                conjugationEntries.clear(); // limpiamos las conjugaciones añadidas
                                addedRows.clear(); // Limpiamos la lista

                                // Opcional: Restaurar todas las opciones a verbalForms
                                // (Si no quieres perderlas para futuras selecciones)
                                // Esto requiere guardar qué se quitó.
                            }
                        });
                        fieldViews.put(field.getName(), checkBox);

                    // CHECKEO DE SI ES UN HASHMAP, SOPORTANDO MEANING, EXAMPLE, CONTEXT, CONJUGATION (OCULTA), FURIGANA y HISTORY
                    } else if (type == HashMap.class) {
                        TextView textView = new TextView(context);
                        textView.setText("Enter " + field.getName());
                        linearLayout.addView(textView); // título del campo

                        // Controlamos que tipo de hasmap será, por ejemplo si es para meaning, example o context tendremos los idiomas
                        if (field.getName().equals("meaning") || field.getName().equals("example") || field.getName().equals("context") || field.getName().equals("history")){
                            for (String language : languages) {
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
                                hashmapFieldViews
                                        .computeIfAbsent(field.getName(), k -> new HashMap<>())
                                        .put(language, editText);

                                // Agrega la fila al formulario
                                linearLayout.addView(row);
                            }
                        } else if (field.getName().equals("conjugation")) {
                            textView.setText("");
                            Log.d("VocabularyFormHandler", "Se detectó campo conjugation pero no se está procesando ya que se procesa en boolean");
                        } else if (field.getName().equals("furigana")) {
                            // Contenedor general vertical
                            LinearLayout optionalContainer = new LinearLayout(context);
                            optionalContainer.setOrientation(LinearLayout.VERTICAL);
                            optionalContainer.setPadding(0, 16, 0, 16);

                            // Botón para añadir más pares de campos
                            Button addButton = new Button(context);
                            addButton.setText("➕ Add new Furigana");

                            // Acción del botón
                            addButton.setOnClickListener(v -> {
                                // Fila horizontal
                                LinearLayout fila = new LinearLayout(context);
                                fila.setOrientation(LinearLayout.HORIZONTAL);
                                fila.setPadding(0, 8, 0, 8);

                                // Campo izquierdo
                                EditText leftField = new EditText(context);
                                leftField.setHint("Kanji");
                                leftField.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

                                // Campo derecho
                                EditText rightField = new EditText(context);
                                rightField.setHint("Kana");
                                rightField.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

                                // Botón eliminar
                                Button eliminarBtn = new Button(context);
                                eliminarBtn.setText("❌");
                                eliminarBtn.setOnClickListener(x -> {
                                    optionalContainer.removeView(fila);
                                });

                                // Añadir los elementos a la fila
                                fila.addView(leftField);
                                fila.addView(rightField);
                                fila.addView(eliminarBtn);

                                FuriganaEntry entry = new FuriganaEntry();
                                entry.kanji = leftField;
                                entry.kana = rightField;
                                furiganaEntries.add(entry);

                                // Añadir la fila al contenedor principal
                                optionalContainer.addView(fila);

                            });

                            // Añadir el botón y el contenedor al layout principal
                            linearLayout.addView(addButton);
                            linearLayout.addView(optionalContainer);
                        } else {
                            TextView unsupported = new TextView(context);
                            unsupported.setText("Unsupported Hashmap: " + field.getName());
                            linearLayout.addView(unsupported);
                        }
                    // CHECKEO SI ES UN LIST, SOPORTANDO DE MOMENTO SOLO SUBCATEGORY, ONYOMI Y KUNYOMI
                    } else if (type == List.class) {
                        if (field.getName().equals("subcategory")){
                            // Opciones predefinidas (puedes cambiar estas)
                            boolean[] checkedItems = new boolean[subcategories.length];
                            List<String> selectedItems = new ArrayList<>();

                            // Botón para abrir el selector
                            Button selectButton = new Button(context);
                            selectButton.setText("Select tags");

                            // TextView para mostrar los seleccionados
                            TextView selectedText = new TextView(context);
                            fieldViews.put("subcategory", selectedText);
                            selectedText.setText("Selected: None");

                            selectButton.setOnClickListener(v -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder((Activity) context);
                                builder.setTitle("Select options");
                                builder.setMultiChoiceItems(subcategories, checkedItems, (dialog, which, isChecked) -> {
                                    if (isChecked) {
                                        selectedItems.add(subcategories[which]);
                                    } else {
                                        selectedItems.remove(subcategories[which]);
                                    }
                                });

                                builder.setPositiveButton("OK", (dialog, which) -> {
                                    if (!selectedItems.isEmpty()) {
                                        selectedText.setText("Selected: " + String.join(", ", selectedItems));
                                    } else {
                                        selectedText.setText("Selected: None");
                                    }
                                });

                                builder.setNegativeButton("Cancel", null);
                                builder.show();
                            });
                            TextView label = new TextView(context);
                            label.setText("Subcategories");

                            linearLayout.addView(label);
                            linearLayout.addView(selectButton);
                            linearLayout.addView(selectedText);

                        } else if (field.getName().equals("onyomi") || field.getName().equals("kunyomi") || field.getName().equals("radical")) {
                            // Contenedor general vertical
                            LinearLayout optionalContainer = new LinearLayout(context);
                            optionalContainer.setOrientation(LinearLayout.VERTICAL);
                            optionalContainer.setPadding(0, 16, 0, 16);

                            // Botón para añadir más pares de campos
                            Button addButton = new Button(context);
                            addButton.setText("➕ Add new "+field.getName());

                            // Acción del botón
                            addButton.setOnClickListener(v -> {
                                // Fila horizontal
                                LinearLayout fila = new LinearLayout(context);
                                fila.setOrientation(LinearLayout.HORIZONTAL);
                                fila.setPadding(0, 8, 0, 8);

                                // Campo izquierdo
                                EditText leftField = new EditText(context);
                                listFieldViews.computeIfAbsent(field.getName(), k -> new ArrayList<>()).add(leftField);
                                leftField.setHint(field.getName());
                                leftField.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

                                // Botón eliminar
                                Button eliminarBtn = new Button(context);
                                eliminarBtn.setText("❌");
                                eliminarBtn.setOnClickListener(x -> optionalContainer.removeView(fila));

                                // Añadir los elementos a la fila
                                fila.addView(leftField);
                                fila.addView(eliminarBtn);

                                // Añadir la fila al contenedor principal
                                optionalContainer.addView(fila);
                            });
                            // Añadir el botón y el contenedor al layout principal
                            linearLayout.addView(addButton);
                            linearLayout.addView(optionalContainer);
                        } else {
                            TextView unsupported = new TextView(context);
                            unsupported.setText("Unsupported field: " + field.getName()+" inside List");
                            linearLayout.addView(unsupported);
                        }

                    // Si no está soportado lo dice, para en el futuro si nos falta algo venimos aqui
                    } else {
                        TextView unsupported = new TextView(context);
                        unsupported.setText("Unsupported field: " + field.getName());
                        linearLayout.addView(unsupported);
                    }
                });

    }

    // Metodo para generar el spinner pasando los valores que tendrá, el nombre del spinner, y el objeto spinner en sí
    private void generateSpinnerString(String[] values, String key, Spinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                values
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Etiqueta para el campo
        TextView label = new TextView(context);
        label.setText(key);

        // Añadir la vista
        linearLayout.addView(label);
        linearLayout.addView(spinner);

        //Añadir los valores en
        fieldViews.put(key, spinner);
    }

    // Método que recopila los datos introducidos en un formulario y los guarda en un HashMap con claves tipo String y valores tipo Object
    public HashMap<String, Object> collectFormData() {
        HashMap<String, Object> data = new HashMap<>(); // Se crea el HashMap donde se almacenarán los datos recogidos

        // Recorre todos los elementos (views) individuales del formulario
        for (String key : fieldViews.keySet()) {
            Object v = fieldViews.get(key);
            v.getClass().toString();

            // Si es un campo de texto (EditText), se guarda el texto como valor
            if (v instanceof EditText) {
                data.put(key, ((EditText) v).getText().toString());

                // Si es un desplegable (Spinner), se guarda el ítem seleccionado como valor
            } else if (v instanceof Spinner) {
                Object selectedItem = ((Spinner) v).getSelectedItem();
                data.put(key, selectedItem != null ? selectedItem.toString() : "");
                // Si es una casilla (CheckBox), se guarda el estado (true/false)
            } else if (v instanceof CheckBox) {
                System.out.println("Estamos devolviendo el checkbox en el formhandler");
                data.put(key, ((CheckBox) v).isChecked());
                // Si es un campo de texto estático (TextView)
            } else if (v instanceof TextView) {
                // Caso especial si la clave es "subcategory"
                if (key.equals("subcategory")) {
                    String selected = ((TextView) v).getText().toString(); // e.g., "Selected: A, B"
                    List<String> selectedList = new ArrayList<>();

                    // Si no se ha seleccionado nada, se deja vacío
                    if (!selected.equals("Selected: None")) {
                        // Se eliminan los prefijos y se separa la cadena en una lista
                        selectedList = Arrays.asList(selected.replace("Selected: ", "").split(",\\s*"));
                    }
                    data.put("subcategory", selectedList);

                    // Cualquier otro caso de TextView
                } else {
                    data.put(key, ((TextView) v).getText().toString());
                }
            }
        }

        // Recorre los campos que son listas de EditText (por ejemplo, listas dinámicas de entradas)
        for (String key : listFieldViews.keySet()) {
            List<EditText> fields = listFieldViews.get(key);
            List<String> values = new ArrayList<>();

            // Se recopilan todos los valores no vacíos
            for (EditText et : fields) {
                String val = et.getText().toString().trim();
                if (!val.isEmpty()) values.add(val);
            }
            data.put(key, values); // Se guarda la lista de valores bajo su clave
        }

        // Recorre los campos que son HashMaps con claves por idioma (por ejemplo: "en", "es", "ja")
        for (String key : hashmapFieldViews.keySet()) {
            HashMap<String, EditText> langFields = hashmapFieldViews.get(key);
            HashMap<String, String> values = new HashMap<>();

            // Se recogen los valores no vacíos para cada idioma
            for (String lang : langFields.keySet()) {
                String val = langFields.get(lang).getText().toString().trim();
                if (!val.isEmpty()) values.put(lang, val);
            }
            data.put(key, values); // Se guarda el HashMap por idioma bajo su clave
        }

        // Si hay entradas de furigana (kanji y su lectura en kana)
        if (!furiganaEntries.isEmpty()) {
            HashMap<String, String> furiganaMap = new HashMap<>();

            // Se recogen todos los pares kanji-kana no vacíos
            for (FuriganaEntry entry : furiganaEntries) {
                String kanji = entry.kanji.getText().toString().trim();
                String kana = entry.kana.getText().toString().trim();
                if (!kanji.isEmpty() && !kana.isEmpty()) {
                    furiganaMap.put(kanji, kana);
                }
            }
            data.put("furigana", furiganaMap); // Se guarda el HashMap de furigana
        }

        // Si hay entradas de conjugations se leen
        if (!conjugationEntries.isEmpty()) {
            HashMap<String, String> conjugationMap = new HashMap<>();

            // Se recogen todos los pares forma-conjugacion no vacíos
            for (ConjugationEntry entry : conjugationEntries) {
                String form = entry.form.getText().toString().trim();
                String conjugation = entry.conjugation.getText().toString().trim();
                if (!form.isEmpty() && !conjugation.isEmpty()) {
                    conjugationMap.put(form, conjugation);
                }
            }
            data.put("conjugation", conjugationMap); // Se guarda el HashMap de furigana
            conjugationMap.forEach((key, value) -> Log.d("Conjugation inside Formhandler", "collectFormData: "+"[Key] : " + key + " [Value] : " + value));
        }

        Log.d("FormHandler", "fieldViews keys: " + fieldViews.keySet());

        Log.d("FormHandler", "Datos recogidos: " + data.toString());
        return data; // Devuelve el HashMap completo con todos los datos del formulario
    }


    private void deleteForm(String toEdit) {
        // Mostrar selector de vocabulario y borrarlo
        // Crear formulario dinámico para añadir vocabulario
        Log.d("VocabularyFormHandler", "Estamos añadiendo en el formulario de vocabulario");
        Field[] fields;
        if (toEdit.equals("vocabulary")) {
            VocabularyRepository vocabRepository = new VocabularyRepository();

            EditText searchBar = new EditText(context);
            searchBar.setHint("Search Word... (empty for all)");
            Button search = new Button(context);
            search.setText("Search");

            LinearLayout resultsContainer = new LinearLayout(context);
            resultsContainer.setOrientation(LinearLayout.VERTICAL);

            linearLayout.addView(searchBar);
            linearLayout.addView(search);
            linearLayout.addView(resultsContainer);  // Añadimos contenedor para resultados

            search.setOnClickListener(v -> {
                String query = searchBar.getText().toString().trim();
                if (query.isEmpty()){
                    vocabRepository.getAllVocabulary(new OnResultListener<Vocabulary>() {
                        @Override
                        public void onSuccess(List<Vocabulary> result) {
                            resultsContainer.removeAllViews(); // Limpiar anteriores

                            for (Vocabulary vocab : result) {
                                LinearLayout itemLayout = new LinearLayout(context);
                                itemLayout.setOrientation(LinearLayout.HORIZONTAL);

                                TextView vocabText = new TextView(context);
                                vocabText.setText(vocab.getMeaning().toString());
                                vocabText.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

                                Button deleteBtn = new Button(context);
                                deleteBtn.setText("❌");
                                deleteBtn.setOnClickListener(delView -> {
                                    vocabRepository.deleteVocabulary(vocab,
                                            unused -> {
                                                resultsContainer.removeView(itemLayout); // Eliminar de la vista si se borra correctamente
                                                Toast.makeText(context, "Vocabulario eliminado", Toast.LENGTH_SHORT).show();
                                            },
                                            error -> {
                                                Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show();
                                            }
                                    );
                                });

                                itemLayout.addView(vocabText);
                                itemLayout.addView(deleteBtn);

                                resultsContainer.addView(itemLayout);
                            }
                        }

                        @Override
                        public void onNotFound() {
                            Log.d("ALL_VOCAB", "No hay vocabulario en la colección.");
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.e("ALL_VOCAB", "Error al obtener vocabulario: " + e.getMessage());
                        }
                    });
                } else {
                    vocabRepository.searchVocabularyByMeaning(query, new OnResultListener<Vocabulary>() {
                        @Override
                        public void onSuccess(List<Vocabulary> results) {
                            resultsContainer.removeAllViews(); // Limpiar anteriores

                            for (Vocabulary vocab : results) {
                                LinearLayout itemLayout = new LinearLayout(context);
                                itemLayout.setOrientation(LinearLayout.HORIZONTAL);

                                TextView vocabText = new TextView(context);
                                vocabText.setText(vocab.getMeaning().toString());
                                vocabText.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

                                Button deleteBtn = new Button(context);
                                deleteBtn.setText("❌");
                                deleteBtn.setOnClickListener(delView -> {
                                    vocabRepository.deleteVocabulary(vocab,
                                            unused -> {
                                                resultsContainer.removeView(itemLayout); // Eliminar de la vista si se borra correctamente
                                                Toast.makeText(context, "Vocabulario eliminado", Toast.LENGTH_SHORT).show();
                                            },
                                            error -> {
                                                Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show();
                                            }
                                    );
                                });

                                itemLayout.addView(vocabText);
                                itemLayout.addView(deleteBtn);

                                resultsContainer.addView(itemLayout);
                            }
                        }

                        @Override
                        public void onNotFound() {
                            resultsContainer.removeAllViews();
                            TextView noResult = new TextView(context);
                            noResult.setText("No se encontró ningún vocabulario con ese significado");
                            resultsContainer.addView(noResult);
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.e("RESULT", "Error al buscar: " + e.getMessage());
                            Toast.makeText(context, "Error al buscar", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            });
        } else if (toEdit.equals("kanji")) {
            KanjiRepository kanjiRepository = new KanjiRepository();

            EditText searchBar = new EditText(context);
            searchBar.setHint("Search Kanji... (empty for all)");
            Button search = new Button(context);
            search.setText("Search");

            LinearLayout resultsContainer = new LinearLayout(context);
            resultsContainer.setOrientation(LinearLayout.VERTICAL);

            linearLayout.addView(searchBar);
            linearLayout.addView(search);
            linearLayout.addView(resultsContainer);  // Contenedor para resultados

            search.setOnClickListener(v -> {
                String query = searchBar.getText().toString().trim();
                if (query.isEmpty()) {
                    kanjiRepository.getAllKanji(new OnResultListener<Kanji>() {
                        @Override
                        public void onSuccess(List<Kanji> result) {
                            resultsContainer.removeAllViews(); // Limpiar anteriores

                            for (Kanji kanji : result) {
                                LinearLayout itemLayout = new LinearLayout(context);
                                itemLayout.setOrientation(LinearLayout.HORIZONTAL);

                                TextView kanjiText = new TextView(context);
                                kanjiText.setText(kanji.getMeaning().toString());
                                kanjiText.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

                                Button deleteBtn = new Button(context);
                                deleteBtn.setText("❌");
                                deleteBtn.setOnClickListener(delView -> {
                                    kanjiRepository.deleteKanji(kanji,
                                            unused -> {
                                                resultsContainer.removeView(itemLayout);
                                                Toast.makeText(context, "Kanji eliminado", Toast.LENGTH_SHORT).show();
                                            },
                                            error -> {
                                                Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show();
                                            }
                                    );
                                });

                                itemLayout.addView(kanjiText);
                                itemLayout.addView(deleteBtn);
                                resultsContainer.addView(itemLayout);
                            }
                        }

                        @Override
                        public void onNotFound() {
                            resultsContainer.removeAllViews();
                            TextView noResult = new TextView(context);
                            noResult.setText("No se encontraron kanjis.");
                            resultsContainer.addView(noResult);
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.e("KANJI_ALL", "Error al obtener kanji: " + e.getMessage());
                            Toast.makeText(context, "Error al buscar kanji", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    kanjiRepository.searchKanjiByMeaning(query, new OnResultListener<Kanji>() {
                        @Override
                        public void onSuccess(List<Kanji> results) {
                            resultsContainer.removeAllViews();

                            for (Kanji kanji : results) {
                                LinearLayout itemLayout = new LinearLayout(context);
                                itemLayout.setOrientation(LinearLayout.HORIZONTAL);

                                TextView kanjiText = new TextView(context);
                                kanjiText.setText(kanji.getMeaning().toString());
                                kanjiText.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

                                Button deleteBtn = new Button(context);
                                deleteBtn.setText("❌");
                                deleteBtn.setOnClickListener(delView -> {
                                    kanjiRepository.deleteKanji(kanji,
                                            unused -> {
                                                resultsContainer.removeView(itemLayout);
                                                Toast.makeText(context, "Kanji eliminado", Toast.LENGTH_SHORT).show();
                                            },
                                            error -> {
                                                Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show();
                                            }
                                    );
                                });

                                itemLayout.addView(kanjiText);
                                itemLayout.addView(deleteBtn);
                                resultsContainer.addView(itemLayout);
                            }
                        }

                        @Override
                        public void onNotFound() {
                            resultsContainer.removeAllViews();
                            TextView noResult = new TextView(context);
                            noResult.setText("No se encontró ningún kanji con ese significado");
                            resultsContainer.addView(noResult);
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.e("KANJI_SEARCH", "Error al buscar kanji: " + e.getMessage());
                            Toast.makeText(context, "Error al buscar", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder((Activity) context);
            builder.setTitle("El tipo no está permitido!");
        }
    }

    private void generateDeleteForm(Object o){

    }


    private void updateForm(String toDdit) {
        // Mostrar formulario con vocabulario ya cargado
    }

    // Clase auxiliar para el furigana
    private static class FuriganaEntry {
        EditText kanji;
        EditText kana;
    }

    private static class ConjugationEntry {
        TextView form;
        EditText conjugation;
    }

}
