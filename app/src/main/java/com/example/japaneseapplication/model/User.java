package com.example.japaneseapplication.model;

import java.util.Date;

public class User {
    private String id;
    private String name;
    private String lastName;
    private String username;
    // private String password;
    private String email;
    private int userType;
    private Progress vocabularyProgress;
    private Progress kanjiProgress;
    private Progress grammarProgress;
    private Date creationDate;
    private int experience;
    private int level;

    public User() {
    }

    public User(String id, String name, String lastName, String username, String email, int userType, Progress vocabularyProgress, Progress kanjiProgress, Progress grammarProgress, Date creationDate, int experience, int level) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        //this.password = password;
        this.email = email;
        this.userType = userType;
        this.vocabularyProgress = vocabularyProgress;
        this.kanjiProgress = kanjiProgress;
        this.grammarProgress = grammarProgress;
        this.creationDate = creationDate;
        this.experience = experience;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /* public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Progress getVocabularyProgress() {
        return vocabularyProgress;
    }

    public void setVocabularyProgress(Progress vocabularyProgress) {
        this.vocabularyProgress = vocabularyProgress;
    }

    public Progress getKanjiProgress() {
        return kanjiProgress;
    }

    public void setKanjiProgress(Progress kanjiProgress) {
        this.kanjiProgress = kanjiProgress;
    }

    public Progress getGrammarProgress() {
        return grammarProgress;
    }

    public void setGrammarProgress(Progress grammarProgress) {
        this.grammarProgress = grammarProgress;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
