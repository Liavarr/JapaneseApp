package com.example.japaneseapplication.utils;

public class Categories {

    // All conjugation-related things
    public static String[] group = new String[8];
    {
        group[0] = "Group1";
        group[1] = "Group2";
        group[2] = "Group3";
        group[3] = "AdjI";
        group[4] = "AdjNa";
        group[5] = "Counter1"; // To define the type, like little items, plain, etc.
        group[6] = "Counter2"; // To define the type, like little items, plain, etc.
        group[7] = "Counter3"; // To define the type, like little items, plain, etc.
    }

    // Generic categories, for splitting the studies themselves
    public static String[] category = new String[4];
    {
        category[0] = "Noun";
        category[1] = "Adjetive";
        category[2] = "Verb";
        category[3] = "Numbering";
    }

    // This is more for searching and provide more context, we can use this for study per type, like I want to study kind of food, and those things
    public static String[] subcategory = new String[9];
    {
        subcategory[0] = "Family";
        subcategory[1] = "Food";
        subcategory[2] = "Place";
        subcategory[3] = "Clothes";
        subcategory[4] = "Colour";
        subcategory[5] = "Size";
        subcategory[6] = "School";
        subcategory[7] = "Home";
        subcategory[8] = "Vehicle";
    }


}
