package duke.tasks;

import duke.tasks.Task;

/**
 * A subclass of Task class.
 * Denotes a task that is meant to happen with no specific date or time deadline
 */
public class Todo extends Task {
    protected static String type = "TODO";

    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
