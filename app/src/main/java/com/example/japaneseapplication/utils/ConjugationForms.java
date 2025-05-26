package com.example.japaneseapplication.utils;

public class ConjugationForms {
    public static String[] verbalForm = new String[10];
    static {
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
        verbalForm[8] = "imperativeCasual";
        verbalForm[9] = "imperativeYakuza"; // XD
    }

    public static String[] adjetiveFormI = new String[10];
    static {
        // Base
        verbalForm[0] = "short"; // Remove I
        verbalForm[1] = "negative"; // ~Kunai
        verbalForm[2] = "past"; // ~Katta
        verbalForm[3] = "partNegative"; // ~Kunakatta

        // Say a couple of adjetives together
        verbalForm[4] = "kute"; // ~kute like Oishikute
    }


}
