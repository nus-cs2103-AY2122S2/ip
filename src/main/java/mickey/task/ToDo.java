package mickey.task;

/**
 * ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructor.
     *
     * @param description Task description.
     */
    public ToDo(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
