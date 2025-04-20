package com.example.japaneseapplication.model;

import android.util.ArrayMap;

import java.util.Date;

public class ExamProgress extends Progress{
    // Attributes
    private ArrayMap<String, String> questionsAnswers;
    private String[] userAnswers;

    // Constructors
    public ExamProgress() {
    }

    public ExamProgress(String id, int hit, int fail, Date lastTimeSeen, Date firstTimeSeen, ArrayMap<String, String> questionsAnswers, String[] userAnswers) {
        super(id, hit, fail, lastTimeSeen, firstTimeSeen);
        this.questionsAnswers = questionsAnswers;
        this.userAnswers = userAnswers;
    }

    // Getter & Setters

    public ArrayMap<String, String> getQuestionsAnswers() {
        return questionsAnswers;
    }

    public void setQuestionsAnswers(ArrayMap<String, String> questionsAnswers) {
        this.questionsAnswers = questionsAnswers;
    }

    public String[] getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(String[] userAnswers) {
        this.userAnswers = userAnswers;
    }
}
