package com.example.japaneseapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button connect;
    Button createSession;
    TextView text;
    String email = "";
    String password = ""; // mínimo 6 caracteres
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });*/
        initializeButtons();
        FirebaseApp.initializeApp(this);


    }

    private void initializeButtons(){
        connect = findViewById(R.id.connect);
        createSession = findViewById(R.id.createSession);
        text = findViewById(R.id.textView);

        createSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
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
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String, Object> vocab = new HashMap<>();
                vocab.put("japanese", "犬");
                vocab.put("meaning", "test1");

                db.collection("vocabulary")
                        .add(vocab)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(MainActivity.this, "Palabra añadida", Toast.LENGTH_SHORT).show();
                            Log.d("FIREBASE", "Documento guardado con ID: " + documentReference.getId());
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(MainActivity.this, "Algo falló", Toast.LENGTH_SHORT).show();
                            Log.w("FIREBASE", "Error al guardar", e);
                        });
            }
        });
    }

}