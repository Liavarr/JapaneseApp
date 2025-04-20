package com.example.japaneseapplication.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiraganaKatakana {
    private static final Map<String, List<String>> hiraganaMap = new HashMap<>();
    static {
        hiraganaMap.put("A", Arrays.asList("あ", "い", "う", "え", "お"));
        hiraganaMap.put("K", Arrays.asList("か", "き", "く", "け", "こ"));
        hiraganaMap.put("S", Arrays.asList("さ", "し", "す", "せ", "そ"));
        hiraganaMap.put("T", Arrays.asList("た", "ち", "つ", "て", "と"));
        hiraganaMap.put("N", Arrays.asList("な", "に", "ぬ", "ね", "の"));
        hiraganaMap.put("H", Arrays.asList("は", "ひ", "ふ", "へ", "ほ"));
        hiraganaMap.put("M", Arrays.asList("ま", "み", "む", "め", "も"));
        hiraganaMap.put("Y", Arrays.asList("や", "ゆ", "よ"));
        hiraganaMap.put("R", Arrays.asList("ら", "り", "る", "れ", "ろ"));
        hiraganaMap.put("W", Arrays.asList("わ", "を"));
        hiraganaMap.put("N (ん)", Arrays.asList("ん"));

        // Dakuten (sonidos con "g", "z", etc.)
        hiraganaMap.put("G", Arrays.asList("が", "ぎ", "ぐ", "げ", "ご"));
        hiraganaMap.put("Z", Arrays.asList("ざ", "じ", "ず", "ぜ", "ぞ"));
        hiraganaMap.put("D", Arrays.asList("だ", "ぢ", "づ", "で", "ど"));
        hiraganaMap.put("B", Arrays.asList("ば", "び", "ぶ", "べ", "ぼ"));
        hiraganaMap.put("P", Arrays.asList("ぱ", "ぴ", "ぷ", "ぺ", "ぽ"));
    }

    private static final Map<String, List<String>> katakanaMap = new HashMap<>();
    static {
        katakanaMap.put("A", Arrays.asList("ア", "イ", "ウ", "エ", "オ"));
        katakanaMap.put("K", Arrays.asList("カ", "キ", "ク", "ケ", "コ"));
        katakanaMap.put("S", Arrays.asList("サ", "シ", "ス", "セ", "ソ"));
        katakanaMap.put("T", Arrays.asList("タ", "チ", "ツ", "テ", "ト"));
        katakanaMap.put("N", Arrays.asList("ナ", "ニ", "ヌ", "ネ", "ノ"));
        katakanaMap.put("H", Arrays.asList("ハ", "ヒ", "フ", "ヘ", "ホ"));
        katakanaMap.put("M", Arrays.asList("マ", "ミ", "ム", "メ", "モ"));
        katakanaMap.put("Y", Arrays.asList("ヤ", "ユ", "ヨ"));
        katakanaMap.put("R", Arrays.asList("ラ", "リ", "ル", "レ", "ロ"));
        katakanaMap.put("W", Arrays.asList("ワ", "ヲ"));
        katakanaMap.put("N (ン)", Arrays.asList("ン"));

        // Dakuten / Handakuten
        katakanaMap.put("G", Arrays.asList("ガ", "ギ", "グ", "ゲ", "ゴ"));
        katakanaMap.put("Z", Arrays.asList("ザ", "ジ", "ズ", "ゼ", "ゾ"));
        katakanaMap.put("D", Arrays.asList("ダ", "ヂ", "ヅ", "デ", "ド"));
        katakanaMap.put("B", Arrays.asList("バ", "ビ", "ブ", "ベ", "ボ"));
        katakanaMap.put("P", Arrays.asList("パ", "ピ", "プ", "ペ", "ポ"));
    }

    // Verify if we are using Hiragana or Katakana
    public static boolean isKatakana(String value) {
        char c = value.charAt(0);
        if (c >= '\u30A0' && c <= '\u30FF'){
            return true;
        } else {
            return false;
        }
    }

    // Le pasamos el valor de la silaba y va a detectar si es katakana o no, despues devuelve la linea que corresponde
    public static String detectLine(String kana) {
        Map<String, List<String>> mapa = isKatakana(kana) ? katakanaMap : hiraganaMap;

        for (Map.Entry<String, List<String>> entry : mapa.entrySet()) {
            if (entry.getValue().contains(kana)) {
                return entry.getKey();
            }
        }
        return "Unknown parameter in detectLine";
    }

    // We sent the Kana Value and the position
    public static String returnLine (String kana, int position){
        // Detect first wether the kana is hiragana or katakana
        Map<String, List<String>> mapa = isKatakana(kana) ? katakanaMap : hiraganaMap;
        String line = "";

        // We save the line that corresponds to our kana, for example K
        for (Map.Entry<String, List<String>> entry : mapa.entrySet()) {
            if (entry.getValue().contains(kana)) {
                line = entry.getKey();
            }
        }

        // We save the whole line in a list, for example "か", "き", "く", "け", "こ", then we return the position on the list, 1 = ka
        List<String> syllable = mapa.get(line);
        if (syllable != null && position >= 0 && position < syllable.size()) {
            return syllable.get(position);
        } else {
            return "Not valid values";
        }
    }

}
