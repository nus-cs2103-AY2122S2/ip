package siri;

public class Task {
    String description;
    String initialLetter;
    boolean isDone;

    public Task(String description, String initialLetter) { //task constructor
        this.description = description;
        this.initialLetter = initialLetter;
        this.isDone = false; //initialise as false
    }

    public String getStatusIcon() { //will return either "X" or " " depending if task is done or not
        return isDone
                ? "X" //if true mark with X
                : " "; //if false do not mark, leave as empty space
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
