package duke.task;

/**
 * Represents a todo task
 */
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    /**
     * Gets format to display todo task on file
     *
     * @return format to display todo task on file
     */
    @Override
    public String getFileFormat() {
        return "T" + super.getFileFormat();
    }

    /**
     * Get general format to display todo task
     *
     * @return general format to display todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

