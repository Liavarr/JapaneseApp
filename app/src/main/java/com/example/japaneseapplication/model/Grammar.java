package com.example.japaneseapplication.model;

import java.util.ArrayList;

public class Grammar {
    // Attributes
    private String id;
    private String japanese; // explanation of the grammar in japanese (te imasu, y esas cosas) that will be showed up with the language you are using + japanese. I.E: Past (~ta)
    private String title;
    private String description;
    private String level; // N5, N4, N3, N2, N1
    private String category;
    private String exampleJapanese;
    private String structure;

    public Grammar() {
    }

    public Grammar(String id, String japanese, String title, String description, String level, String category, String exampleJapanese, String structure) {
        this.id = id;
        this.japanese = japanese;
        this.title = title;
        this.description = description;
        this.level = level;
        this.category = category;
        this.exampleJapanese = exampleJapanese;
        this.structure = structure;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJapanese() {
        return japanese;
    }

    public void setJapanese(String japanese) {
        this.japanese = japanese;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExampleJapanese() {
        return exampleJapanese;
    }

    public void setExampleJapanese(String exampleJapanese) {
        this.exampleJapanese = exampleJapanese;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }
}
