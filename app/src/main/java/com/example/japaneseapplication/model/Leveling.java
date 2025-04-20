package com.example.japaneseapplication.model;

public class Leveling {
    // Attributes
    private String levelingType;
    private int experience;
    private int level;

    // Constructor

    public Leveling() {
    }

    public Leveling(String levelingType, int experience, int level) {
        this.levelingType = levelingType;
        this.experience = experience;
        this.level = level;
    }


    // Getters and Setterts

    public String getLevelingType() {
        return levelingType;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevelingType(String levelingType) {
        this.levelingType = levelingType;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void calculateExperience(Object o){
        int experience = 0;
        if (o instanceof Vocabulary){

        } else if (o instanceof Grammar) {
            
        } else if (o instanceof Kanji) {

        } else if (o instanceof Exam) {

        } else {
            System.out.println("Not recognized type");
        }

        switch (this.level){
            case 1:
                // Exp
                break;
            case 2:
                // Exp
                break;
            case 3:
                // Exp
                break;
            case 4:
                // Exp
                break;
            case 5:
                // Exp
                break;
            default:

        }
    }

    public void levelUp (User user){

    }
}
