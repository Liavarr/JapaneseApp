package com.example.japaneseapplication.utils;

public class Levels {
    public static String[] level = new String[5];
    static {
        level[0] = "N5";
        level[1] = "N4";
        level[2] = "N3";
        level[3] = "N2";
        level[4] = "N1";
    }

    public static int[] difficulty = new int[5];
    static {
        difficulty[0] = 1; // Easiest one
        difficulty[1] = 2;
        difficulty[2] = 3;
        difficulty[3] = 4;
        difficulty[4] = 5; // Hardest one
    }
}
