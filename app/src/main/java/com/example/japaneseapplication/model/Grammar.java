package com.example.japaneseapplication.model;

import android.util.ArrayMap;

import java.util.ArrayList;

public class Grammar {
    // Attributes
    private String id;
    private ArrayMap<String, String> title; // explanation of the grammar in japanese (te imasu, y esas cosas) that will be showed up with the language you are using + japanese. I.E: Past (~ta)
    private ArrayMap<String, String> description;
    private ArrayMap<String, String> example;
    private String level; // N5, N4, N3, N2, N1
    private String category;
    private ArrayMap<String, String> structure;
    public Grammar() {
    }

    public Grammar(String id, String japanese, ArrayMap<String, String> title, ArrayMap<String, String> description, String level, String category, ArrayMap<String, String> example, ArrayMap<String, String> structure) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.level = level;
        this.example = example;
        this.category = category;
        this.structure = structure;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayMap<String, String> getTitle() {
        return title;
    }

    public void setTitle(ArrayMap<String, String> title) {
        this.title = title;
    }

    public ArrayMap<String, String> getDescription() {
        return description;
    }

    public void setDescription(ArrayMap<String, String> description) {
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

    public ArrayMap<String, String> getExample() {
        return example;
    }

    public void setExample(ArrayMap<String, String> example) {
        this.example = example;
    }

    public ArrayMap<String, String> getStructure() {
        return structure;
    }

    public void setStructure(ArrayMap<String, String> structure) {
        this.structure = structure;
    }
}
