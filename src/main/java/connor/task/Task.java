package connor.task;

/**
 * Represents a {@code Task}.
 */
public class Task {
    protected String desc;
    protected boolean isDone = false;
    protected TaskType taskType;

    /**
     * Constructor for {@code Task} class.
     *
     * @param desc Description of the task.
     * @param taskType Type of task.
     */
    public Task(String desc, TaskType taskType) {
        this.desc = desc;
        this.taskType = taskType;
    }

    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getType() {
        switch (this.taskType) {
        case TODO:
            return "[T]";
        case DEADLINE:
            return "[D]";
        case EVENT:
            return "[E]";
        default:
            return null;
        }
    }

    public String getLetter() {
        switch (this.taskType) {
        case TODO:
            return "T";
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        default:
            return null;
        }
    }

    public String getDesc() {
        return desc;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void mark() {
        setDone(true);
    }

    public void unmark() {
        setDone(false);
    }

    private void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a {@code String} representation of a generic {@code Task} type.
     *
     * @return A {@code String} representation of a generic {@code Task} type.
     */
    @Override
    public String toString() {
        return getStatus() + " " + desc;
    }

}
