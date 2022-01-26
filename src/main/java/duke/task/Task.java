package duke.task;

import duke.DukeException;

public abstract class Task {
    protected final String description;
    protected boolean isDone = false;

    public Task(String description) {
        if (description == null || description.isEmpty()) {
            throw new DukeException("The description of a Task cannot be empty");
        }

        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public abstract String toSaveData();
}
