package com.example.finalproject.Model;

public class Task {
    private int taskId;
    private String taskName;
    private String taskDescription;
    private String taskTime;
    private boolean isDone;
    private int day;
    private int month;
    private int year;

    public Task(int taskId,String taskName, String taskDescription, String taskTime,int day, int month, int year) {
        this.taskId=taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskTime = taskTime;
        this.day=day;
        this.month=month;
        this.year=year;

    }

    public Task(String taskName, String taskDescription,String taskTime,int day, int month, int year) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskTime = taskTime;
        this.isDone =isDone;
        this.day=day;
        this.month=month;
        this.year=year;
    }
    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public Task() {

    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone){this.isDone=isDone;}

    public void setHmwrkTime(boolean isDone) {
        this.isDone = isDone;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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
}
