package duke.task.tasks;

import duke.task.tasks.Task;

public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String encode() {
        return "T <> " + super.encode() + "\n";
    }
}
