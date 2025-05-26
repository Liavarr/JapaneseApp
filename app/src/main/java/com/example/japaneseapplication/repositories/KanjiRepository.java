package com.example.japaneseapplication.repositories;

import com.example.japaneseapplication.model.Kanji;
import com.example.japaneseapplication.repositories.listener.OnResultListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KanjiRepository {
    private final FirebaseFirestore db;
    private final String COLLECTION_NAME = "kanji";

    public KanjiRepository() {
        db = FirebaseFirestore.getInstance();
    }

    // CREATE - con ID automático y asignado al objeto
    public void createKanji(final Kanji kanji, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure, Runnable onMeaningAlreadyExists) {
        Map<String, String> meanings = kanji.getMeaning();

        if (meanings == null || meanings.isEmpty()) {
            onFailure.onFailure(new IllegalArgumentException("Meaning cannot be null or empty"));
            return;
        }

        // Filtros por idioma
        List<Filter> filters = new ArrayList<>();
        for (Map.Entry<String, String> entry : meanings.entrySet()) {
            filters.add(Filter.equalTo("meaning." + entry.getKey(), entry.getValue()));
        }

        Query query = db.collection(COLLECTION_NAME)
                .where(Filter.or(filters.toArray(new Filter[0])));

        query.get()
                .addOnSuccessListener(snapshot -> {
                    if (!snapshot.isEmpty()) {
                        if (onMeaningAlreadyExists != null) onMeaningAlreadyExists.run();
                    } else {
                        db.collection(COLLECTION_NAME)
                                .add(kanji)
                                .addOnSuccessListener(docRef -> {
                                    kanji.setId(docRef.getId());
                                    docRef.set(kanji)
                                            .addOnSuccessListener(onSuccess)
                                            .addOnFailureListener(onFailure);
                                })
                                .addOnFailureListener(onFailure);
                    }
                })
                .addOnFailureListener(onFailure);
    }

    // READ - por ID
    public void getKanji(Kanji kanji, OnSuccessListener<Kanji> onSuccess, OnFailureListener onFailure) {
        db.collection(COLLECTION_NAME)
                .document(kanji.getId())
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Kanji k = documentSnapshot.toObject(Kanji.class);
                        onSuccess.onSuccess(k);
                    } else {
                        onFailure.onFailure(new Exception("Kanji not found"));
                    }
                })
                .addOnFailureListener(onFailure);
    }


    public void getAllKanji(OnResultListener<Kanji> listener) {
        db.collection(COLLECTION_NAME)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Kanji> results = new ArrayList<>();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Kanji kanji = doc.toObject(Kanji.class);
                        kanji.setId(doc.getId());
                        results.add(kanji);
                    }

                    if (!results.isEmpty()) {
                        listener.onSuccess(results);
                    } else {
                        listener.onNotFound();
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    // Buscar por significado (ejemplo para "jp", "en", "es")
    public void searchKanjiByMeaning(String palabra, OnResultListener listener) {
        Query query = db.collection(COLLECTION_NAME)
                .where(
                        Filter.or(
                                Filter.equalTo("meaning.jp", palabra),
                                Filter.equalTo("meaning.en", palabra),
                                Filter.equalTo("meaning.es", palabra)
                        )
                );

        query.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Kanji> resultados = new ArrayList<>();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Kanji kanji = doc.toObject(Kanji.class);
                        kanji.setId(doc.getId());
                        resultados.add(kanji);
                    }

                    if (!resultados.isEmpty()) {
                        listener.onSuccess(resultados);
                    } else {
                        listener.onNotFound();
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    // UPDATE
    public void updateKanji(Kanji kanji, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        if (kanji.getId() == null || kanji.getId().isEmpty()) {
            onFailure.onFailure(new Exception("Kanji ID is null or empty"));
            return;
        }

        db.collection(COLLECTION_NAME)
                .document(kanji.getId())
                .set(kanji)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }

    // DELETE
    public void deleteKanji(Kanji kanji, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        db.collection(COLLECTION_NAME)
                .document(kanji.getId())
                .delete()
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }
}
