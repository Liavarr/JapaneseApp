package com.example.japaneseapplication.model;

import com.example.japaneseapplication.controllers.FormIgnore;
import com.example.japaneseapplication.controllers.FormOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vocabulary {
    // Attributes
    @FormIgnore
    private String id;
    @FormOrder(1)
    private HashMap<String, String> meaning; // languages
    @FormOrder(2)
    private HashMap<String, String> example; // languages
    @FormOrder(3)
    private HashMap<String, String> context; // languages
    @FormOrder(4)
    private String level; // N5, N4, N3, N2, N1
    @FormOrder(5)
    private boolean regular; // If is a verb it could be not regular
    @FormOrder(6)
    private String group; // For example group 1, group 2, group 3, adj na, adj i, counter, etc.
    @FormOrder(7)
    private String category; //
    @FormOrder(8)
    private List<String> subcategory; //
    @FormOrder(9)
    private HashMap <String, String> conjugation; // Here we save all non regular verbs
    @FormOrder(10)
    private HashMap <String, String> furigana; //here we save syllable with its reading Kanji - Hiragana
    @FormOrder(11)
    private int difficulty;


    // Constructors
    public Vocabulary() {
    }

    public Vocabulary(String id, HashMap<String, String> meaning,  HashMap<String, String> example, HashMap<String, String> context, String level,  boolean regular, String category, List<String> subcategory, String group, HashMap<String, String> conjugation, HashMap<String, String> furigana, int difficulty) {
        this.id = id;
        this.meaning = meaning;
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
    public HashMap<String, String> getMeaning() {
        return meaning;
    }
    public HashMap<String, String> getExample() {
        return example;
    }

    public HashMap<String, String> getContext() {
        return context;
    }
    public String getLevel() {
        return level;
    }

    public boolean isRegular() {
        return regular;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getSubcategory() {
        return subcategory;
    }

    public String getGroup() {
        return group;
    }

    public HashMap<String, String> getConjugation() {
        return conjugation;
    }

    public HashMap<String, String> getFurigana() {
        return furigana;
    }

    public int getDifficulty() {
        return difficulty;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setMeaning(HashMap<String, String> meaning) {
        this.meaning = meaning;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setExample(HashMap<String, String> example) {
        this.example = example;
    }

    public void setContext(HashMap<String, String> context) {
        this.context = context;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubcategory(List<String> subcategory) {
        this.subcategory = subcategory;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setConjugation(HashMap<String, String> conjugation) {
        this.conjugation = conjugation;
    }

    public void setFurigana(HashMap<String, String> furigana) {
        this.furigana = furigana;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "id='" + id + '\'' +
                ", meaning=" + meaning +
                ", example=" + example +
                ", context=" + context +
                ", level='" + level + '\'' +
                ", regular=" + regular +
                ", group='" + group + '\'' +
                ", category='" + category + '\'' +
                ", subcategory=" + subcategory +
                ", conjugation=" + conjugation +
                ", furigana=" + furigana +
                ", difficulty=" + difficulty +
                '}';
    }
}
