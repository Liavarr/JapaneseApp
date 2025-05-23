package com.example.japaneseapplication.repositories;

import com.example.japaneseapplication.model.Vocabulary;
import com.example.japaneseapplication.repositories.listener.OnResultListener;
import com.example.japaneseapplication.utils.Languages;
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


public class VocabularyRepository {
    private final FirebaseFirestore db;
    private final String COLLECTION_NAME = "vocabulary";

    public VocabularyRepository() {
        db = FirebaseFirestore.getInstance();
    }

    // CREATE - ahora generando ID automáticamente y guardándolo en el objeto, se pasa un
    public void createVocabulary(final Vocabulary vocabulary, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure, Runnable onMeaningAlreadyExists) {
        Map<String, String> meanings = vocabulary.getMeaning();

        if (meanings == null || meanings.isEmpty()) {
            onFailure.onFailure(new IllegalArgumentException("Meaning cannot be null or empty"));
            return;
        }

        // Creamos una lista de filtros por idioma
        List<Filter> filters = new ArrayList<>();
        for (Map.Entry<String, String> entry : meanings.entrySet()) {
            filters.add(Filter.equalTo("meaning." + entry.getKey(), entry.getValue()));
        }

        // Creamos una query con OR entre todos los posibles significados
        Query query = db.collection(COLLECTION_NAME)
                .where(Filter.or(filters.toArray(new Filter[0])));

        query.get()
                .addOnSuccessListener(snapshot -> {
                    if (!snapshot.isEmpty()) {
                        // Ya hay un documento con alguno de los significados
                        if (onMeaningAlreadyExists != null) onMeaningAlreadyExists.run();
                    } else {
                        // No hay duplicados, se puede guardar
                        db.collection(COLLECTION_NAME)
                                .add(vocabulary)
                                .addOnSuccessListener(docRef -> {
                                    vocabulary.setId(docRef.getId());
                                    docRef.set(vocabulary)
                                            .addOnSuccessListener(onSuccess)
                                            .addOnFailureListener(onFailure);
                                })
                                .addOnFailureListener(onFailure);
                    }
                })
                .addOnFailureListener(onFailure);
    }

    // READ - Lee un vocabulario pasandole el id, aqui le pasamos directamente los mensajes de error
    // porque busca por id, solo devuelve 1 siempre
    public void getVocabulary(Vocabulary vocabulary, OnSuccessListener<Vocabulary> onSuccess, OnFailureListener onFailure) {
        db.collection(COLLECTION_NAME)
                .document(vocabulary.getId())
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

    // Lee un vocabulario buscando por palabra, necesitamos implementar lo que hace cada listener para cada caso
    // si devuelve más de uno el listener devuelve una list
    public void searchVocabularyByMeaning(String palabra, OnResultListener listener) {
        // FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (String meaning:Languages.languages){
            // Te quedaste aqui para añadir que busque en todos los lenguajes disponibles ;)
        }
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

    // UPDATE - Se le pasa un vocabulary directamente, que cogerá buscando, se crea el objeto vocabulary y después se actualia,
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

    // DELETE - borra pasando el Vocabulary
    public void deleteVocabulary(Vocabulary vocabulary, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        db.collection(COLLECTION_NAME)
                .document(vocabulary.getId())
                .delete()
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }
}
