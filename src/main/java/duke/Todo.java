package duke;

/**
 * Represents a task to do.
 *
 * @author joey-chance
 * @version 1.0
 * @since 2022-02-05
 */
public class Todo extends Task{
    /**
     * Returns a Todo object with a description of the task.
     *
     * @param description description of the task to be done
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * This method is used to format a Todo object into a String which can then be stored in the text file.
     *
     * @return String This returns the String which details the task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
