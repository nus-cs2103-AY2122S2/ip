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

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[%s] %s%n", this.getStatusIcon(), this.description);
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("[%s] %s%n", this.getStatusIcon(), this.description);
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