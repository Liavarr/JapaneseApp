package com.example.japaneseapplication.utils;

public class VerbalForms {
    public String[] verbalForm = new String[18];
    {
        // base
        verbalForm[0] = "short"; // Dicctionary
        // long
        verbalForm[1] = "long"; // ~Masu
        verbalForm[2] = "longNegative"; // ~Masen
        verbalForm[3] = "longPast"; // ~Mashita
        verbalForm[4] = "longPastNegative"; // ~Masen Deshita
        // short
        verbalForm[5] = "shortNegative"; // ~nai
        verbalForm[6] = "shortPast"; // ~ta
        verbalForm[7] = "shortPastNegative"; // ~nakatta
        // rest
        verbalForm[8] = "tbd";
        verbalForm[9] = "tbd";
        verbalForm[10] = "tbd";
    }

    public String[] level = new String[5];
    {
        level[0] = "N5";
        level[1] = "N4";
        level[2] = "N3";
        level[3] = "N2";
        level[4] = "N1";
    }

    // This one is used to identify how to conjugate the verb, adj, etc. Can be used to filters words as well
    public String[] group = new String[10];
    {
        group[0] = "verbGroup1";
        group[1] = "verbGroup2";
        group[2] = "verbGroup3";
        group[3] = "AdjI";
        group[4] = "AdjNa";
        group[5] = "AdjNa";
        group[6] = "Counter";
        group[7] = "Noun";
        group[8] = "tbd";
        group[9] = "tbd";

    }

    // This one is used for categories itself, to filter in the app, such noun, verb, adj, etc.
    public String[] category = new String[10];

    {
        category[0] = "verb";
        category[1] = "adjetive";
        category[2] = "numbering";
        category[3] = "noun";
        category[4] = "tbd";
        category[5] = "tbd";
    }
    public String[] subcategory = new String[10];
    {
        subcategory[0] = "food";
        subcategory[1] = "animal";
        subcategory[2] = "place";
        subcategory[3] = "family";
        subcategory[4] = "school";
        subcategory[5] = "home";
        subcategory[6] = "vehicle";
    }
}
