package pyke.task;

import pyke.task.Task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSavedFile() {
        return "T | " + super.toSavedFile();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}