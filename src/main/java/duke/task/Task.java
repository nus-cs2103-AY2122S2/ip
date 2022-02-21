package duke.task;

/**
 * Tasks represent activities that the user has to do.
 * There are 3 different types of tasks: Todos, Deadlines and Events
 *
 * @author Jian Rong
 */
public abstract class Task {
    protected enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }
    protected Priority priority;
    protected String title;

    public Task(String title) {
        this.title = title;
        this.priority = Priority.LOW;
    }

    boolean isChecked = false;

    /**
     * Sets the value of a task to true.
     */
    public void setChecked() {
        this.isChecked = true;
    }

    /**
     * Sets the value of a task to false.
     */
    public void setUnchecked() {
        this.isChecked = false;
    }

    /**
     * Checks if title contains term.
     * @param term Search term by user
     * @return boolean True if title contains term, false otherwaise
     */
    public boolean titleContains(String term) {
        return title.contains(term);
    }

    public void setPriority(String priority) {
        switch (priority) {
        case ("low"):
            this.priority = Priority.LOW;
            break;
        case ("medium"):
            this.priority = Priority.MEDIUM;
            break;
        case ("high"):
            this.priority = Priority.HIGH;
            break;
        }
    }

}
