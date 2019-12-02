package com.example.finalproject.Model;

public class Homework {
    private int id;
    private String hwrkName;
    private String hmwkDescirption;
    private String hmwkClass;
    private String hmwrkTime;
    private boolean isDone;
    private int day;
    private int month;
    private int year;

    public Homework(int id, String hwrkName, String hmwkDescirption, String hmwkClass, String hmwrkTime, int day, int month, int year) {
        this.id = id;
        this.hwrkName = hwrkName;
        this.hmwkDescirption = hmwkDescirption;
        this.hmwkClass = hmwkClass;
        this.hmwrkTime = hmwrkTime;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public Homework(String hwrkName, String hmwkDescirption, String hmwkClass, String hmwrkTime, int day, int month, int year) {
        this.hwrkName = hwrkName;
        this.hmwkDescirption = hmwkDescirption;
        this.hmwkClass = hmwkClass;
        this.hmwrkTime = hmwrkTime;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Homework(String hwrkName, String hmwkDescirption, String hmwkClass, String hmwrkTime) {
        this.hwrkName = hwrkName;
        this.hmwkDescirption = hmwkDescirption;
        this.hmwkClass = hmwkClass;
        this.hmwrkTime = hmwrkTime;
    }

    public String getHwrkName() {
        return hwrkName;
    }

    public void setHwrkName(String hwrkName) {
        this.hwrkName = hwrkName;
    }

    public String getHmwkDescirption() {
        return hmwkDescirption;
    }

    public void setHmwkDescirption(String hmwkDescirption) {
        this.hmwkDescirption = hmwkDescirption;
    }

    public String getHmwkClass() {
        return hmwkClass;
    }

    public void setHmwkClass(String hmwkClass) {
        this.hmwkClass = hmwkClass;
    }

    public String getHmwrkTime() {
        return hmwrkTime;
    }

    public void setHmwrkTime(String hmwrkTime) {
        this.hmwrkTime = hmwrkTime;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone){this.isDone=isDone;}

    public void setHmwrkTime(boolean isDone) {
        this.isDone = isDone;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
