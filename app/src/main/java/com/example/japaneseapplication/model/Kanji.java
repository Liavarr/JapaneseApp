package com.example.japaneseapplication.model;

import com.example.japaneseapplication.controllers.FormIgnore;
import com.example.japaneseapplication.controllers.FormOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Kanji {
    // Attributes
    @FormIgnore
    private String id;
    @FormOrder(1)
    private HashMap<String, String> meaning;
    @FormOrder(5)
    private String level; // N5, N4, N3, N2, N1
    @FormOrder(10)
    private HashMap<String, String> history;
    @FormOrder(4)
    private List<String> radical; // List of all kanjis that compose this kanji
    @FormOrder(2)
    private List<String> onyomi; // Chinese reading
    @FormOrder(3)
    private List<String> kunyomi; // Japanese reading
    @FormOrder(6)
    private String category; //
    @FormOrder(7)
    private List<String> subcategory; //ç
    @FormOrder(9)
    private int difficulty;

    // Constructors

    public Kanji() {
    }

    public Kanji(String id, HashMap meaning, String level, HashMap<String, String> history, ArrayList<String> radical, ArrayList<String> onyomi, ArrayList<String> kunyomi, String category, List<String> subcategory, int difficulty) {
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

    public HashMap<String, String> getMeaning() {
        return meaning;
    }

    public void setMeaning(HashMap<String, String> meaning) {
        this.meaning = meaning;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public HashMap<String, String> getHistory() {
        return history;
    }

    public void setHistory(HashMap<String, String> history) {
        this.history = history;
    }

    public List<String> getRadical() {
        return radical;
    }

    public void setRadical(List<String> radical) {
        this.radical = radical;
    }

    public List<String> getOnyomi() {
        return onyomi;
    }

    public void setOnyomi(List<String> onyomi) {
        this.onyomi = onyomi;
    }

    public List<String> getKunyomi() {
        return kunyomi;
    }

    public void setKunyomi(List<String> kunyomi) {
        this.kunyomi = kunyomi;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<String> subcategory) {
        this.subcategory = subcategory;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Kanji{" +
                "id='" + id + '\'' +
                ", meaning=" + meaning +
                ", level='" + level + '\'' +
                ", history=" + history +
                ", radical=" + radical +
                ", onyomi=" + onyomi +
                ", kunyomi=" + kunyomi +
                ", category='" + category + '\'' +
                ", subcategory=" + subcategory +
                ", difficulty=" + difficulty +
                '}';
    }

    public Kanji buildKanjiFromMap(HashMap<String, Object> data) {
        Kanji kanji = new Kanji();
        // Meaning es HashMap<String, String>
        kanji.setMeaning((HashMap<String, String>) data.getOrDefault("meaning", new HashMap<>()));
        // Level es String
        kanji.setLevel((String) data.getOrDefault("level", ""));
        // History es String
        kanji.setHistory((HashMap<String, String>) data.getOrDefault("history", new HashMap<>()));
        // Radical es List<String>
        kanji.setRadical((List<String>) data.getOrDefault("radical", new ArrayList<>()));
        // Onyomi es List<String>
        kanji.setOnyomi((List<String>) data.getOrDefault("onyomi", new ArrayList<>()));
        // Kunyomi es List<String>
        kanji.setKunyomi((List<String>) data.getOrDefault("kunyomi", new ArrayList<>()));
        // Category es String
        kanji.setCategory((String) data.getOrDefault("category", ""));
        // Subcategory es List<String>
        kanji.setSubcategory((List<String>) data.getOrDefault("subcategory", new ArrayList<>()));
        // Difficulty es int, intenta parsear
        Object difficultyObj = data.get("difficulty");
        int difficulty = 0;
        if (difficultyObj != null) {
            try {
                difficulty = Integer.parseInt(difficultyObj.toString());
            } catch (NumberFormatException e) {
                // queda en 0
            }
        }
        kanji.setDifficulty(difficulty);

        // Si tienes ID, por si acaso:
        kanji.setId((String) data.getOrDefault("id", ""));
        kanji.toString();
        return kanji;
    }
}
