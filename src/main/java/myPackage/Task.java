package myPackage;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String markAsDone() {
        this.isDone = true;
        return "";
    }

    public String unmarkAsDone() {
        this.isDone = false;
        return "";
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return " ";
    }

    public String getFullDescription() {
        return this.description;
    }

    public String getShortDescription() {
        return "";
    }

    public String getOriginalDescription() {return description;}

    public String getTiming() {
        return "";
    }

}
    //Task t = new Task("read book");
    //t.markAsDone();