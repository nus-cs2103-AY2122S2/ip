package duke.task;

/**
 * Represents a Task that the user wants to keep track of
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    Task() {
        this.taskName = "";
        this.isDone = false;
    }

    Task(String ss) {
        this.taskName = ss;
        this.isDone = false;
    }

    Task(String ss, boolean b) {
        this.taskName = ss;
        this.isDone = b;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone?"X":" ", taskName);
    }
}

