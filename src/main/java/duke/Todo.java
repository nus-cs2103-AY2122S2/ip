package duke;

/**
 * Represents a Task which only has a description
 */
public class Todo extends Task {
    private final String sym = "T";

    /**
     * Constructor for Todo
      * @param description The description of the todo Task
     */
    Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s]%s", sym, super.getStatusIcon(), super.getDescription());
    }
}
