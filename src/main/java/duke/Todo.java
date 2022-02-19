package duke;

/**
 * Represents a task that is to be done.
 */
public class Todo extends Task {
    protected String by;

    /**
     * Constructor of Todo.
     * 
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the todo task to a string that includes its status and description.
     */
    @Override
    public String toString() {
        String todoString = "[T]" + this.getStatusIcon() + " " + this.description;
        return todoString;
    }
}
