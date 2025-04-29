package com.example.japaneseapplication.repositories;

import com.example.japaneseapplication.model.Vocabulary;
import com.example.japaneseapplication.repositories.listener.OnVocabularyListResultListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;


public class VocabularyRepository {
    private final FirebaseFirestore db;
    private final String COLLECTION_NAME = "vocabulary";

    public VocabularyRepository() {
        db = FirebaseFirestore.getInstance();
    }

    // CREATE - ahora generando ID automáticamente y guardándolo en el objeto
    public void createVocabulary(final Vocabulary vocabulary, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        db.collection(COLLECTION_NAME)
                .add(vocabulary)
                .addOnSuccessListener(documentReference -> {
                    String generatedId = documentReference.getId();
                    vocabulary.setId(generatedId);

                    // Ahora actualizamos el documento para guardar el ID dentro del objeto
                    documentReference.set(vocabulary)
                            .addOnSuccessListener(onSuccess)
                            .addOnFailureListener(onFailure);
                })
                .addOnFailureListener(onFailure);
    }

    // READ
    public void getVocabulary(String id, OnSuccessListener<Vocabulary> onSuccess, OnFailureListener onFailure) {
        db.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Vocabulary vocab = documentSnapshot.toObject(Vocabulary.class);
                        onSuccess.onSuccess(vocab);
                    } else {
                        onFailure.onFailure(new Exception("Vocabulary not found"));
                    }
                })
                .addOnFailureListener(onFailure);
    }

    public void searchVocabularyByMeaning(String palabra, OnVocabularyListResultListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                    List<Vocabulary> resultados = new ArrayList<>();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Vocabulary vocab = doc.toObject(Vocabulary.class);
                        vocab.setId(doc.getId());
                        resultados.add(vocab);
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
    public void updateVocabulary(Vocabulary vocabulary, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        if (vocabulary.getId() == null || vocabulary.getId().isEmpty()) {
            onFailure.onFailure(new Exception("Vocabulary ID is null or empty"));
            return;
        }

        db.collection(COLLECTION_NAME)
                .document(vocabulary.getId())
                .set(vocabulary)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }

    // DELETE
    public void deleteVocabulary(String id, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        db.collection(COLLECTION_NAME)
                .document(id)
                .delete()
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }
}
