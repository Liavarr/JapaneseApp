package com.example.japaneseapplication.ui;

import android.os.Bundle;


import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.japaneseapplication.R;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setupDrawer(R.layout.activity_main);
        // initializeButtons();
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });*/




    }



    /*private void initializeButtons(){
        connect = findViewById(R.id.connect);
        createSession = findViewById(R.id.createSession);
        delete = findViewById(R.id.delete);
        text = findViewById(R.id.textView);

        createSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                user = FirebaseAuth.getInstance().getCurrentUser();
                                Log.d("UID", user.getUid());

                                Toast.makeText(MainActivity.this, "Usuario logueado", Toast.LENGTH_SHORT).show();
                                Log.d("FIREBASE", "Usuario logueado: " + user.getEmail());

                                // Aquí ya podés acceder a su UID, email, etc.
                            } else {
                                Toast.makeText(MainActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                                Log.e("FIREBASE", "Error al iniciar sesión", task.getException());
                            }
                        });
            }
        });
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lo que querés que haga
                Toast.makeText(MainActivity.this, "¡Botón pulsado!", Toast.LENGTH_SHORT).show();


                service.createVocabulary(vocab1,
                        unused -> Log.d("Firebase", "Vocabulario 1 creado: " + vocab1.getId()),
                        e -> Log.e("Firebase", "Error creando vocabulario 1", e)
                );

                service.createVocabulary(vocab2,
                        unused -> Log.d("Firebase", "Vocabulario 2 creado: " + vocab2.getId()),
                        e -> Log.e("Firebase", "Error creando vocabulario 2", e)
                );

                service.searchVocabularyByMeaning("来る", new OnVocabularyListResultListener() {
                    @Override
                    public void onSuccess(List<Vocabulary> vocabularies) {
                        // Aquí puedes trabajar con la lista de resultados
                        for (Vocabulary vocab : vocabularies) {
                            Log.d("VOCAB", "ID: " + vocab.toString());
                        }
                    }

                    @Override
                    public void onNotFound() {
                        Log.d("VOCAB", "No se encontró la palabra.");
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.e("VOCAB", "Error al buscar la palabra", e);
                    }
                });
            }
        });*/

        /*delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lo que querés que haga
                Toast.makeText(MainActivity.this, "¡Botón pulsado!", Toast.LENGTH_SHORT).show();
                service.searchVocabularyByMeaning("venir", new OnVocabularyListResultListener() {
                    @Override
                    public void onSuccess(List<Vocabulary> vocabularies) {
                        // Aquí puedes trabajar con la lista de resultados
                        for (Vocabulary vocab : vocabularies) {
                            service.deleteVocabulary(
                                    vocab.getId(),
                                    unused -> Log.d("Firebase", "Vocabulary deleted: " + vocab.toString()+ " -- "+user.getUid()),
                                    e -> Log.e("Firebase", "Error deleting "+vocab.getId(), e));
                        }
                    }

                    @Override
                    public void onNotFound() {
                        Log.d("VOCAB", "No se encontró la palabra.");
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.e("VOCAB", "Error al buscar la palabra", e);
                    }
                });
            }
        });
    }*/

    /*Button connect;
    Button createSession;
    Button delete;
    TextView text;
    String email = "";
    String password = ""; // mínimo 6 caracteres
    FirebaseUser user;
    VocabularyRepository service = new VocabularyRepository();*/

    /*Vocabulary vocab1 = new Vocabulary(
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

    Vocabulary vocab2 = new Vocabulary(
            null,
            new HashMap<String, String>() {{
                put("en", "to come");
                put("es", "venir");
                put("jp", "来る");
            }},
            new HashMap<String, String>() {{
                put("en", "He comes to school.");
                put("es", "Él viene a la escuela.");
                put("jp", "彼は学校に来ます。");
            }},
            new HashMap<String, String>() {{
                put("general", "Used for indicating movement towards the speaker.");
            }},
            "N5",
            false,          // No regular
            "verb",
            new ArrayList<>(Arrays.asList("colores", "ropa", "comida")),
            "grupo 3",      // Irregular verbs are usually treated as "grupo 3"
            new HashMap<String, String>() {{
                put("negativo", "来ない");
                put("pasado", "来た");
                put("teForm", "来て");
                put("potencial", "来られる");
                put("pasiva", "来られる");
                put("causativa", "来させる");
                put("volitiva", "来よう");
                put("condicional", "来れば");
            }},
            new HashMap<String, String>() {{
                put("来", "く");
            }},
            3
    );*/

}