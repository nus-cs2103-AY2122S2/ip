package duke;

public class TaskToDos extends Task {
    /**
     * Constructor
     */
    public TaskToDos(boolean isDone, String name) {
        super(isDone, name);
    }

    /**
     * Returns String representation of the Task
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", isDone ? "X" : " ", name);
    }
}
