package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description; // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String parseString() {
        int done = isDone ? 1 : 0;
        String parsedTask = " " + done + " | " + description;
        return parsedTask;
    }
    public void markIsDone() {
        this.isDone = true;
    }

    public void unmarkIsDone() {
        this.isDone = false;
    }
    //...
}
