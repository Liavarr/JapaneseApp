package com.example.japaneseapplication.model;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Map;

public class Exam {
    private String id;
    private ArrayMap<String, String> questionsAnswers;
    private ArrayList<String> userAnswers;

    public Exam() {
    }

    public Exam(String id, ArrayMap<String, String> questionsAnswers, ArrayList<String> userAnswers) {
        this.id = id;
        this.questionsAnswers = questionsAnswers;
        this.userAnswers = userAnswers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayMap<String, String> getQuestionsAnswers() {
        return questionsAnswers;
    }

    public void setQuestionsAnswers(ArrayMap<String, String> questionsAnswers) {
        this.questionsAnswers = questionsAnswers;
    }

    public ArrayList<String> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(ArrayList<String> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
