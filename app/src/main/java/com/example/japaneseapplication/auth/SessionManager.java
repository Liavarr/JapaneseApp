package com.example.japaneseapplication.auth;

import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SessionManager {

    private final FirebaseAuth mAuth;
    // Constructor de la clase. Al crear un SessionManager, obtenemos la instancia de autenticación activa.
    public SessionManager() {
        mAuth = FirebaseAuth.getInstance();
    }
    // Devuelve true si hay un usuario autenticado (es decir, si hay sesión activa), o false si no hay sesión.
    public boolean isLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }
    // Devuelve el objeto FirebaseUser actual (con info como UID, email, etc), o null si no hay usuario.
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }
    // Devuelve el identificador único del usuario (UID), o null si no hay sesión iniciada.
    public String getUid() {
        FirebaseUser user = getCurrentUser();
        return user != null ? user.getUid() : null;
    }
    // Devuelve el email del usuario autenticado, o null si no hay sesión.
    public String getEmail() {
        FirebaseUser user = getCurrentUser();
        return user != null ? user.getEmail() : null;
    }
    // Cierra la sesión actual y muestra un mensaje en los logs indicando que la sesión se ha cerrado.
    public void logout() {
        mAuth.signOut();
        Log.d("SESSION", "Sesión cerrada");
    }
}
