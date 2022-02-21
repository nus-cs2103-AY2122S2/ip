package seedu.task;

/**
 * Inherits from the Task class.
 * Stores the description of a ToDo object.
 * Provides to String method to print event details in specific format.
 */
public class ToDo extends Task {

    /**
     * Creates an instance of a todo object.
     * Sets isDone as false by default.
     *
     * @param description Description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates an instance of a todo object.
     * Sets isDone based on the argument passed in.
     *
     * @param description Description of the todo task.
     * @param isDone Boolean value of whether the todo task is marked or unmarked.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Formats the description of the todo task to be written to a file.
     *
     * @return String format of todo description.
     */
    public String toWrite() {
        return "[T]" + super.toString();
    }

    /**
     * Overrides toString() method to print the todo task in a specific format.
     * Calls the toString() method of the inherited task class.
     *
     * @return String to be printed in a specified format for the specific todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
