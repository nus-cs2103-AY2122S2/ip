package alfred.task;


/**
 * Encapsulates a T0D0 task.
 */
public class ToDo extends Task {
    protected static final String type = "T";

    /**
     * Constructs a T0D0 object.
     *
     * @param description Name of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    protected ToDo(Boolean marked, String description) {
        super(description);
        if (marked) {
            this.markComplete();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskToSaveString() {
        String mark = this.isCompleted ? Task.COMPLETION_MARK : Task.INCOMPLETE_MARK;
        return String.join(Task.FORMAT_SPLIT, ToDo.type, mark, this.description);
    }

    @Override
    public String toString() {
        return "[" + ToDo.type + "]" + super.toString();
    }

}
