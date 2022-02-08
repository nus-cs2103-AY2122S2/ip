package karen.task;

/**
 * Stores the description of the Tasks
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo
     *
     * @param description Description of what ToDo is for
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toSaveData() {
        return String.format("T|%s|%s", isDone, getDescription());
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
