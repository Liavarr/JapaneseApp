package com.example.japaneseapplication.model;

import android.util.ArrayMap;

import java.util.Map;

public class Vocabulary {
    private String id;
    private String japaneseMeaning;
    private String englishMeaning;
    private String spanishMeaning;
    private String level; // N5, N4, N3, N2, N1
    private String example;
    private String context;
    private boolean regular; // If is a verb it could be not regular
    private String category; //
    private String subcategory; //
    private String group; // For example group 1, group 2, group 3, adj na, adj i, counter, etc.
    private ArrayMap <String, String> conjugation; // Here we save all non regular verbs
    private ArrayMap <String, String> furigana;
    private int difficulty;
}
