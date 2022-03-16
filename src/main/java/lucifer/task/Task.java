package lucifer.task;

/**
 * Class Task which encapsulates the task information.
 * @author Yu Meng
 */
public abstract class Task {
    /** description of task **/
    protected String description;
    /** status of task **/
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isDone = true;
    }
    /**
     * Marks task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Outputs whether the task is done or not.
     */
    public String getStatusIcon() {
        // Marks done task with X
        return ((isDone ? "[X] " : "[ ] ") + this.description);
    }

    /**
     * Formats the string representation of task objects for
     * saving to the text file.
     */
    public abstract String saveFormat();

    /**
     {@inheritdoc}
     */
    @Override
    public String toString() {
        return getStatusIcon();
    }

}
