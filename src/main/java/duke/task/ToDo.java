package duke.task;

import duke.task.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String infoString() {
        return "T/" + super.infoString();
    }
}
