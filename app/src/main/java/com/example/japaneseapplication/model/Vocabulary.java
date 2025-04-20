package com.example.japaneseapplication.model;

import android.util.ArrayMap;

import java.util.Map;

public class Vocabulary {
    // Attributes
    private String id;
    private String japanese;
    private String meaning;
    private String level; // N5, N4, N3, N2, N1
    private String example;
    private String context;
    private boolean regular; // If is a verb it could be not regular
    private String category; //
    private String subcategory; //
    private String group; // For example group 1, group 2, group 3, adj na, adj i, counter, etc.
    private ArrayMap <String, String> conjugation; // Here we save all non regular verbs
    private ArrayMap <String, String> furigana; //here we save syllable with its reading Kanji - Hiragana
    private int difficulty;

    // Constructors
    public Vocabulary() {
    }

    public Vocabulary(String id, String japanese, String englishMeaning, String spanishMeaning, String level, String example, String context, boolean regular, String category, String subcategory, String group, ArrayMap<String, String> conjugation, ArrayMap<String, String> furigana, int difficulty) {
        this.id = id;
        this.japanese = japanese;
        this.meaning = englishMeaning;
        this.level = level;
        this.example = example;
        this.context = context;
        this.regular = regular;
        this.category = category;
        this.subcategory = subcategory;
        this.group = group;
        this.conjugation = conjugation;
        this.furigana = furigana;
        this.difficulty = difficulty;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getJapanese() {
        return japanese;
    }

    public String getMeaning() {
        return meaning;
    }


    public String getLevel() {
        return level;
    }

    public String getExample() {
        return example;
    }

    public String getContext() {
        return context;
    }

    public boolean isRegular() {
        return regular;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getGroup() {
        return group;
    }

    public ArrayMap<String, String> getConjugation() {
        return conjugation;
    }

    public ArrayMap<String, String> getFurigana() {
        return furigana;
    }

    public int getDifficulty() {
        return difficulty;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setJapaneseMeaning(String japanese) {
        this.japanese = japanese;
    }

    public void setEnglishMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setConjugation(ArrayMap<String, String> conjugation) {
        this.conjugation = conjugation;
    }

    public void setFurigana(ArrayMap<String, String> furigana) {
        this.furigana = furigana;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
