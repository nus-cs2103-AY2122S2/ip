package duke.task;

/**
 * Represents a task which is a simple Todo.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo Class
     *
     * @param description The description of the todo.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        if (isDone) {
            this.markAsDone();
        }
    }

    /**
     * Returns a standardized format for storing the todo into the data file.
     *
     * @return String of todo in data file storage format.
     */
    @Override
    public String writeToFile() {
        return " T " + super.writeToFile() ;
    }

    /**
     * Adds extra custom formatting for user view specific to todos.
     *
     * @return A custom String display of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
