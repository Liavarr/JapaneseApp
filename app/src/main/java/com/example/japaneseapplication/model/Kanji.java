package com.example.japaneseapplication.model;

import android.util.ArrayMap;

import java.util.ArrayList;

public class Kanji {
    // Attributes
    private String id;
    private ArrayMap<String, String> meaning;
    private String level; // N5, N4, N3, N2, N1
    private String history;
    private ArrayList<Kanji> radical; // List of all kanjis that compose this kanji
    private ArrayList<String> onyomi; // Chinese reading
    private ArrayList<String> kunyomi; // Japanese reading
    private String category; //
    private String subcategory; //
    private int difficulty;

    // Constructors

    public Kanji() {
    }

    public Kanji(String id, ArrayMap<String, String> meaning, String level, String history, ArrayList<Kanji> radical, ArrayList<String> onyomi, ArrayList<String> kunyomi, String category, String subcategory, int difficulty) {
        this.id = id;
        this.meaning = meaning;
        this.level = level;
        this.history = history;
        this.radical = radical;
        this.onyomi = onyomi;
        this.kunyomi = kunyomi;
        this.category = category;
        this.subcategory = subcategory;
        this.difficulty = difficulty;
    }


    // Getters & Setter


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayMap<String, String> getMeaning() {
        return meaning;
    }

    public void setMeaning(ArrayMap<String, String> meaning) {
        this.meaning = meaning;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public ArrayList<Kanji> getRadical() {
        return radical;
    }

    public void setRadical(ArrayList<Kanji> radical) {
        this.radical = radical;
    }

    public ArrayList<String> getOnyomi() {
        return onyomi;
    }

    public void setOnyomi(ArrayList<String> onyomi) {
        this.onyomi = onyomi;
    }

    public ArrayList<String> getKunyomi() {
        return kunyomi;
    }

    public void setKunyomi(ArrayList<String> kunyomi) {
        this.kunyomi = kunyomi;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
