package duke.task;

/**
 * Todo task that inherits from Task
 */
public class Todo extends Task {

    /**
     * Default constructor for Todo
     *
     * @param description Description for Todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overloaded constructor for Todo
     * Allows for passing in whether task is done
     *
     * @param description Description for Todo
     * @param isDone Whether the task is done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overloaded toString function
     *
     * @return Returns a string representation for the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Overloaded getFileString function
     * Used in writing to file in a format that the parser will be able to parse
     *
     * @return A string representation of the task that the parser can understand
     */
    @Override
    public String getFileString() {
        return "T|" + (isDone == true ? "1|" : "0|") + getDescription() + "\n";
    }
}
