package duke.task;

/**
 * The type To-do.
 */
public class ToDo extends Task {
    /**
     * Instantiates a new To do.
     *
     * @param description the description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
