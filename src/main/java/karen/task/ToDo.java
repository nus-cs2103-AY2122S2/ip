package karen.task;

/**
 * Stores the description of the Tasks
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toSaveData() {
        return String.format("T|%s|%s", this.isDone, this.getDescription());
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
