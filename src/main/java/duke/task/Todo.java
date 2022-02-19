package duke.task;

/**
 * Represents a todolist task to be done. This event is a task that possesses a state/status
 * that is by default not done
 */
public class Todo extends Task {

    /**
     * Creates a todolist task that is initially not done
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of a todolist item
     *
     * @return String format of a todolist task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the String format of how the todolist task will be saved in the text file
     *
     * @return String format of the todolist task to be saved
     */
    public String toSave() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }
}
