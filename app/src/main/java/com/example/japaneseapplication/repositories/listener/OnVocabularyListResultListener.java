package com.example.japaneseapplication.repositories.listener;
import com.example.japaneseapplication.model.Vocabulary;
import java.util.List;


public interface OnVocabularyListResultListener {
    void onSuccess(List<Vocabulary> vocabularies);
    void onNotFound();
    void onFailure(Exception e);
}
