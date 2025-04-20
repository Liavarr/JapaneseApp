package com.example.japaneseapplication.model;

import android.util.ArrayMap;

import java.lang.reflect.Array;
import java.util.Date;

public class Progress {

    // Attributes
    private String id;
    private Object object; // se le puede pasar cualquier tipo de objeto
    private int hit;
    private int fail;
    private Date lastTimeSeen;
    private Date firstTimeSeen;


    // Constructor
    public Progress() {
    }

    public Progress(String id, Object object, int hit, int fail, Date lastTimeSeen, Date firstTimeSeen) {
        this.id = id;
        this.object = object;
        this.hit = hit;
        this.fail = fail;
        this.lastTimeSeen = lastTimeSeen;
        this.firstTimeSeen = firstTimeSeen;

    }

    // Getter and Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public Date getLastTimeSeen() {
        return lastTimeSeen;
    }

    public void setLastTimeSeen(Date lastTimeSeen) {
        this.lastTimeSeen = lastTimeSeen;
    }

    public Date getFirstTimeSeen() {
        return firstTimeSeen;
    }

    public void setFirstTimeSeen(Date firstTimeSeen) {
        this.firstTimeSeen = firstTimeSeen;
    }
}
