package tasks;

/**
 * Represents a todo task
 */
public class Todo extends Task {

    /**
     * Instantiates a new To-do by taking in a description
     * @param description description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Method that displays the To-do with the correct display format
     * @return A To-do with the correct display format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Method that displays the To-do with the correct storage file display format
     * @return A To-do with the correct storage file display format
     */
    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormat();
    }
}
