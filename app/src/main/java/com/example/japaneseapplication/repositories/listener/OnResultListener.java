package com.example.japaneseapplication.repositories.listener;
import com.example.japaneseapplication.model.Vocabulary;
import java.util.List;

public interface OnResultListener<T> {
    void onSuccess(List<T> result);
    void onNotFound();
    void onFailure(Exception e);
}
