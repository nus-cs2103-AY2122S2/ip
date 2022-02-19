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
        return "";
    }

    public String unmarkAsDone() {
        return "";
    }

    public void markCheckBox() {
        this.isDone = true;
    }

    public void unmarkCheckBox() {
        this.isDone = false;
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