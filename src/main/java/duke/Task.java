package duke;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int mark) {
        this.description = description;
        this.isDone = mark == 1;
    }

    public String getTask() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getMark() {
        if (this.isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return description;
    }
}