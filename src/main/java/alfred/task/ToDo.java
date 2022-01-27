package alfred.task;

/**
 * Encapsulates a T0D0 task.
 */
public class ToDo extends Task {
    String type = "T";

    /**
     * Constructs a T0D0 object.
     *
     * @param description Name of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString();
    }

}
