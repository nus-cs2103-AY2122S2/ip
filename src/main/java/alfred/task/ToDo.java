package alfred.task;


/**
 * Encapsulates a T0D0 task.
 */
public class ToDo extends Task {
    protected static final String TYPE = "T";
    private static final String SYMBOL = "\uD83D\uDCDA";
    private final String description;

    /**
     * Constructs a T0D0 object.
     *
     * @param description Name of the task.
     */
    public ToDo(String description) {
        super(description);
        this.description = description;
    }

    protected ToDo(Boolean marked, String description) {
        super(description);
        this.description = description;
        if (marked) {
            this.markComplete();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskToSaveString() {
        String mark = this.isCompleted ? Task.FORMAT_COMPLETION_MARK : Task.FORMAT_INCOMPLETE_MARK;
        return String.join(Task.FORMAT_SPLIT, ToDo.TYPE, mark, this.description);
    }

    @Override
    public String toString() {
        return " " + ToDo.SYMBOL + " " + super.toString();
    }

    @Override
    public boolean equals(Task task) {

        if (!(task instanceof ToDo)) {
            return false;
        }

        ToDo todoTask = (ToDo) task;
        return this.description.equals(todoTask.description);
    }

}
