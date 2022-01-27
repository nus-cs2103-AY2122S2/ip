package main.java.duke;

// Credits: partial solution from
// https://nus-cs2103-ay2122s2.github.io/website/schedule/week2/project.html
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toFileFormat() {
        return null;
    }
}
