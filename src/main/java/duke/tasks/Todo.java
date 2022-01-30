package duke.tasks;

/**
 * Todo is a class which represents the todo task.
 */
public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the description of the task into a standardised format to
     * be saved to a file.
     *
     * @return A String which is to be saved in a file.
     */
    @Override
    public String formatString() {
        String output = "T";
        String markState = this.isDone ? "mark" : "unmark";
        return output + " | " + markState + " | " + this.description;
    }

    /**
     * Returns the String representation of the Todo task.
     *
     * @return A String representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
