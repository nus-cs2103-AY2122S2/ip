public class Task {
    /** Description of task. */
    private final String desc;

    /** Status of task. */
    private boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param desc Description of task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Marks task.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns status icon of task.
     *
     * @return String representing the status of task.
     */
    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * String representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), desc);
    }
}